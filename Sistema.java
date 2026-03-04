package Entregable;

import java.text.ParseException;
import java.util.Scanner;

public class Sistema {
    public static void main(String[] args) {
        int op;
        Producto producto = new Producto();
        Pedido pedido = new Pedido();
        Thread hiloProcesador = new Thread(new HiloProcesador(pedido.getPedidos()));
        hiloProcesador.start();
        Thread hiloReporte = new Thread(new HiloGeneradorDeReportes());
        hiloReporte.setDaemon(true);
        hiloReporte.start(); 
        Scanner scan = new Scanner(System.in);

        
        do {
            System.out.println("\n\t-----Menu-----");
            System.out.println("1. Registrar producto  ");
            System.out.println("2. Registrar cliente  ");
            System.out.println("3. Crear pedido");
            System.out.println("4. Agregar producto a pedido");
            System.out.println("5. Ver detalle de pedido");
            System.out.println("6. Listar productos  ");
            System.out.println("7. Listar pedidos");
            System.out.println("8. Cambiar estado de pedido ");
            System.out.println("0. Salir  ");
            System.out.println("Opcion: ");

            op = scan.nextInt();

            scan.nextLine();
            switch (op) {
                case 1:

                    System.out.print("Ingrese el id del producto: ");
                    String id = scan.nextLine();
                    System.out.print("Ingrese el nombre del producto: ");
                    String nombre = scan.nextLine();
                    System.out.print("Ingrese el precio del producto: ");
                    double precio = scan.nextDouble();
                    System.out.print("Ingrese la cantidad en stock del producto: ");
                    int stock = scan.nextInt();
                    try {
                        producto.AgregarProducto(id, nombre, precio, stock);
                    } catch (IllegalArgumentException iae) {
                        System.out.println("El pedido no pudo ser agregado.");
                    }

                    break;
                case 2:
                    System.out.println("1. Cliente Regular");
                    System.out.println("2. Cliente VIP");
                    System.out.print("Seleccione : ");
                    int tipoCliente = scan.nextInt();
                    scan.nextLine();
                    String idCliente;
                    String nombreCliente;
                    switch (tipoCliente) {
                        case 1:
                            try {
                                System.out.print("Ingrese el id del cliente: ");
                                idCliente = scan.nextLine();
                                System.out.print("Ingrese el nombre del cliente: ");
                                nombreCliente = scan.nextLine();
                                ClienteRegular cl1 = new ClienteRegular();
                                cl1.AgregarCliente(idCliente, nombreCliente);
                            } catch (IllegalArgumentException iae) {
                                System.out.println(iae.getMessage());
                            }
                            break;

                        case 2:
                            try {
                                System.out.print("Ingrese el id del cliente: ");
                                idCliente = scan.nextLine();
                                System.out.print("Ingrese el nombre del cliente: ");
                                nombreCliente = scan.nextLine();
                                ClienteVIP cl2 = new ClienteVIP();
                                cl2.AgregarCliente(idCliente, nombreCliente);
                            } catch (IllegalArgumentException iae) {
                                System.out.println(iae.getMessage());
                            }
                            break;

                        default:
                            System.out.println("Opción no válida.");
                    }
                    break;

                case 3:
                    System.out.print("Ingrese el id del pedido: ");
                    String idPedido = scan.nextLine();
                    System.out.print("Ingrese el id del cliente: ");
                    idCliente = scan.nextLine();
                    System.out.print("Ingrese la fecha de creacion: ");
                  String fecha = scan.nextLine();
                    try {
                        pedido.agregarPedido(idPedido, idCliente, fecha);
                    } catch (ParseException pe) {
                        System.out.print(pe.getMessage());
                    }

                    break;

                case 4:
                    if (!pedido.pedidos.isEmpty()) {
                        System.out.print("Ingrese el id o nombre del producto: ");
                        String idProductoPedido = scan.nextLine();
                        Producto productoPedido = null;
                        try {
                            productoPedido = producto.buscarProducto(idProductoPedido);
                        } catch (ProductoNoEncontradoException e) {
                            System.out.println(e.getMessage());
                        }
                        if (productoPedido != null) {
                            System.out.print("Ingrese la cantidad del producto: ");
                            int cantidad = scan.nextInt();
                            try {
                                pedido.AgregarProductosAlPedido(productoPedido, cantidad, productoPedido.getPrecio());
                            } catch (ProductoNoEncontradoException | StockInsuficienteException e) {
                                System.out.println(e.getMessage());
                            }
                        } else {
                            System.out.println("Producto no encontrado.");
                        }
                    } else {
                        System.out.println("Debe crear un pedido primero.");
                    }
                    break;

                case 5:
                    if (!pedido.pedidos.isEmpty()) {
                        System.out.println("Subtotal: " + pedido.CalculaSubtotal());
                        System.out.println("Descuento: " + pedido.CalcularDescuento());
                        System.out.println("Total final: " + pedido.CalcularTotalFinal());
                    } else {
                        System.out.println("Debe crear un pedido primero.");
                    }
                    break;

                case 6:
                    producto.listarProductos();
                    break;

                case 7:
                    pedido.listarPedidos();
                    break;

                case 8:

                    try {
                        pedido.CambiarEstado();
                    } catch (PedidoInvalidoException pie) {
                        System.out.println(pie.getMessage());
                    }
                default:
                    break;
            }
        } while (op != 0);
        scan.close();
    }

}
