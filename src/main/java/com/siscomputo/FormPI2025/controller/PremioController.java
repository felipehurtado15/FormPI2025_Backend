package com.siscomputo.FormPI2025.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import static org.apache.commons.compress.utils.FileNameUtils.getExtension;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.siscomputo.FormPI2025.DTO.DatosForm;
import com.siscomputo.FormPI2025.DTO.FileInfo;
import com.siscomputo.FormPI2025.DTO.FormularioPremiosDTO;
import com.siscomputo.FormPI2025.service.EmailSender;
import com.siscomputo.FormPI2025.service.FileService;

@RestController
@RequestMapping("/api/public/premios")
public class PremioController {
	
	private static final String UPLOAD_DIR = "D:/imagenes/formularios/"; 
	
	private EmailSender emailSender;
	private FileService fileService;

	public PremioController(EmailSender emailSender,FileService fileService) {
		super();
		this.emailSender = emailSender;
		this.fileService = fileService;
	}
	
	 @GetMapping("/listar")
	    public ResponseEntity<List<FileInfo>> listarArchivos() {
	        try {
	            List<FileInfo> archivos = fileService.obtenerArchivos(UPLOAD_DIR);
	            return ResponseEntity.ok(archivos);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.internalServerError().build();
	        }
	    }

	
	@PostMapping(value = "/enviarFormulario", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> procesarFormulario(
	        @RequestPart("form") FormularioPremiosDTO form,
	        @RequestPart(value = "file", required = false) MultipartFile file) {
	    try {
	    	
	        // Aquí puedes manejar el formulario y el archivo
	        form.setFile(file);
	        // Procesar el formulario...
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
	     				System.out.println(form.toString());
	     				System.out.println(form.getNIT());
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
	        
	        
	        
	        return ResponseEntity.ok("Formulario procesado correctamente");
	    } catch (Exception e) {
	    	e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
	    }
	}
	
	@GetMapping("/descargar/{nombreArchivo}")
    public ResponseEntity<Resource> descargarArchivo(@PathVariable String nombreArchivo) {
        try {
            Resource resource = fileService.descargarArchivo(nombreArchivo,UPLOAD_DIR);
            
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, 
                           "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
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
	
	private String csvHeader() {
		return "nombreOrganizacion,NIT,direccion,nombreGerente,sector,tamanioOrganizacion,municipio,correoElectronico,"
				+ "afiliado,comunicaciones,nombreCompletoPersona,numeroContacto,categoria,"
				+ "conflicto,discapacidad,mujeres,jovenes,migrantes,lgbtiq,otroGrupo,otroGrupoTexto,numPersonasIncluidas,"
				+ "reconocida,detalleReconocimiento,nombrePractica,razonesInclusion,practicasInclusion,"
				+ "practicasInclusivas,tiempoEstrategia,documentada,accionesPermanencia,principalesLogros,"
				+ "nombrePostula,numeroContactoPostulante,correoPostulacion,cargoPostulacion,urlVideo,urlDrive,"
				+ "datos,nombreArchivo,11- RB,12- RB,13- RB,14- RB,15- RB,16- RB,17- RB,18- RB,19- RB,20- RB,21- RB,"; // Columna																											// del
																														// archivo
	}

	private String nullToEmpty(String value) {
		String rta = value != null ? value : "";
		return rta.replace(",", " ");
	}

	private String booleanToString(Boolean value) {
		return value != null && value ? "SI" : "NO";
	}

	private String integerToString(Integer value) {
		return value != null ? value.toString() : "";
	}
	
}
