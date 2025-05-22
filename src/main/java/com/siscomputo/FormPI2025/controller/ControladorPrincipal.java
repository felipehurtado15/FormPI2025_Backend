/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.siscomputo.FormPI2025.controller;

import com.siscomputo.FormPI2025.DTO.FormularioPremiosDTO;
import com.siscomputo.FormPI2025.config.EmailSender;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import static org.apache.commons.compress.utils.FileNameUtils.getExtension;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Felipe
 */
@Controller
public class ControladorPrincipal {

	private static final String UPLOAD_DIR = "/imagenes/formularios/"; // cambia esto según tu entorno

	private EmailSender emailSender;

	public ControladorPrincipal(EmailSender emailSender) {
		super();
		this.emailSender = emailSender;
	}

	@GetMapping("/")
	public String redireionar() {
		return "redirect:/form";
	}

	@GetMapping("/form")
	public String mostrarFormulario(Model model) {
		return "formulario";
	}

	@GetMapping("/listar")
	public String listarArchivos(Model model) {
		try {
			// Leer data del csv
			String nombreBase = "formularioPI_Master_2025";
			String nombreCSV = nombreBase + ".csv"; // Archivo CSV con nombre único
			Path rutaCSV = Paths.get(UPLOAD_DIR + nombreCSV);
			 List<String[]> filas = new ArrayList<>();
			 String separador = ",";
			 try (BufferedReader br = new BufferedReader(new FileReader(rutaCSV.toString()))) {
		            String linea;
		            while ((linea = br.readLine()) != null) {
		                String[] columnas = linea.split(separador);
		                filas.add(columnas);
		            }
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
			
			// Obtener todos los archivos del directorio
			List<FileInfo> archivos = Files.walk(Paths.get(UPLOAD_DIR)).filter(Files::isRegularFile).map(path -> {
				try {
					// Obtener los atributos del archivo, incluyendo la fecha de creación
					BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);
					// Formatear la fecha de creación
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
					String fechaCreacion = sdf.format(attrs.creationTime().toMillis());
					
					/// Filtar info en el csv
					String valorBuscado = path.getFileName().toString();
			        int indiceColumna = 39;

			        String nit = "";
			        String organizacion = "";
			        String postulante = "";
			        for (String[] fila : filas) {
			            if (fila.length > indiceColumna && fila[indiceColumna].equalsIgnoreCase(valorBuscado)) {
			               // System.out.println(Arrays.toString(fila));
			            	nit = fila[1];
			            	organizacion = fila[0];
			            	postulante = fila[32];
			            }
			        }
					
					return new FileInfo(path.getFileName().toString(), fechaCreacion, nit, organizacion, postulante);
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}
			}).filter(fileInfo -> fileInfo != null) // Filtrar archivos nulos
					.collect(Collectors.toList());
			archivos.sort(Comparator.comparing(FileInfo::getFechaCreacion));
			// Añadir los archivos al modelo
			model.addAttribute("archivos", archivos);

		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("error", "Error al listar archivos.");
		}

		return "listarArchivos"; // nombre de la vista
	}

	// Clase auxiliar para almacenar la información del archivo
	public static class FileInfo {

		private String nombre;
		private String fechaCreacion;
		private String nit; 
		private String organizacion; 
		private String postulante; 

		

		public FileInfo(String nombre, String fechaCreacion, String nit, String organizacion, String postulante) {
			this.nombre = nombre;
			this.fechaCreacion = fechaCreacion;
			this.nit = nit;
			this.organizacion = organizacion;
			this.postulante = postulante;
		}

		public String getNombre() {
			return nombre;
		}

		public String getFechaCreacion() {
			return fechaCreacion;
		}

		public String getNit() {
			return nit;
		}

		public void setNit(String nit) {
			this.nit = nit;
		}

		public String getOrganizacion() {
			return organizacion;
		}

		public void setOrganizacion(String organizacion) {
			this.organizacion = organizacion;
		}

		public String getPostulante() {
			return postulante;
		}

		public void setPostulante(String postulante) {
			this.postulante = postulante;
		}
	}
	// Descargar un archivo

	@GetMapping("/descargar/{nombreArchivo}")
	public ResponseEntity<Resource> descargarArchivo(@PathVariable String nombreArchivo) throws IOException {
		Path archivoPath = Paths.get(UPLOAD_DIR + nombreArchivo);
		Resource resource = new UrlResource(archivoPath.toUri());

		if (resource.exists() || resource.isReadable()) {
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
					.body(resource);
		} else {
			throw new IOException("No se puede leer el archivo: " + nombreArchivo);
		}
	}

