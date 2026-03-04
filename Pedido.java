package Entregable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Pedido {
    private String id;
    private String cliente;
    private String estado;
    private Date fechaDeCreacion;
    private Cliente tipoCliente = null;
    List<DetallePedido> detalles = new ArrayList<>();
    List<Pedido> pedidos = new ArrayList<>();
    Producto producto = new Producto();
    Scanner scan = new Scanner(System.in);
    DateFormat formato = new SimpleDateFormat("dd/mm/yyyy");
    
    public Pedido() {
    }

    public Pedido(String id, String cliente, String fecha)throws ParseException {
        this.id = id;
        this.cliente = cliente;
        Date fechaR = formato.parse(fecha);
        this.fechaDeCreacion = fechaR;
        this.estado = "BORRADOR";
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public synchronized List<Pedido> getPedidos() {
        return pedidos;
    }

    public void CambiarEstado()throws PedidoInvalidoException {
        System.out.println("1.BORRADOR\n2.CONFIRMAR\n3.CANCELAR");
        int opcion = scan.nextInt();
        switch (opcion) {
            case 1:
                System.out.println("El pedido ya esta en borrador");
                break;
            case 2:
                if (detalles.isEmpty()) {
                    throw new PedidoInvalidoException("No se puede confirmar el pedido, ya que no tiene productos.");
                } else {
                    estado = "CONFIRMADO";
                    for (DetallePedido detalle : detalles) {
                        int cantidadRestada = detalle.getCantidad();
                        int stock = producto.getStock();
                        int stockActualizado = stock - cantidadRestada;
                        producto.setStock(stockActualizado);
                    }
                    System.out.println("El pedido ha sido confirmado");
                }
                break;
            case 3:
                estado = "CANCELADO";
                pedidos.removeLast();
                System.out.println("El pedido ha sido cancelado");
                break;
            default:
                System.out.println("Opcion invalida");
                break;
        }
    }

    public void AgregarProductosAlPedido(Producto producto, int cantidad, double precioUnitario) throws ProductoNoEncontradoException, StockInsuficienteException{
       
            if( producto.getStock()< cantidad)
                throw new StockInsuficienteException("No hay suficiente stock del producto requerido.");
            else{
//
                detalles.add(new DetallePedido(producto.getNombre(), cantidad, precioUnitario));
                System.out.println("Producto agregado al pedido.");
            }
            }
       
    

    public double CalculaSubtotal() {
        double subtotal = 0;
        for (DetallePedido detalle : detalles) {
            double totalProducto = detalle.getCantidad() * detalle.getPrecioUnitario();
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

    public void agregarPedido(String id, String cliente, String fechaDeCreacion)throws ParseException {
        
            if (Cliente.BuscarCliente(cliente) != null) { 
                Pedido p = new Pedido(id, cliente, fechaDeCreacion);
                pedidos.add(p);
                System.out.println("Pedido creado!");
            } else
                System.out.println("El pedido debe estar asociado a un cliente existente");

    }
    @Override
    public String toString(){
        String fechaformateada = formato.format(fechaDeCreacion);
        return String.format("Pedido ID: %s\tCliente: %s\tEstado: %s\tFecha: %s", id, cliente,estado,fechaformateada);

    }
    public void listarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos registrados.");
        } else {

            for (Pedido pedido : pedidos) {
                System.out.println(pedido.toString());
            }
        }
    }
}
