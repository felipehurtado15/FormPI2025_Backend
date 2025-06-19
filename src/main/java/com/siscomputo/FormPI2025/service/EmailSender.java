package com.siscomputo.FormPI2025.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.siscomputo.FormPI2025.DTO.FormularioPremiosDTO;


@Service
@Component
public class EmailSender {
	public void enviarCorreoConAdjunto(String destinatario, String asunto, String htmlCuerpo, File archivoAdjunto) {

		MimeMessage message = null;
		try {
			System.out.println("destinatario: " + destinatario);
			String remitente = "info@comfenalcoantioquia.com";
			String password = "Env10M4s1v08524*$"; // Cambia esta contraseña a través de un sistema seguro
			String host = "192.168.1.46";
			String puerto = "25"; 
			
			/*String remitente = "landing.premios.2025@gmail.com";
			String password = "kzdpraedtxakvgho"; // Cambia esta contraseña a través de un sistema seguro
			String host = "smtp.gmail.com";
			String puerto = "587"; */

			// Configuración del servidor SMTP
			Properties props = new Properties();
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", puerto);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");

			// Autenticación
			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(remitente, password);
				}
			});

			// Crear el mensaje
			message = new MimeMessage(session);
			message.setFrom(new InternetAddress(remitente));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(remitente)); // Temporal

			message.addRecipient(Message.RecipientType.BCC, new InternetAddress("hugo.alarco@siscomputo.com"));
			message.addRecipient(Message.RecipientType.BCC, new InternetAddress("and.alarcon@gmail.com"));
			message.addRecipient(Message.RecipientType.BCC,new InternetAddress("mariana.arroyave@comfenalcoantioquia.com"));
			message.addRecipient(Message.RecipientType.BCC,	new InternetAddress("ferney.zabala@comfenalcoantioquia.com"));
			message.addRecipient(Message.RecipientType.BCC, new InternetAddress("juliana.rua@comfenalcoantioquia.com"));
			// message.addRecipient(Message.RecipientType.BCC, new
			// InternetAddress("diego.cadavid@comfenalcoantioquia.com"));
			// message.addRecipient(Message.RecipientType.BCC, new
			// InternetAddress("manuela.marin@comfenalcoantioquia.com"));

			message.setSubject(asunto);

			// Parte HTML
			MimeBodyPart cuerpoHTML = new MimeBodyPart();
			cuerpoHTML.setContent(htmlCuerpo, "text/html; charset=utf-8");

			// Contenedor Multipart
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(cuerpoHTML);

			// Adjuntar archivo solo si es válido
			if (archivoAdjunto != null && archivoAdjunto.exists() && archivoAdjunto.isFile()) {
				MimeBodyPart adjunto = new MimeBodyPart();
				adjunto.attachFile(archivoAdjunto);
				multipart.addBodyPart(adjunto);
			} else {
				System.out.println("Archivo adjunto no válido. El correo se enviará sin adjunto.");
			}

			message.setContent(multipart);

			// Enviar
			Transport.send(message);
			System.out.println("Correo enviado correctamente.");
		} catch (Exception e) {
			System.err.println("Error al enviar correo: " + e.getMessage());
			e.printStackTrace(System.err);
			System.err.println("Remitente: " + "masimail");
			System.err.println("Destinatario: " + destinatario);
			System.err.println("Asunto: " + asunto);
			System.err.println("Error al enviar el correo. Intentando guardar como archivo .eml...");

			try {
				// Guardar el correo como .eml
				File emlFile = new File(archivoAdjunto.getAbsolutePath() + "_" + System.currentTimeMillis() + ".eml");
				try (FileOutputStream fos = new FileOutputStream(emlFile)) {
					message.writeTo(fos);
					System.out.println("Correo guardado como archivo .eml en: " + emlFile.getAbsolutePath());
				}
			} catch (Exception emlEx) {
				System.err.println("No se pudo guardar el archivo .eml:");
				emlEx.printStackTrace();
			}

			// Imprimir información del error original
			e.printStackTrace();
		}
	}

	public String generarCuerpoCorreo(FormularioPremiosDTO dto) {
		StringBuilder html = new StringBuilder();

		html.append("<html><head>");
		html.append("<style>");
		html.append("body { font-family: Arial, sans-serif; background-color: #f9f9f9; color: #333; margin: 20px; }");
		html.append("h2 { color: #005740; border-bottom: 2px solid #005740; padding-bottom: 5px; }");
		html.append("h3 { color: #005740; margin-top: 30px; }");
		html.append("p { margin: 5px 0; }");
		html.append("strong { color: #005740; }");
		html.append("ul { margin: 5px 0 15px 20px; padding: 0; }");
		html.append(
				".section { background: #ffffff; border: 1px solid #ddd; padding: 15px 20px; border-radius: 8px; margin-bottom: 20px; box-shadow: 0 2px 4px rgba(0,0,0,0.05); }");
		html.append("</style>");
		html.append("</head><body>");

		html.append("<h2>Formulario de Postulación - Premios Inclusión</h2>");

		html.append("<div class='section'>");
		html.append("<h3>Información de la Organización</h3>");
		html.append("<p><strong>Nombre:</strong> ").append(nullToEmpty(dto.getNombreOrganizacion())).append("</p>");
		html.append("<p><strong>NIT:</strong> ").append(nullToEmpty(dto.getNIT())).append("</p>");
		html.append("<p><strong>Dirección:</strong> ").append(nullToEmpty(dto.getDireccion())).append("</p>");
		html.append("<p><strong>Municipio:</strong> ")
				.append(nullToEmpty(dto.getMunicipio()).equalsIgnoreCase("OTRO MUNICIPIO")
						? nullToEmpty(dto.getOtroMunicipio())
						: nullToEmpty(dto.getMunicipio()))
				.append("</p>");
		html.append("<p><strong>Nombre del Gerente:</strong> ").append(nullToEmpty(dto.getNombreGerente()))
				.append("</p>");
		html.append("<p><strong>Sector:</strong> ").append(nullToEmpty(dto.getSector())).append("</p>");
		html.append("<p><strong>Otro sector:</strong> ").append(nullToEmpty(dto.getOtroSector())).append("</p>");
		html.append("<p><strong>Tamaño de la organización:</strong> ").append(nullToEmpty(dto.getTamanioOrganizacion()))
				.append("</p>");
		html.append("<p><strong>Afiliado a ANDI:</strong> ").append(nullToEmpty(dto.getAfiliado())).append("</p>");
		html.append("<p><strong>Desea recibir comunicaciones:</strong> ").append(nullToEmpty(dto.getComunicaciones()))
				.append("</p>");
		html.append("</div>");

		html.append("<div class='section'>");
		html.append("<h3>Datos de contacto</h3>");
		html.append("<p><strong>Nombre completo:</strong> ").append(nullToEmpty(dto.getNombreCompletoPersona()))
				.append("</p>");
		html.append("<p><strong>Teléfono:</strong> ").append(nullToEmpty(dto.getNumeroContacto())).append("</p>");
		html.append("<p><strong>Correo electrónico:</strong> ").append(nullToEmpty(dto.getCorreoElectronico()))
				.append("</p>");
		html.append("</div>");

		html.append("<div class='section'>");
		html.append("<h3>Categoría y Grupos Poblacionales</h3>");
		html.append("<p><strong>Categoría:</strong> ").append(nullToEmpty(dto.getCategoria())).append("</p>");
		html.append("<p><strong>Grupos poblacionales incluidos:</strong><ul>");
		if (Boolean.TRUE.equals(dto.isConflicto()))
			html.append("<li>Víctimas del conflicto</li>");
		if (Boolean.TRUE.equals(dto.isDiscapacidad()))
			html.append("<li>Personas con discapacidad</li>");
		if (Boolean.TRUE.equals(dto.isMujeres()))
			html.append("<li>Mujeres</li>");
		if (Boolean.TRUE.equals(dto.isJovenes()))
			html.append("<li>Jóvenes</li>");
		if (Boolean.TRUE.equals(dto.isMigrantes()))
			html.append("<li>Migrantes</li>");
		if (Boolean.TRUE.equals(dto.isLgbtiq()))
			html.append("<li>LGBTIQ+</li>");
		if (Boolean.TRUE.equals(dto.isOtroGrupo()))
			html.append("<li>Otro: ").append(nullToEmpty(dto.getOtroGrupoTexto())).append("</li>");
		html.append("</ul></p>");
		html.append("<p><strong>Número de personas incluidas:</strong> ")
				.append(nullToEmpty(dto.getNumPersonasIncluidas())).append("</p>");
		html.append("</div>");

		html.append("<div class='section'>");
		html.append("<h3>Reconocimiento</h3>");
		html.append("<p><strong>¿Ha sido reconocida?</strong> ").append(nullToEmpty(dto.getReconocida()))
				.append("</p>");
		html.append("<p><strong>Detalle del reconocimiento:</strong> ")
				.append(nullToEmpty(dto.getDetalleReconocimiento())).append("</p>");
		html.append("</div>");

		html.append("<div class='section'>");
		html.append("<h3>Prácticas de Inclusión</h3>");
		html.append("<p><strong>Nombre de la práctica:</strong> ").append(nullToEmpty(dto.getNombrePractica()))
				.append("</p>");
		html.append("<p><strong>Razones de inclusión:</strong> ").append(nullToEmpty(dto.getRazonesInclusion()))
				.append("</p>");
		html.append("<p><strong>Prácticas de inclusión:</strong> ").append(nullToEmpty(dto.getPracticasInclusion()))
				.append("</p>");
		html.append("<p><strong>Acciones inclusivas:</strong> ").append(nullToEmpty(dto.getPracticasInclusivas()))
				.append("</p>");
		html.append("<p><strong>Tiempo de implementación:</strong> ").append(nullToEmpty(dto.getTiempoEstrategia()))
				.append("</p>");
		html.append("<p><strong>¿Documentada?</strong> ").append(nullToEmpty(dto.getDocumentada())).append("</p>");
		html.append("<p><strong>Acciones para permanencia:</strong> ").append(nullToEmpty(dto.getAccionesPermanencia()))
				.append("</p>");
		html.append("<p><strong>Principales logros:</strong> ").append(nullToEmpty(dto.getPrincipalesLogros()))
				.append("</p>");
		html.append("</div>");

		html.append("<div class='section'>");
		html.append("<h3>Datos del postulante</h3>");
		html.append("<p><strong>Nombre:</strong> ").append(nullToEmpty(dto.getNombrePostula())).append("</p>");
		html.append("<p><strong>Contacto:</strong> ").append(nullToEmpty(dto.getNumeroContactoPostulante()))
				.append("</p>");
		html.append("<p><strong>Correo:</strong> ").append(nullToEmpty(dto.getCorreoPostulacion())).append("</p>");
		html.append("<p><strong>Cargo:</strong> ").append(nullToEmpty(dto.getCargoPostulacion())).append("</p>");
		html.append("<p><strong>Video (URL):</strong> ").append(nullToEmpty(dto.getUrlVideo())).append("</p>");
		html.append("<p><strong>Documentación en Drive:</strong> ").append(nullToEmpty(dto.getUrlDrive()))
				.append("</p>");
		html.append("<p><strong>Autorización uso de datos:</strong> ").append(dto.isDatos() ? "Sí" : "No")
				.append("</p>");
		html.append("</div>");

		html.append("<div class='section'>");
		html.append("<h3>Sección Bienestar</h3>");
		html.append("<p><strong>Dimensión de bienestar:</strong> ").append(nullToEmpty(dto.getDimensionBienestar()))
				.append("</p>");
		html.append("<p><strong>Grupos objetivo:</strong><ul>");
		if (dto.isEmpleados())
			html.append("<li>Empleados</li>");
		if (dto.isAprendices())
			html.append("<li>Aprendices</li>");
		if (dto.isFamilias())
			html.append("<li>Familias</li>");
		if (dto.isProveedores())
			html.append("<li>Proveedores</li>");
		if (dto.isComunidad())
			html.append("<li>Comunidad</li>");
		if (dto.isOtroGrupoBienestar())
			html.append("<li>Otro: ").append(nullToEmpty(dto.getOtroGrupoBienestarTexto())).append("</li>");
		html.append("</ul></p>");
		html.append("<p><strong>Impacto en personas:</strong> ").append(nullToEmpty(dto.getImpactoPersonas()))
				.append("</p>");
		html.append("<p><strong>Razones:</strong> ").append(nullToEmpty(dto.getRazonesBienestar())).append("</p>");
		html.append("<p><strong>Nombre de estrategia:</strong> ")
				.append(nullToEmpty(dto.getNombreEstrategiaBienestar())).append("</p>");
		html.append("<p><strong>Descripción de estrategia:</strong> ")
				.append(nullToEmpty(dto.getDescripcionEstrategia())).append("</p>");
		html.append("<p><strong>Tiempo y articulación:</strong> ").append(nullToEmpty(dto.getTiempoYArticulacion()))
				.append("</p>");
		html.append("<p><strong>¿Documentada?</strong> ").append(nullToEmpty(dto.getDocumentadaBienestar()))
				.append("</p>");
		html.append("<p><strong>Acciones de impacto:</strong> ").append(nullToEmpty(dto.getAccionesImpacto()))
				.append("</p>");
		html.append("<p><strong>Logros:</strong> ").append(nullToEmpty(dto.getLogrosBienestar())).append("</p>");
		html.append("<p><strong>¿Reconocida?</strong> ").append(nullToEmpty(dto.getReconocidaBienestar()))
				.append("</p>");
		html.append("<p><strong>Detalles del reconocimiento:</strong> ")
				.append(nullToEmpty(dto.getDetalleReconocimientoBienestar())).append("</p>");
		html.append("</div>");

		html.append("</body></html>");

		return html.toString();
	}

	private String nullToEmpty(String value) {
		return value != null ? value : "";
	}

	private String nullToEmpty(Integer value) {
		return value != null ? value.toString() : "";
	}

}
