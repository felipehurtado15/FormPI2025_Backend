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

import com.siscomputo.FormPI2025.DTO.FileInfo;

@Service
@Component
public class FileService {

	public List<FileInfo> obtenerArchivos(String UPLOAD_DIR) throws IOException {
		String nombreBase = "generacionesPI_Master_2025";
		String nombreCSV = nombreBase + ".csv";
		Path rutaCSV = Paths.get(UPLOAD_DIR + nombreCSV);
		List<FileInfo> archivos = new ArrayList<>();
		String separador = ",";

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");

		// Agregar el CSV como archivo principal
		if (Files.exists(rutaCSV) && Files.isRegularFile(rutaCSV)) {
			BasicFileAttributes attrs = Files.readAttributes(rutaCSV, BasicFileAttributes.class);
			String fechaCSV = sdf.format(attrs.creationTime().toMillis());
			FileInfo principal = new FileInfo();
			principal.setNombre(nombreCSV);
			principal.setFechaCreacion(fechaCSV);
			principal.setNit("");
			principal.setNombre(nombreCSV);
			archivos.add(principal);
		}

		// Leer el contenido del CSV
		try (BufferedReader br = new BufferedReader(new FileReader(rutaCSV.toString()))) {
			String linea;
			boolean primeraLinea = true;

			while ((linea = br.readLine()) != null) {
				if (primeraLinea) {
					primeraLinea = false;
					continue;
				}

				String[] columnas = linea.split(separador);
				
				String organizacion = columnas[0];
				String nit = columnas[1];
				String direccion = columnas[2];
				String fechaCreacion = "";
				
				FileInfo info = new FileInfo();
				info.setFechaCreacion(fechaCreacion);
				info.setOrganizacion(organizacion);
				info.setNit(nit);
				info.setDireccion(direccion);
				info.setNombre("");
				
				archivos.add(info);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Ordenar por fecha descendente (más reciente primero)
		archivos.sort((a1, a2) -> {
			try {
				Date d1 = sdf.parse(a1.getFechaCreacion());
				Date d2 = sdf.parse(a2.getFechaCreacion());
				return d2.compareTo(d1); // Descendente
			} catch (Exception e) {
				return 0; // Si falla el parseo, no cambiar el orden
			}
		});

		return archivos;
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
