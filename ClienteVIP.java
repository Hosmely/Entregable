package Entregable;

public class ClienteVIP extends Cliente {

    public ClienteVIP(String id, String nombre) {
        super(id, nombre);
    }
   

    @Override
    public  double CalcularDescuento(double subtotal){
        return subtotal *0.10;
    }


}
