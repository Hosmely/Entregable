package Entregable;

import java.util.Scanner;

public class Pedido {
    private String id;
    private String cliente;
    private String estado;
    private Cliente tipoCliente;
    private DetallePedido detalles[] = new DetallePedido[100];
    private static int contadorProductosAgregados = 0;
    static Pedido[] pedidos = new Pedido[100];
    private static int contadorPedidos = 0;
    Producto producto;
    Scanner scan = new Scanner(System.in);

    public static int getContadorPedidos() {
        return contadorPedidos;
    }

    public static int getContadorProductosAgregados() {
        return contadorProductosAgregados;
    }

    public Pedido() {
    }

    public Pedido(String id, String cliente) {
        this.id = id;
        this.cliente = cliente;
        this.estado = "BORRADOR";
    }

    public void CambiarEstado() {
        System.out.println("1.BORRADOR\n2.CONFIRMAR\n3.CANCELAR");
        int opcion = scan.nextInt();
        switch (opcion) {
            case 1:
                System.out.println("El pedido ya esta en borrador");
                break;
            case 2:
                if (contadorProductosAgregados == 0) {
                    System.out.println("No se puede confirmar el pedido, ya que no tiene productos.");
                } else {
                    estado = "CONFIRMADO";
                    for (int i = 0; i < contadorProductosAgregados; i++) {
                        int cantidadRestada = detalles[i].getCantidad();
                        int stock = Producto.buscarProducto(detalles[i].getProducto()).getStock();
                        int stockActualizado = stock - cantidadRestada;
                        Producto.buscarProducto(detalles[i].getProducto()).setStock(stockActualizado);
                    }
                    System.out.println("El pedido ha sido confirmado");
                }
                break;
            case 3:
                estado = "CANCELADO";
                System.out.println("El pedido ha sido cancelado");
                break;
            default:
                System.out.println("Opcion invalida");
                break;
        }
    }

    public void AgregarProductosAlPedido(String idProducto, int cantidad, double precioUnitario) {
        if (contadorProductosAgregados < detalles.length) {
            if (Producto.buscarProducto(idProducto) != null) {
                detalles[contadorProductosAgregados++] = new DetallePedido(idProducto, cantidad, precioUnitario);
                System.out.println("Producto agregado al pedido.");
            }
            else{
                System.out.println("El producto que se quiere agregar no existe.");
            }
        } else {
            System.out.println("Se ha alcanzado la cantidad maxima de productos permitidos en el pedido.");
        }
    }

    public double CalculaSubtotal() {
        double subtotal = 0;
        for (int i = 0; i < contadorProductosAgregados; i++) {
            double totalProducto = detalles[i].getCantidad() * detalles[i].getPrecioUnitario();
            subtotal += totalProducto;
        }
        return subtotal;
    }

    public double CalcularDescuento() {
        double subtotal = CalculaSubtotal();
        return tipoCliente.CalcularDescuento(subtotal);
    }

    public double CalcularTotalFinal() {
        return CalculaSubtotal() - CalcularDescuento();
    }

    public static void agregarPedido(String id, String cliente) {
        if (contadorPedidos < pedidos.length) {
            if (Cliente.BuscarCliente(cliente) != null) {
                pedidos[contadorPedidos] = new Pedido(id, cliente);
                System.out.println("Pedido creado!");
                contadorPedidos++;
            } else
                System.out.println("El pedido debe estar asociado a un cliente existente");

        } else {
            System.out.println("No se pueden agregar mas pedidos.");
        }
    }

    public static void listarPedidos() {
        if (contadorPedidos == 0) {
            System.out.println("No hay pedidos registrados.");
        } else {
            for (int i = 0; i < contadorPedidos; i++) {
                System.out.println("Pedido ID: " + pedidos[i].id + " | Cliente: " + pedidos[i].tipoCliente.getNombre()
                        + " | Estado: " + pedidos[i].estado);
            }
        }
    }
}
