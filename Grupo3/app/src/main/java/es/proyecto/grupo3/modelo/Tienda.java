package es.proyecto.grupo3.modelo;

public class Tienda {

    private int id;
    private String nombre;
    private String descripcion;
    private String localizacion;
    private String calle;
    private double longitud;
    private double latitud;
    private int idTendero;

    public Tienda(int id, String nombre, String descripcion, String localizacion, String calle, double longitud, double latitud, int idTendero) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.localizacion = localizacion;
        this.calle = calle;
        this.longitud = longitud;
        this.latitud = latitud;
        this.idTendero = idTendero;
    }

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

    public String getLocalizacion() {
        return localizacion;
    }
    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public String getCalle() {
        return calle;
    }
    public void setCalle(String calle) {
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

    public int getIdTendero() {
        return idTendero;
    }
    public void setIdTendero(int idTendero) {
        this.idTendero = idTendero;
    }

}