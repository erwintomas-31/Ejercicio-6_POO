public class PasarelaBilletera extends IntegracionExterna implements Transaccional {
    public PasarelaBilletera(String id, String nombre, String estadoConexion, double latenciaPromedio, String configuracion) {
        super(id, nombre, estadoConexion, latenciaPromedio, configuracion);
    }

    @Override
    public boolean procesarCobro(double monto, String referencia) {
        return operacionValida(monto, referencia) && monto <= 5000;
    }

    @Override
    public boolean procesarReintegro(double monto, String referenciaTransaccion) {
        return operacionValida(monto, referenciaTransaccion);
    }
}
