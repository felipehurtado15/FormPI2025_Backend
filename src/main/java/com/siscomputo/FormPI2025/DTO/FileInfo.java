package com.siscomputo.FormPI2025.DTO;

public class FileInfo {
	 private String nombre;
	    private String fechaCreacion;
	    private String nit;
	    private String organizacion;
	    private String postulante;

	    public FileInfo() {}

	    public FileInfo(String nombre, String fechaCreacion, String nit, String organizacion, String postulante) {
	        this.nombre = nombre;
	        this.fechaCreacion = fechaCreacion;
	        this.nit = nit;
	        this.organizacion = organizacion;
	        this.postulante = postulante;
	    }

	    // Getters y Setters
	    public String getNombre() {
	        return nombre;
	    }

	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }

	    public String getFechaCreacion() {
	        return fechaCreacion;
	    }

	    public void setFechaCreacion(String fechaCreacion) {
	        this.fechaCreacion = fechaCreacion;
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
