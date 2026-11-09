public class ProveedorLogisticaInternacional extends IntegracionExterna implements Documentable {
    public ProveedorLogisticaInternacional(String id, String nombre, String estadoConexion, double latenciaPromedio, String configuracion) {
        super(id, nombre, estadoConexion, latenciaPromedio, configuracion);
    }

    @Override
    public String generarDocumento(String tipoDocumento) {
        if (!estaDisponible()) {
            return errorNoDisponible();
        }
        return "Guía de envío\n"
        + "Proveedor: " + nombre+ " TipoDocumento:" + tipoDocumento+ "  Alcance: INTERNACIONAL";
    }
}
