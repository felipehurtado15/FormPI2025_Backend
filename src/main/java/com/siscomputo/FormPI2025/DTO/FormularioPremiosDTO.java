/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.siscomputo.FormPI2025.DTO;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Felipe
 */
public class FormularioPremiosDTO {

    private String nombreOrganizacion;
    private String NIT;
    private String direccion;
    private String nombreGerente;
    private String sector;
    private String otroSector;
    private String tamanioOrganizacion;
    private String municipio;

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

    public String getNombreOrganizacion() {
        return nombreOrganizacion;
    }

    public void setNombreOrganizacion(String nombreOrganizacion) {
        this.nombreOrganizacion = nombreOrganizacion;
    }

    public String getNIT() {
        return NIT;
    }

    public void setNIT(String NIT) {
        this.NIT = NIT;
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

}
