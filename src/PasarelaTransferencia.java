public class PasarelaTransferencia extends IntegracionExterna implements Transaccional {
    public PasarelaTransferencia(String id, String nombre, String estadoConexion,double latenciaPromedio, String configuracion) {
        super(id, nombre, estadoConexion, latenciaPromedio, configuracion);
    }

    @Override
    public boolean procesarCobro(double monto, String referencia) {
        return operacionValida(monto, referencia) && monto >= 10;
    }

    @Override
    public boolean procesarReintegro(double monto, String referenciaTransaccion) {
        return operacionValida(monto, referenciaTransaccion);
    }
}
