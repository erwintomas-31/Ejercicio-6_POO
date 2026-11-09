public interface Transaccional {
    boolean procesarCobro(double monto, String referencia);

    boolean procesarReintegro(double monto, String referenciaTransaccion);
}
