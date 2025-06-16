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
import com.siscomputo.FormPI2025.DTO.FileInfo;
import com.siscomputo.FormPI2025.DTO.FormularioPremiosDTO;
import com.siscomputo.FormPI2025.service.EmailSender;
import com.siscomputo.FormPI2025.service.FileService;

@RestController
@RequestMapping("/api/public/premios")
public class PremioController {
	
	private static final String UPLOAD_DIR = "/logs/premios/"; 
	
	private EmailSender emailSender;
	private FileService fileService;

	public PremioController(EmailSender emailSender,FileService fileService) {
		super();
		this.emailSender = emailSender;
		this.fileService = fileService;
	}
	
	@GetMapping("/uuid")
    public ResponseEntity<String> generarUuid() {
        String uuid = UUID.randomUUID().toString();
        return ResponseEntity.ok(uuid);
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
	        @RequestPart("form") FormularioPremiosDTO form) {
		Map<String, Object> response = new HashMap<>();
	    
	    try {
	    	
	        // Aquí puedes manejar el formulario y el archivo
	       // form.setFile(file);
	        // Procesar el formulario...
	     // 1. Guardar el archivo subido
	     			String fecha = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	     			String hash = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
	     			String nombreBase = "formularioPI_Master_2025";

	     			String archivoNombreConHash = hash + "_" + fecha;
	     			// 2. Guardar archivo subido con nombre único (hash)
	     			/*MultipartFile archivo = form.getFile();
	     			String nombreArchivo = "";
	     			if (archivo != null && !archivo.isEmpty()) {
	     				String extension = getExtension(archivo.getOriginalFilename());
	     				System.out.println(form.toString());
	     				System.out.println(form.getNit());
	     				nombreArchivo = form.getNit().replaceAll("[^0-9.]", "") + "_" + archivoNombreConHash + "."
	     						+ extension;
	     				archivoNombreConHash = nombreArchivo;
	     				Path rutaArchivo = Paths.get(UPLOAD_DIR + nombreArchivo);
	     				Files.copy(archivo.getInputStream(), rutaArchivo, StandardCopyOption.REPLACE_EXISTING);
	     			} */

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
	     				//emailSender.enviarCorreoConAdjunto(form.getCorreoPostulacion().trim(), "Formulario Premios Inclusión 2025", htmlCuerpo,  new File(UPLOAD_DIR + nombreArchivo));
	     			} catch (Exception e) {
	     				e.printStackTrace();
	     			}
	        
	     			response.put("mensaje", "Formulario procesado correctamente");
	     			response.put("ok",true);
	        
	        return ResponseEntity.ok(response);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	response.put("mensaje", "Error: " + e.getMessage());
 			response.put("ok",false);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
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
		return String.join(",",
				nullToEmpty(form.getNombreOrganizacion()), 
				nullToEmpty(form.getNit()),
				nullToEmpty(form.getDireccion()),
				nullToEmpty(form.getAreaComunicaciones()),
				nullToEmpty(form.getUsoServicios()),
				nullToEmpty(form.getOrganizacionAfiliada()),
				nullToEmpty(form.getCategoriasParticipar()),
				nullToEmpty(form.getRazonesMotivan()),
				nullToEmpty(form.getContrataGP()),
				nullToEmpty(form.getNumpersonasGP())		
				
			  );
	}
	
	private String csvHeader() {
		return "nombreOrganizacion,nit,direccion,areaComunicaciones,usoServicios,organizacionAfiliada,categoriasParticipar,razonesMotivan,contrataGP,numpersonasGP"; // Columna																											// del
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
