public class CertificadoF2 extends IntegracionExterna implements Documentable {
    public CertificadoF2(String id, String nombre, String estadoConexion,double latenciaPromedio, String configuracion) {
        super(id, nombre, estadoConexion, latenciaPromedio, configuracion);
    }

    @Override
    public String generarDocumento(String tipoDocumento) {
        if (!estaDisponible()) {
            return errorNoDisponible();
        }
        return "Certificado FEL 2, XML: \n"
        +"Certificador: " + nombre + "  TipoDocumento:" + tipoDocumento+ "  Estado: Certificado";
    }
}