package Entregable;

public class HiloGeneradorDeReportes implements Runnable{
    Producto p = new Producto();
    Pedido pe = new Pedido();

    @Override
    public void run(){
        while(true){
            generarReporte();

            try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
        }
       
        }

 private void generarReporte() {
    System.out.print("Cantidad de Productos: " + p.getProductos().size() );
    System.out.print("\nCantidad de Pedidos: " + pe.getPedidos().size() );

    }
}
