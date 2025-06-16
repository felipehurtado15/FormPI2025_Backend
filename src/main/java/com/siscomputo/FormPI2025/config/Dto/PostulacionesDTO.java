package com.siscomputo.FormPI2025.config.Dto;

public class PostulacionesDTO {
	private String nombre;
	private String nit;
	private String direccion;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	@Override
	public String toString() {
		return "PostulacionesDTO [nombre=" + nombre + ", nit=" + nit + ", direccion=" + direccion + "]";
	}
	
	

}