	@PostMapping("/enviarFormulario")
	public String procesarFormulario(@ModelAttribute FormularioPremiosDTO form, Model model) {
		try {
			// 1. Guardar el archivo subido
			String fecha = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			String hash = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
			String nombreBase = "formularioPI_Master_2025";

			// 2. Guardar archivo subido con nombre único (hash)
			MultipartFile archivo = form.getFile();
			String archivoNombreConHash = hash + "_" + fecha;
			String nombreArchivo = "";
			if (archivo != null && !archivo.isEmpty()) {
				String extension = getExtension(archivo.getOriginalFilename());
				nombreArchivo = form.getNIT().replaceAll("[^0-9.]", "") + "_" + archivoNombreConHash + "."
						+ extension;
				archivoNombreConHash = nombreArchivo;
				Path rutaArchivo = Paths.get(UPLOAD_DIR + nombreArchivo);
				Files.copy(archivo.getInputStream(), rutaArchivo, StandardCopyOption.REPLACE_EXISTING);
			}

			// 3. Guardar CSV con los datos del formulario y nombre del archivo
			String nombreCSV = nombreBase + ".csv"; // Archivo CSV con nombre único
			Path rutaCSV = Paths.get(UPLOAD_DIR + nombreCSV);

			// Verificar si el archivo ya existe; si no, crear el encabezado
			boolean esNuevoArchivo = !Files.exists(rutaCSV);

			try (BufferedWriter writer = Files.newBufferedWriter(rutaCSV, StandardOpenOption.CREATE,
					StandardOpenOption.APPEND)) {

				if (esNuevoArchivo) {
					writer.write('\uFEFF'); // BOM para UTF-8
					writer.write(csvHeader()); // Escribe el encabezado solo una vez
					writer.newLine();
				}

				// Agregar los datos del formulario y el nombre del archivo
				writer.write(csvFromFormWithFilename(form, archivoNombreConHash));
				writer.newLine();
			}
			
			// Enviar correo
			try {
				String htmlCuerpo= emailSender.generarCuerpoCorreo(form);
				emailSender.enviarCorreoConAdjunto(form.getCorreoPostulacion().trim(), "Formulario Premios Inclusión 2025", htmlCuerpo,  new File(UPLOAD_DIR + nombreArchivo));
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("mensaje", "Formulario recibido con éxito.");
		return "resultado"; // tu vista de confirmación
	}

	private String csvHeader() {
		return "nombreOrganizacion,NIT,direccion,nombreGerente,sector,tamanioOrganizacion,municipio,correoElectronico,"
				+ "afiliado,comunicaciones,nombreCompletoPersona,numeroContacto,categoria,"
				+ "conflicto,discapacidad,mujeres,jovenes,migrantes,lgbtiq,otroGrupo,otroGrupoTexto,numPersonasIncluidas,"
				+ "reconocida,detalleReconocimiento,nombrePractica,razonesInclusion,practicasInclusion,"
				+ "practicasInclusivas,tiempoEstrategia,documentada,accionesPermanencia,principalesLogros,"
				+ "nombrePostula,numeroContactoPostulante,correoPostulacion,cargoPostulacion,urlVideo,urlDrive,"
				+ "datos,nombreArchivo,11- RB,12- RB,13- RB,14- RB,15- RB,16- RB,17- RB,18- RB,19- RB,20- RB,21- RB,"; // Columna
																														// adicional
																														// para
																														// el
																														// nombre
																														// del
																														// archivo
	}

	private String csvFromFormWithFilename(FormularioPremiosDTO form, String nombreArchivo) {
		return String.join(",", nullToEmpty(form.getNombreOrganizacion()), nullToEmpty(form.getNIT()),
				nullToEmpty(form.getDireccion()), nullToEmpty(form.getNombreGerente()), nullToEmpty(form.getSector()),
				nullToEmpty(form.getTamanioOrganizacion()), nullToEmpty(form.getMunicipio()),
				nullToEmpty(form.getCorreoElectronico()),
				// Nuevos campos
				nullToEmpty(form.getAfiliado()), nullToEmpty(form.getComunicaciones()),
				nullToEmpty(form.getNombreCompletoPersona()), nullToEmpty(form.getNumeroContacto()),
				nullToEmpty(form.getCategoria()),
				// Grupos poblacionales
				booleanToString(form.isConflicto()), booleanToString(form.isDiscapacidad()),
				booleanToString(form.isMujeres()), booleanToString(form.isJovenes()),
				booleanToString(form.isMigrantes()), booleanToString(form.isLgbtiq()),
				booleanToString(form.isOtroGrupo()), nullToEmpty(form.getOtroGrupoTexto()),
				integerToString(form.getNumPersonasIncluidas()), nullToEmpty(form.getReconocida()),
				nullToEmpty(form.getDetalleReconocimiento()), nullToEmpty(form.getNombrePractica()),
				nullToEmpty(form.getRazonesInclusion()), nullToEmpty(form.getPracticasInclusion()),
				nullToEmpty(form.getPracticasInclusivas()), nullToEmpty(form.getTiempoEstrategia()),
				nullToEmpty(form.getDocumentada()), nullToEmpty(form.getAccionesPermanencia()),
				nullToEmpty(form.getPrincipalesLogros()), nullToEmpty(form.getNombrePostula()),
				nullToEmpty(form.getNumeroContactoPostulante()), nullToEmpty(form.getCorreoPostulacion()),
				nullToEmpty(form.getCargoPostulacion()), nullToEmpty(form.getUrlVideo()),
				nullToEmpty(form.getUrlDrive()), booleanToString(form.isDatos()), nombreArchivo,
				nullToEmpty(form.getDimensionBienestar()), nullToEmpty(form.getOtroGrupoBienestarTexto()),
				nullToEmpty(form.getImpactoPersonas()), nullToEmpty(form.getRazonesBienestar()),
				nullToEmpty(form.getNombreEstrategiaBienestar()), nullToEmpty(form.getDescripcionEstrategia()),
				nullToEmpty(form.getTiempoYArticulacion()), nullToEmpty(form.getDocumentadaBienestar()),
				nullToEmpty(form.getAccionesImpacto()), nullToEmpty(form.getLogrosBienestar()),
				nullToEmpty(form.obtenerReconocimientoBienestar()));
	}

	private String nullToEmpty(String value) {
		return value != null ? value : "";
	}

	private String booleanToString(Boolean value) {
		return value != null && value ? "SI" : "NO";
	}

	private String integerToString(Integer value) {
		return value != null ? value.toString() : "";
	}

}
