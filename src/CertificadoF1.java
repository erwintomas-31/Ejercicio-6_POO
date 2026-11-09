public class CertificadoF1 extends IntegracionExterna implements Documentable{
    public CertificadoF1(String id, String nombre, String estadoConexion,double latenciaPromedio, String configuracion) {
        super(id, nombre, estadoConexion, latenciaPromedio, configuracion);
    }

    @Override
    public String generarDocumento(String tipoDocumento) {
        if (!estaDisponible()) {
            return errorNoDisponible();
        }
        return "Certificado FEL 1, XML: \n"
        +"Certificador: " + nombre + "  TipoDocumento:" + tipoDocumento+ "  Estado: Certificado";
    }
}
