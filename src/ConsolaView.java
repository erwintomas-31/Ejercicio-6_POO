import java.util.List;
import java.util.Scanner;

public class ConsolaView {
    private Scanner scanner;
    private IntegracionController controlador;

    public ConsolaView(){
        this.scanner = new Scanner(System.in);
        this.controlador = new IntegracionController();
    }

    public void iniciarMenu(){
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("GESTOR DE INTEGRACIONES EXTERNAS");
            System.out.println("1. Ver todas las integraciones y su estado de conexion");
            System.out.println("2. Buscar integracion por id");
            System.out.println("3. Ordenar por latencia promedio");
            System.out.println("4. Simular transaccion financiera");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");
            try {
                opcion = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                opcion = -1;
            }
            switch (opcion) {
                case 1:
                    mostrarListaIntegraciones();
                    break;
                case 2:
                    buscarIntegracion();
                    break;
                case 3:
                    ordenarYMostrarLatencias();
                    break;
                case 4:
                    simularTransaccion();
                    break;
                case 0:
                    System.out.println("Cerrando el sistema");
                    break;
                default:
                    System.out.println("Opcion invalida, intente de nuevo");
            }
        }
    }

    public void mostrarListaIntegraciones() {
        System.out.println("Integraciones registradas");
        for (IntegracionExterna integracion : controlador.obtenerTodasLasIntegraciones()) {
            System.out.println(integracion);
        }
    }

    public void buscarIntegracion() {
        System.out.print("Ingrese el identificador");
        String id = scanner.nextLine();
        IntegracionExterna integracion = controlador.buscarPorIdentificador(id);
        if (integracion == null) {
            System.out.println("No se encontro ninguna integracion con el identificador: " + id.trim());
            return;
        }
        System.out.println("Detalle de la integracion");
        System.out.println("ID: " + integracion.getId());
        System.out.println("Nombre: " + integracion.getNombre());
        System.out.println("Estado: " + integracion.verificarEstadoConexion());
        System.out.println("Latencia: " + integracion.getLatenciaPromedio() + " ms");
        System.out.println("Configuracion: " + integracion.getConfiguracion());
        System.out.println("Capacidades: " + describirCapacidades(integracion));
    }

    public void ordenarYMostrarLatencias() {
        controlador.ordenarPorLatencia();
        List<IntegracionExterna> catalogo = controlador.obtenerTodasLasIntegraciones();

        System.out.println("Catalogo ordenado por latencia (menor a mayor)");
        for (int i = 0; i < catalogo.size(); i++) {
            String marca = (i == catalogo.size() - 1) ? " posible cuello de botella" : "";
            System.out.println(catalogo.get(i) + marca);
        }
    }

    public void simularTransaccion() {
        System.out.print("Identificador de la integracion: ");
        String id = scanner.nextLine();

        System.out.print("Monto Q: ");
        double monto;
        try {
            monto = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Monto invalido");
            return;
        }
        System.out.println(controlador.ejecutarOperacionFinanciera(id, monto));
    }

    private String describirCapacidades(IntegracionExterna integracion) {
        StringBuilder sb = new StringBuilder("Auditable");
        if (integracion instanceof Transaccional) {
            sb.append(", Transaccional (cobro/reintegro)");
        }
        if (integracion instanceof Documentable) {
            sb.append(", Documentable");
        }
        return sb.toString();
    }

}
