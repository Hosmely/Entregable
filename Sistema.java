package Entregable;

import java.util.Scanner;

public class Sistema {
    public static void main(String[] args) {
        int op;
        Producto producto = new Producto();
        Cliente cliente = null;
        Pedido pedido = new Pedido();


        do {
            System.out.println("-----Menu-----");
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
            Scanner scan = new Scanner(System.in);
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
                    producto.AgregarProducto(id, nombre, precio, stock);

                    break;
                case 2:
                    System.out.println("1. Cliente Regular");
                    System.out.println("2. Cliente VIP");
                    System.out.print("Seleccione : ");
                    int tipoCliente = scan.nextInt();
                    scan.nextLine();  
                    System.out.print("Ingrese el id del cliente: ");
                    String idCliente = scan.nextLine();
                    System.out.print("Ingrese el nombre del cliente: ");
                    String nombreCliente = scan.nextLine();

                     if (tipoCliente == 1) {
                        cliente = new ClienteRegular(idCliente, nombreCliente);
                        System.out.println("Cliente Regular registrado.");
                    } else if (tipoCliente == 2) {
                        cliente = new ClienteVIP(idCliente, nombreCliente);
                        System.out.println("Cliente VIP registrado.");
                    } else {
                        System.out.println("Opción no válida.");
                    }
                    break;

                case 3:
                    if (cliente != null) {
                        System.out.print("Ingrese el id del pedido: ");
                        String idPedido = scan.nextLine();
                        System.out.print("Ingrese el id del cliente: ");
                        idCliente = scan.nextLine();
                        Pedido.agregarPedido(idPedido, idCliente);;
                        System.out.println("Pedido creado.");
                    } else {
                        System.out.println("Primero debe registrar un cliente.");
                    }
                    break;

                case 4:
                    if (Pedido.getContadorPedidos() == 0) {
                        System.out.print("Ingrese el id del producto: ");
                        String idProductoPedido = scan.nextLine();
                        Producto productoPedido = Producto.buscarProducto(idProductoPedido);
                        if (productoPedido != null) {
                            System.out.print("Ingrese la cantidad del producto: ");
                            int cantidad = scan.nextInt();
                            pedido.AgregarProductosAlPedido(idProductoPedido, cantidad, productoPedido.getPrecio());
                        } else {
                            System.out.println("Producto no encontrado.");
                        }
                    } else {
                        System.out.println("Debe crear un pedido primero.");
                    }
                    break;

                case 5:
                    if (Pedido.getContadorPedidos() != 0) {
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
                    Pedido.listarPedidos();
                    break;

                case 8:
                    pedido.CambiarEstado();
                default:
                    break;
            }
        } while (op != 0);
    }
}
