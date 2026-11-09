public abstract class IntegracionExterna implements Auditable, Comparable<IntegracionExterna>{
    protected String id;
    protected String nombre;
    protected String estadoConexion;
    protected double latenciaPromedio;
    protected String configuracion;

    public IntegracionExterna(String id, String nombre, String estadoConexion, double latenciaPromedio, String configuracion) {
        this.id = id;
        this.nombre = nombre;
        this.estadoConexion = estadoConexion;
        this.latenciaPromedio = latenciaPromedio;
        this.configuracion = configuracion;
    }

    @Override
    public int compareTo(IntegracionExterna otra) {
        return Double.compare(this.latenciaPromedio, otra.latenciaPromedio);
    }

    @Override
    public String verificarEstadoConexion() {
        return estadoConexion;
    }

    @Override
    public double getLatenciaPromedio() {
        return latenciaPromedio;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getConfiguracion() {
        return configuracion;
    }

    protected boolean estaDisponible() {
        return !"DESCONECTADA".equalsIgnoreCase(estadoConexion);
    }

    protected boolean operacionValida(double monto, String referencia) {
        return estaDisponible() && monto > 0 && referencia != null && !referencia.trim().isEmpty();
    }

    protected String errorNoDisponible() {
        return "ERROR: " + nombre + " no está disponible, estado: " + estadoConexion;
    }

    @Override
    public String toString() {
        return "Id: "+id+" Nombre: "+nombre+" Estado de conexión: "+estadoConexion+" Latencia promedio: "+latenciaPromedio;
    }
}
