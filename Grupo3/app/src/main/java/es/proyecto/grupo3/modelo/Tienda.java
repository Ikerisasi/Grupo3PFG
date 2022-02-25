package es.proyecto.grupo3.modelo;

public class Tienda {

    private int id;
    private String nombre;
    private String descripcion;
    private double localizacion;
    private double calle;
    private double longitud;
    private double latitud;

    public Tienda() {

    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getLocalizacion() {
        return localizacion;
    }
    public void setLocalizacion(double localizacion) {
        this.localizacion = localizacion;
    }

    public double getCalle() {
        return calle;
    }
    public void setCalle(double calle) {
        this.calle = calle;
    }

    public double getLongitud() {
        return longitud;
    }
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }
    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

}