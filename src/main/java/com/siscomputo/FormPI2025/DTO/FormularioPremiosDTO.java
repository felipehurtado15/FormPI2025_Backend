package com.siscomputo.FormPI2025.DTO;


public class FormularioPremiosDTO {
	private String nombreOrganizacion;
	private String nit;
	private String direccion;
	
	private String organizacionAfiliada;
	private String usoServicios;
	private String areaComunicaciones;
	private String categoriasParticipar;
	private String razonesMotivan;
	private String contrataGP;
	private String numpersonasGP;
	
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

	public String getCategoriasParticipar() {
		return categoriasParticipar;
	}

	public void setCategoriasParticipar(String categoriasParticipar) {
		this.categoriasParticipar = categoriasParticipar;
	}

	public String getRazonesMotivan() {
		return razonesMotivan;
	}

	public void setRazonesMotivan(String razonesMotivan) {
		this.razonesMotivan = razonesMotivan;
	}

	public String getContrataGP() {
		return contrataGP;
	}

	public void setContrataGP(String contrataGP) {
		this.contrataGP = contrataGP;
	}

	public String getNumpersonasGP() {
		return numpersonasGP;
	}

	public void setNumpersonasGP(String numpersonasGP) {
		this.numpersonasGP = numpersonasGP;
	}

	@Override
	public String toString() {
		return "FormularioPremiosDTO [nombreOrganizacion=" + nombreOrganizacion + ", nit=" + nit + ", direccion="
				+ direccion + ", organizacionAfiliada=" + organizacionAfiliada + ", usoServicios=" + usoServicios
				+ ", areaComunicaciones=" + areaComunicaciones + ", categoriasParticipar=" + categoriasParticipar
				+ ", razonesMotivan=" + razonesMotivan + ", contrataGP=" + contrataGP + ", numpersonasGP="
				+ numpersonasGP + ", getNombreOrganizacion()=" + getNombreOrganizacion() + ", getNit()=" + getNit()
				+ ", getDireccion()=" + getDireccion() + ", getOrganizacionAfiliada()=" + getOrganizacionAfiliada()
				+ ", getUsoServicios()=" + getUsoServicios() + ", getAreaComunicaciones()=" + getAreaComunicaciones()
				+ ", getCategoriasParticipar()=" + getCategoriasParticipar() + ", getRazonesMotivan()="
				+ getRazonesMotivan() + ", getContrataGP()=" + getContrataGP() + ", getNumpersonasGP()="
				+ getNumpersonasGP() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
	
}
