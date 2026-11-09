import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IntegracionController {
    private List<IntegracionExterna> catalogoIntegraciones;

    public IntegracionController() {
        this.catalogoIntegraciones = new ArrayList<>();
        iniciarCatalogo();
    }

    private void iniciarCatalogo(){
        catalogoIntegraciones.add(new PasarelaTarjeta("PAY-001", "Pasarela Tarjetas Credito/Debito", "CONECTADA", 180.5,"timeout=5000ms; moneda=GTQ; 3DS=true"));
        catalogoIntegraciones.add(new PasarelaTransferencia("PAY-002", "Transferencias Bancarias ACH", "CONECTADA", 420.0,"timeout=8000ms; moneda=GTQ; conciliacion=diaria"));
        catalogoIntegraciones.add(new PasarelaBilletera("PAY-003", "Billetera Digital Movil", "DEGRADADA", 650.8,"timeout=6000ms; moneda=GTQ; webhook=activo"));
        catalogoIntegraciones.add(new PasarelaCriptomonedas("PAY-004", "Pasarela Criptomonedas", "CONECTADA", 890.3,"timeout=10000ms; confirmaciones=3; red=mainnet"));

        catalogoIntegraciones.add(new ProveedorLogisticaNacional("LOG-001", "Logistica Nacional Express", "CONECTADA", 230.4,"timeout=5000ms; cobertura=22 departamentos"));
        catalogoIntegraciones.add(new ProveedorLogisticaInternacional("LOG-002", "Logistica Internacional Global", "DEGRADADA", 1250.0, "timeout=15000ms; aduana=integrada"));
        catalogoIntegraciones.add(new StartupUltimaMilla("LOG-003", "Startup Ultima Milla Rapido", "CONECTADA", 95.2,"timeout=3000ms; zona=Ciudad de Guatemala"));
        catalogoIntegraciones.add(new ServicioCotizacionEnvios("LOG-004", "Cotizador y Rastreo de Envios", "CONECTADA", 310.7, "timeout=4000ms; modo=solo-consulta"));

        catalogoIntegraciones.add(new CertificadoF1("FEL-001", "Certificador FEL Alfa", "CONECTADA", 275.9, "timeout=7000ms; formato=XML"));
        catalogoIntegraciones.add(new CertificadoF2("FEL-002", "Certificador FEL Beta", "DESCONECTADA", 1800.0, "timeout=7000ms; formato=XML"));
    }

    public List<IntegracionExterna> obtenerTodasLasIntegraciones() {
        return Collections.unmodifiableList(catalogoIntegraciones);
    }

    public IntegracionExterna buscarPorIdentificador(String id) {
        if (id == null) {
            return null;
        }
        String buscado = id.trim();
        for (IntegracionExterna integracion : catalogoIntegraciones) {
            if (integracion.getId().equalsIgnoreCase(buscado)) {
                return integracion;
            }
        }
        return null;
    }

    public void ordenarPorLatencia() {
        Collections.sort(catalogoIntegraciones);
    }

    public String ejecutarOperacionFinanciera(String idIntegracion, double monto) {
        IntegracionExterna integracion = buscarPorIdentificador(idIntegracion);
        if (integracion == null) {
            return "No existe una integracion con el identificador: " + idIntegracion;
        }
        if (!(integracion instanceof Transaccional)) {
            return integracion.getNombre() + " no lleva a cabo operaciones financieras";
        }
        Transaccional transaccional = (Transaccional) integracion;
        String referencia = "REF " + System.currentTimeMillis();

        StringBuilder resultado = new StringBuilder();
        boolean cobroOk = transaccional.procesarCobro(monto, referencia);
        resultado.append("Cobro de ").append(monto).append(" ").append(referencia).append(", ").append(cobroOk ? "APROBADO" : "RECHAZADO");
        if (cobroOk) {
            boolean reintegroOk = transaccional.procesarReintegro(monto, referencia);
            resultado.append(" Reintegro de ").append(monto).append(" ").append(referencia).append(", ").append(reintegroOk ? "APROBADO" : "RECHAZADO");
        } else {
            resultado.append("Reintegro rechazado, no hay cobro que devolver");
        }
        return resultado.toString();
    }
}

