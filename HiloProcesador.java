package Entregable;
import java.util.List;
import java.util.ArrayList;
public class HiloProcesador implements Runnable{
    private List<Pedido> pedidos = new ArrayList<>();
    public HiloProcesador(List<Pedido> pedidos){
        this.pedidos = pedidos;
    }
    @Override
    public void run(){
        while(true){
            for(Pedido pedido : pedidos){
                if (pedido.getEstado().equals("CONFIRMADO")){
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pedido.setEstado("PROCESADO");
                }
            }

            try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
        }
    }
    

}
