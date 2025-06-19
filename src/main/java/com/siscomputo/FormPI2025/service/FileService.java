package com.siscomputo.FormPI2025.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.siscomputo.FormPI2025.DTO.FileInfo;

@Service
@Component
public class FileService {

	 public List<FileInfo> obtenerArchivosConOpenCSV(String UPLOAD_DIR) throws IOException {
	        String nombreBase = "formularioPI_Master_2025";
	        String nombreCSV = nombreBase + ".csv";
	        Path rutaCSV = Paths.get(UPLOAD_DIR + nombreCSV);
	        List<FileInfo> archivos = new ArrayList<>();

	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");

	        // Agregar el CSV como archivo principal
	        if (Files.exists(rutaCSV) && Files.isRegularFile(rutaCSV)) {
	            BasicFileAttributes attrs = Files.readAttributes(rutaCSV, BasicFileAttributes.class);
	            String fechaCSV = sdf.format(attrs.creationTime().toMillis());
	            archivos.add(new FileInfo(nombreCSV, fechaCSV, "", "Archivo maestro", "CSV principal"));
	        }

	        // Usar OpenCSV para leer el archivo
	        try (CSVReader reader = new CSVReader(new FileReader(rutaCSV.toString()))) {
	            List<String[]> todasLasFilas = reader.readAll();
	            
	            // Saltar la primera fila (encabezados)
	            for (int i = 1; i < todasLasFilas.size(); i++) {
	                String[] columnas = todasLasFilas.get(i);
	                
	                // Validar que tenemos suficientes columnas
	                if (columnas.length < 40) {
	                    System.err.println("Fila " + (i+1) + " con columnas insuficientes: " + columnas.length);
	                    continue;
	                }

	                String organizacion = obtenerValorSeguro(columnas, 0);
	                String nit = obtenerValorSeguro(columnas, 1);
	                String postulante = obtenerValorSeguro(columnas, 32);
	                String nombreArchivoBuscado = obtenerValorSeguro(columnas, 39);

	                if (nombreArchivoBuscado.trim().isEmpty()) {
	                    continue;
	                }

	                Path archivoPath = Paths.get(UPLOAD_DIR + nombreArchivoBuscado);
	                String fechaCreacion = "";

	                if (Files.exists(archivoPath) && Files.isRegularFile(archivoPath)) {
	                    BasicFileAttributes attrs = Files.readAttributes(archivoPath, BasicFileAttributes.class);
	                    fechaCreacion = sdf.format(attrs.creationTime().toMillis());
	                } else {
	                    fechaCreacion = extraerFechaDelNombre(nombreArchivoBuscado, sdf);
	                }

	                archivos.add(new FileInfo(nombreArchivoBuscado, fechaCreacion, nit, organizacion, postulante));
	            }
	        } catch (CsvException e) {
	            System.err.println("Error procesando CSV: " + e.getMessage());
	            e.printStackTrace();
	        }

	        // Ordenar por fecha
	        archivos.sort((a1, a2) -> {
	            try {
	                if (a1.getFechaCreacion().isEmpty() && a2.getFechaCreacion().isEmpty()) return 0;
	                if (a1.getFechaCreacion().isEmpty()) return 1;
	                if (a2.getFechaCreacion().isEmpty()) return -1;
	                
	                Date d1 = sdf.parse(a1.getFechaCreacion());
	                Date d2 = sdf.parse(a2.getFechaCreacion());
	                return d2.compareTo(d1);
	            } catch (Exception e) {
	                return 0;
	            }
	        });

	        return archivos;
	    }
	    
	    private String obtenerValorSeguro(String[] array, int indice) {
	        return (indice >= 0 && indice < array.length && array[indice] != null) 
	               ? array[indice].trim() 
	               : "";
	    }
	    
	    private String extraerFechaDelNombre(String nombreArchivo, SimpleDateFormat sdf) {
	        String[] partes = nombreArchivo.split("_");
	        if (partes.length > 1) {
	            String fechaStr = partes[1];
	            if (fechaStr.matches("\\d{14}")) {
	                try {
	                    SimpleDateFormat entrada = new SimpleDateFormat("yyyyMMddHHmmss");
	                    Date fecha = entrada.parse(fechaStr);
	                    return sdf.format(fecha);
	                } catch (ParseException e) {
	                    System.err.println("Error parseando fecha: " + fechaStr);
	                }
	            }
	        }
	        return "";
	    }
	public Resource descargarArchivo(String nombreArchivo, String UPLOAD_DIR) throws IOException {
		Path filePath = Paths.get(UPLOAD_DIR).resolve(nombreArchivo).normalize();
		Resource resource = new UrlResource(filePath.toUri());

		if (resource.exists()) {
			return resource;
		} else {
			throw new IOException("Archivo no encontrado: " + nombreArchivo);
		}
	}
}
