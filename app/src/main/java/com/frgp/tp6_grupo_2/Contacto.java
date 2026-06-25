package com.frgp.tp6_grupo_2;

public class Contacto {

    private int id;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String email;
    private String fechaNacimiento;
    private String nivelEstudios;
    private String intereses;
    private int recibirInformacion;
    private String tipoTelefono;
    private String tipoEmail;


    public String getDireccion() {
        return direccion != null ? direccion : "";
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre != null ? nombre : "";
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido != null ? apellido : "";
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono != null ? telefono : "";
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email != null ? email : "";
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNivelEstudios() {
        return nivelEstudios != null ? nivelEstudios : "";
    }

    public void setNivelEstudios(String nivelEstudios) {
        this.nivelEstudios = nivelEstudios;
    }

    public String getIntereses() {
        return intereses != null ? intereses : "";
    }

    public void setIntereses(String intereses) {
        this.intereses = intereses;
    }

    public int getRecibirInformacion() {
        return recibirInformacion;
    }

    public void setRecibirInformacion(int recibirInformacion) {
        this.recibirInformacion = recibirInformacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento != null ? fechaNacimiento : "";
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTipoTelefono() {
        return tipoTelefono;
    }

    public void setTipoTelefono(String tipoTelefono) {
        this.tipoTelefono = tipoTelefono;
    }

    public String getTipoEmail() {
        return tipoEmail;
    }

    public void setTipoEmail(String tipoEmail) {
        this.tipoEmail = tipoEmail;
    }

    public Contacto(){
    }
    public Contacto(int id, String nombre, String apellido, String direccion,
                    String telefono, String email, String fechaNacimiento,
                    String nivelEstudios, String intereses,
                    String tipoTelefono, String tipoEmail,
                    int recibirInformacion) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.nivelEstudios = nivelEstudios;
        this.intereses = intereses;
        this.tipoTelefono = tipoTelefono;
        this.tipoEmail = tipoEmail;
        this.recibirInformacion = recibirInformacion;
    }

}
