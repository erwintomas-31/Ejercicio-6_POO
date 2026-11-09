public class StartupUltimaMilla extends IntegracionExterna implements Documentable {
    public StartupUltimaMilla(String id, String nombre, String estadoConexion, double latenciaPromedio, String configuracion) {
        super(id, nombre, estadoConexion, latenciaPromedio, configuracion);
    }

    @Override
    public String generarDocumento(String tipoDocumento) {
        if (!estaDisponible()) {
            return errorNoDisponible();
        }
        return "Guía de envío\n"
        + "Proveedor: " + nombre+ " TipoDocumento:" + tipoDocumento+ "  Alcance: ULTIMA MILLA";
    }
}
