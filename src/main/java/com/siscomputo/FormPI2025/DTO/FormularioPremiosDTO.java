package com.siscomputo.FormPI2025.DTO;


public class FormularioPremiosDTO {
	private String nombreOrganizacion;
	private String nit;
	private String direccion;
	
	private String organizacionAfiliada;
	private String usoServicios;
	private String areaComunicaciones;
	

	public String getNombreOrganizacion() {
		return nombreOrganizacion;
	}

	public void setNombreOrganizacion(String nombreOrganizacion) {
		this.nombreOrganizacion = nombreOrganizacion;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getOrganizacionAfiliada() {
		return organizacionAfiliada;
	}

	public void setOrganizacionAfiliada(String organizacionAfiliada) {
		this.organizacionAfiliada = organizacionAfiliada;
	}

	public String getUsoServicios() {
		return usoServicios;
	}

	public void setUsoServicios(String usoServicios) {
		this.usoServicios = usoServicios;
	}

	public String getAreaComunicaciones() {
		return areaComunicaciones;
	}

	public void setAreaComunicaciones(String areaComunicaciones) {
		this.areaComunicaciones = areaComunicaciones;
	}
	
}
