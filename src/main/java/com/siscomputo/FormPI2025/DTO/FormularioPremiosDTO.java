package com.siscomputo.FormPI2025.DTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FormularioPremiosDTO {
	 private String nombreOrganizacion;
	 
	   @JsonProperty("NIT")
	    private String NIT;
	    private String direccion;
	    private String nombreGerente;
	    private String sector;
	    private String otroSector;
	    private String tamanioOrganizacion;
	    private String municipio;
	    private String otroMunicipio;

	    private String afiliado; // SI / NO
	    private String comunicaciones; // SI / NO
	    private String nombreCompletoPersona;
	    private String numeroContacto;
	    private String correoElectronico;

	    private String categoria;

	    // Grupos poblacionales (checkboxes)
	    private Boolean conflicto;
	    private Boolean discapacidad;
	    private Boolean mujeres;
	    private Boolean jovenes;
	    private Boolean migrantes;
	    private Boolean lgbtiq;
	    private Boolean otroGrupo;
	    private String otroGrupoTexto;

	    private Integer numPersonasIncluidas;

	    private String reconocida; // SI / NO
	    private String detalleReconocimiento;

	    private String nombrePractica;
	    private String razonesInclusion;
	    private String practicasInclusion;
	    private String practicasInclusivas;
	    private String tiempoEstrategia;

	    private String documentada; // SI / NO

	    private String accionesPermanencia;
	    private String principalesLogros;

	    // Datos del postulante
	    private String nombrePostula;
	    private String numeroContactoPostulante;
	    private String correoPostulacion;
	    private String cargoPostulacion;
	    private String urlVideo;
	    private String urlDrive;

	    private Boolean datos; // Checkbox de uso de datos

	    private MultipartFile file;
	    
	    
	    // Datos de para la segunda sección de preguntas
	    // Pregunta 11
	    private String dimensionBienestar;

	    // Pregunta 12 - Checkboxes
	    private boolean empleados;
	    private boolean aprendices;
	    private boolean familias;
	    private boolean proveedores;
	    private boolean comunidad;
	    private boolean otroGrupoBienestar;
	    private String otroGrupoBienestarTexto;

	    // Pregunta 13
	    private String impactoPersonas;

	    // Pregunta 14
	    private String razonesBienestar;

	    // Pregunta 15
	    private String nombreEstrategiaBienestar;

	    // Pregunta 16
	    private String descripcionEstrategia;

	    // Pregunta 17
	    private String tiempoYArticulacion;

	    // Pregunta 18 - Radio buttons
	    private String documentadaBienestar; // valores posibles: "SI", "NO"

	    // Pregunta 19
	    private String accionesImpacto;

	    // Pregunta 20
	    private String logrosBienestar;

	    // Pregunta 21 - Radio buttons
	    private String reconocidaBienestar; // valores posibles: "SI", "NO"
	    private String detalleReconocimientoBienestar;
	    
	    
	    public String getNombreOrganizacion() {
	        return nombreOrganizacion;
	    }

	    public void setNombreOrganizacion(String nombreOrganizacion) {
	        this.nombreOrganizacion = nombreOrganizacion;
	    }

	    
		public String getNIT() {
			return NIT;
		}

		public void setNIT(String nIT) {
			NIT = nIT;
		}

		public String getDireccion() {
	        return direccion;
	    }

	    public void setDireccion(String direccion) {
	        this.direccion = direccion;
	    }

	    public String getNombreGerente() {
	        return nombreGerente;
	    }

	    public void setNombreGerente(String nombreGerente) {
	        this.nombreGerente = nombreGerente;
	    }

	    public String getSector() {
	        return sector;
	    }

	    public void setSector(String sector) {
	        this.sector = sector;
	    }

	    public String getOtroSector() {
	        return otroSector;
	    }

	    public void setOtroSector(String otroSector) {
	        this.otroSector = otroSector;
	    }

	    public String getTamanioOrganizacion() {
	        return tamanioOrganizacion;
	    }

	    public void setTamanioOrganizacion(String tamanioOrganizacion) {
	        this.tamanioOrganizacion = tamanioOrganizacion;
	    }

	    public String getMunicipio() {
	        return municipio;
	    }

	    public void setMunicipio(String municipio) {
	        this.municipio = municipio;
	    }

	    public String getAfiliado() {
	        return afiliado;
	    }

	    public void setAfiliado(String afiliado) {
	        this.afiliado = afiliado;
	    }

	    public String getComunicaciones() {
	        return comunicaciones;
	    }

	    public void setComunicaciones(String comunicaciones) {
	        this.comunicaciones = comunicaciones;
	    }

	    public String getNombreCompletoPersona() {
	        return nombreCompletoPersona;
	    }

	    public void setNombreCompletoPersona(String nombreCompletoPersona) {
	        this.nombreCompletoPersona = nombreCompletoPersona;
	    }

	    public String getNumeroContacto() {
	        return numeroContacto;
	    }

	    public void setNumeroContacto(String numeroContacto) {
	        this.numeroContacto = numeroContacto;
	    }

	    public String getCorreoElectronico() {
	        return correoElectronico;
	    }

	    public void setCorreoElectronico(String correoElectronico) {
	        this.correoElectronico = correoElectronico;
	    }

	    public String getCategoria() {
	        return categoria;
	    }

	    public void setCategoria(String categoria) {
	        this.categoria = categoria;
	    }

	    public Boolean isConflicto() {
	        return conflicto;
	    }

	    public void setConflicto(Boolean conflicto) {
	        this.conflicto = conflicto;
	    }

	    public Boolean isDiscapacidad() {
	        return discapacidad;
	    }

	    public void setDiscapacidad(Boolean discapacidad) {
	        this.discapacidad = discapacidad;
	    }

	    public Boolean isMujeres() {
	        return mujeres;
	    }

	    public void setMujeres(Boolean mujeres) {
	        this.mujeres = mujeres;
	    }

	    public Boolean isJovenes() {
	        return jovenes;
	    }

	    public void setJovenes(Boolean jovenes) {
	        this.jovenes = jovenes;
	    }

	    public Boolean isMigrantes() {
	        return migrantes;
	    }

	    public void setMigrantes(Boolean migrantes) {
	        this.migrantes = migrantes;
	    }

	    public Boolean isLgbtiq() {
	        return lgbtiq;
	    }

	    public void setLgbtiq(Boolean lgbtiq) {
	        this.lgbtiq = lgbtiq;
	    }

	    public Boolean isOtroGrupo() {
	        return otroGrupo;
	    }

	    public void setOtroGrupo(Boolean otroGrupo) {
	        this.otroGrupo = otroGrupo;
	    }

	    public String getOtroGrupoTexto() {
	        return otroGrupoTexto;
	    }

	    public void setOtroGrupoTexto(String otroGrupoTexto) {
	        this.otroGrupoTexto = otroGrupoTexto;
	    }

	    public Integer getNumPersonasIncluidas() {
	        return numPersonasIncluidas;
	    }

	    public void setNumPersonasIncluidas(Integer numPersonasIncluidas) {
	        this.numPersonasIncluidas = numPersonasIncluidas;
	    }

	    public String getReconocida() {
	        return reconocida;
	    }

	    public void setReconocida(String reconocida) {
	        this.reconocida = reconocida;
	    }

	    public String getDetalleReconocimiento() {
	        return detalleReconocimiento;
	    }

	    public void setDetalleReconocimiento(String detalleReconocimiento) {
	        this.detalleReconocimiento = detalleReconocimiento;
	    }

	    public String getNombrePractica() {
	        return nombrePractica;
	    }

	    public void setNombrePractica(String nombrePractica) {
	        this.nombrePractica = nombrePractica;
	    }

	    public String getRazonesInclusion() {
	        return razonesInclusion;
	    }

	    public void setRazonesInclusion(String razonesInclusion) {
	        this.razonesInclusion = razonesInclusion;
	    }

	    public String getPracticasInclusion() {
	        return practicasInclusion;
	    }

	    public void setPracticasInclusion(String practicasInclusion) {
	        this.practicasInclusion = practicasInclusion;
	    }

	    public String getPracticasInclusivas() {
	        return practicasInclusivas;
	    }

	    public void setPracticasInclusivas(String practicasInclusivas) {
	        this.practicasInclusivas = practicasInclusivas;
	    }

	    public String getTiempoEstrategia() {
	        return tiempoEstrategia;
	    }

	    public void setTiempoEstrategia(String tiempoEstrategia) {
	        this.tiempoEstrategia = tiempoEstrategia;
	    }

	    public String getDocumentada() {
	        return documentada;
	    }

	    public void setDocumentada(String documentada) {
	        this.documentada = documentada;
	    }

	    public String getAccionesPermanencia() {
	        return accionesPermanencia;
	    }

	    public void setAccionesPermanencia(String accionesPermanencia) {
	        this.accionesPermanencia = accionesPermanencia;
	    }

	    public String getPrincipalesLogros() {
	        return principalesLogros;
	    }

	    public void setPrincipalesLogros(String principalesLogros) {
	        this.principalesLogros = principalesLogros;
	    }

	    public String getNombrePostula() {
	        return nombrePostula;
	    }

	    public void setNombrePostula(String nombrePostula) {
	        this.nombrePostula = nombrePostula;
	    }

	    public String getNumeroContactoPostulante() {
	        return numeroContactoPostulante;
	    }

	    public void setNumeroContactoPostulante(String numeroContactoPostulante) {
	        this.numeroContactoPostulante = numeroContactoPostulante;
	    }

	    public String getCorreoPostulacion() {
	        return correoPostulacion;
	    }

	    public void setCorreoPostulacion(String correoPostulacion) {
	        this.correoPostulacion = correoPostulacion;
	    }

	    public String getCargoPostulacion() {
	        return cargoPostulacion;
	    }

	    public void setCargoPostulacion(String cargoPostulacion) {
	        this.cargoPostulacion = cargoPostulacion;
	    }

	    public String getUrlVideo() {
	        return urlVideo;
	    }

	    public void setUrlVideo(String urlVideo) {
	        this.urlVideo = urlVideo;
	    }

	    public String getUrlDrive() {
	        return urlDrive;
	    }

	    public void setUrlDrive(String urlDrive) {
	        this.urlDrive = urlDrive;
	    }

	    public Boolean isDatos() {
	        return datos;
	    }

	    public void setDatos(Boolean datos) {
	        this.datos = datos;
	    }

	    public MultipartFile getFile() {
	        return file;
	    }

	    public void setFile(MultipartFile file) {
	        this.file = file;
	    }

		public String getDimensionBienestar() {
			return dimensionBienestar;
		}

		public void setDimensionBienestar(String dimensionBienestar) {
			this.dimensionBienestar = dimensionBienestar;
		}

		public boolean isEmpleados() {
			return empleados;
		}

		public void setEmpleados(boolean empleados) {
			this.empleados = empleados;
		}

		public boolean isAprendices() {
			return aprendices;
		}

		public void setAprendices(boolean aprendices) {
			this.aprendices = aprendices;
		}

		public boolean isFamilias() {
			return familias;
		}

		public void setFamilias(boolean familias) {
			this.familias = familias;
		}

		public boolean isProveedores() {
			return proveedores;
		}

		public void setProveedores(boolean proveedores) {
			this.proveedores = proveedores;
		}

		public boolean isComunidad() {
			return comunidad;
		}

		public void setComunidad(boolean comunidad) {
			this.comunidad = comunidad;
		}

		public boolean isOtroGrupoBienestar() {
			return otroGrupoBienestar;
		}

		public void setOtroGrupoBienestar(boolean otroGrupoBienestar) {
			this.otroGrupoBienestar = otroGrupoBienestar;
		}

		public String getOtroGrupoBienestarTexto() {
			return otroGrupoBienestarTexto;
		}

		public void setOtroGrupoBienestarTexto(String otroGrupoBienestarTexto) {
			this.otroGrupoBienestarTexto = otroGrupoBienestarTexto;
		}

		public String getImpactoPersonas() {
			return impactoPersonas;
		}

		public void setImpactoPersonas(String impactoPersonas) {
			this.impactoPersonas = impactoPersonas;
		}

		public String getRazonesBienestar() {
			return razonesBienestar;
		}

		public void setRazonesBienestar(String razonesBienestar) {
			this.razonesBienestar = razonesBienestar;
		}

		public String getNombreEstrategiaBienestar() {
			return nombreEstrategiaBienestar;
		}

		public void setNombreEstrategiaBienestar(String nombreEstrategiaBienestar) {
			this.nombreEstrategiaBienestar = nombreEstrategiaBienestar;
		}

		public String getDescripcionEstrategia() {
			return descripcionEstrategia;
		}

		public void setDescripcionEstrategia(String descripcionEstrategia) {
			this.descripcionEstrategia = descripcionEstrategia;
		}

		public String getTiempoYArticulacion() {
			return tiempoYArticulacion;
		}

		public void setTiempoYArticulacion(String tiempoYArticulacion) {
			this.tiempoYArticulacion = tiempoYArticulacion;
		}

		public String getDocumentadaBienestar() {
			return documentadaBienestar;
		}

		public void setDocumentadaBienestar(String documentadaBienestar) {
			this.documentadaBienestar = documentadaBienestar;
		}

		public String getAccionesImpacto() {
			return accionesImpacto;
		}

		public void setAccionesImpacto(String accionesImpacto) {
			this.accionesImpacto = accionesImpacto;
		}

		public String getLogrosBienestar() {
			return logrosBienestar;
		}

		public void setLogrosBienestar(String logrosBienestar) {
			this.logrosBienestar = logrosBienestar;
		}

		public String getReconocidaBienestar() {
			return reconocidaBienestar;
		}

		public void setReconocidaBienestar(String reconocidaBienestar) {
			this.reconocidaBienestar = reconocidaBienestar;
		}

		public String getDetalleReconocimientoBienestar() {
			return detalleReconocimientoBienestar;
		}

		public void setDetalleReconocimientoBienestar(String detalleReconocimientoBienestar) {
			this.detalleReconocimientoBienestar = detalleReconocimientoBienestar;
		}

		public Boolean getConflicto() {
			return conflicto;
		}

		public Boolean getDiscapacidad() {
			return discapacidad;
		}

		public Boolean getMujeres() {
			return mujeres;
		}

		public Boolean getJovenes() {
			return jovenes;
		}

		public Boolean getMigrantes() {
			return migrantes;
		}

		public Boolean getLgbtiq() {
			return lgbtiq;
		}

		public Boolean getOtroGrupo() {
			return otroGrupo;
		}

		public Boolean getDatos() {
			return datos;
		}
		
		public String getOtroMunicipio() {
			return otroMunicipio;
		}

		public void setOtroMunicipio(String otroMunicipio) {
			this.otroMunicipio = otroMunicipio;
		}

		// Opciones en la pregunta 12 RV
		public String obtenerGruposSeleccionados() {
		    List<String> grupos = new ArrayList<>();

		    if (isEmpleados()) {
		        grupos.add("Empleados");
		    }
		    if (isAprendices()) {
		        grupos.add("Aprendices");
		    }
		    if (isFamilias()) {
		        grupos.add("Familias");
		    }
		    if (isProveedores()) {
		        grupos.add("Proveedores");
		    }
		    if (isComunidad()) {
		        grupos.add("Comunidad");
		    }
		    if (isOtroGrupoBienestar() && getOtroGrupoBienestarTexto() != null && !getOtroGrupoBienestarTexto().isEmpty()) {
		        grupos.add(getOtroGrupoBienestarTexto());
		    }

		    return String.join(", ", grupos);
		}
		
		public String obtenerReconocimientoBienestar() {
		    if ("SI".equalsIgnoreCase(getReconocidaBienestar()) && getDetalleReconocimientoBienestar() != null && !getDetalleReconocimientoBienestar().isEmpty()) {
		        return getDetalleReconocimientoBienestar();
		    }
		    return "NO";
		}

		@Override
		public String toString() {
			return "FormularioPremiosDTO [nombreOrganizacion=" + nombreOrganizacion + ", NIT=" + NIT + ", direccion="
					+ direccion + ", nombreGerente=" + nombreGerente + ", sector=" + sector + ", otroSector="
					+ otroSector + ", tamanioOrganizacion=" + tamanioOrganizacion + ", municipio=" + municipio
					+ ", afiliado=" + afiliado + ", comunicaciones=" + comunicaciones + ", nombreCompletoPersona="
					+ nombreCompletoPersona + ", numeroContacto=" + numeroContacto + ", correoElectronico="
					+ correoElectronico + ", categoria=" + categoria + ", conflicto=" + conflicto + ", discapacidad="
					+ discapacidad + ", mujeres=" + mujeres + ", jovenes=" + jovenes + ", migrantes=" + migrantes
					+ ", lgbtiq=" + lgbtiq + ", otroGrupo=" + otroGrupo + ", otroGrupoTexto=" + otroGrupoTexto
					+ ", numPersonasIncluidas=" + numPersonasIncluidas + ", reconocida=" + reconocida
					+ ", detalleReconocimiento=" + detalleReconocimiento + ", nombrePractica=" + nombrePractica
					+ ", razonesInclusion=" + razonesInclusion + ", practicasInclusion=" + practicasInclusion
					+ ", practicasInclusivas=" + practicasInclusivas + ", tiempoEstrategia=" + tiempoEstrategia
					+ ", documentada=" + documentada + ", accionesPermanencia=" + accionesPermanencia
					+ ", principalesLogros=" + principalesLogros + ", nombrePostula=" + nombrePostula
					+ ", numeroContactoPostulante=" + numeroContactoPostulante + ", correoPostulacion="
					+ correoPostulacion + ", cargoPostulacion=" + cargoPostulacion + ", urlVideo=" + urlVideo
					+ ", urlDrive=" + urlDrive + ", datos=" + datos + ", file=" + file + ", dimensionBienestar="
					+ dimensionBienestar + ", empleados=" + empleados + ", aprendices=" + aprendices + ", familias="
					+ familias + ", proveedores=" + proveedores + ", comunidad=" + comunidad + ", otroGrupoBienestar="
					+ otroGrupoBienestar + ", otroGrupoBienestarTexto=" + otroGrupoBienestarTexto + ", impactoPersonas="
					+ impactoPersonas + ", razonesBienestar=" + razonesBienestar + ", nombreEstrategiaBienestar="
					+ nombreEstrategiaBienestar + ", descripcionEstrategia=" + descripcionEstrategia
					+ ", tiempoYArticulacion=" + tiempoYArticulacion + ", documentadaBienestar=" + documentadaBienestar
					+ ", accionesImpacto=" + accionesImpacto + ", logrosBienestar=" + logrosBienestar
					+ ", reconocidaBienestar=" + reconocidaBienestar + ", detalleReconocimientoBienestar="
					+ detalleReconocimientoBienestar + "]";
		}
		
		
		
}
