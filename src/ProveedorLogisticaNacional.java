public class ProveedorLogisticaNacional extends IntegracionExterna implements Documentable {
    public ProveedorLogisticaNacional(String id, String nombre, String estadoConexion, double latenciaPromedio, String configuracion) {
        super(id, nombre, estadoConexion, latenciaPromedio, configuracion);
    }

    @Override
    public String generarDocumento(String tipoDocumento) {
        if (!estaDisponible()) {
            return errorNoDisponible();
        }
        return "Guía de envío\n"
        + "Proveedor: " + nombre+ " TipoDocumento:" + tipoDocumento+ "  Alcance: NACIONAL";
    }
}
