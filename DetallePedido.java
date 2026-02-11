package Entregable;

public class DetallePedido {
    private String producto;
    private int cantidad;
    private double precioUnitario;

    public void setProducto(String producto){
        this.producto = producto;
    }

    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }

    public void setPrecioUnitario(double precioUnitario){
        this.precioUnitario = precioUnitario;
    }

    public String getProducto(){
        return producto;
    }

    public int getCantidad(){
        return cantidad;
    }

    public double getPrecioUnitario(){
        return precioUnitario;
    }

    public DetallePedido(String producto, int cantidad, double precioUnitario){
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        
    }
}
