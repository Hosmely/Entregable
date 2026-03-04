package Entregable;

import java.util.ArrayList; //create a separate class to deal with the client list
import java.util.List;

public class Cliente {
    private String id;
    private String nombre;
 static List<Cliente> clientes =  new ArrayList<>();

      public void setId(String id){
        this.id = id;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

      public String getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public Cliente(String id, String nombre) throws IllegalArgumentException{
        setId(id);
        if(nombre.isBlank()|| nombre == null)
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
        else
            setNombre(nombre);
    }

    public Cliente(){}

    public double CalcularDescuento(double subtotal){
        return 0;
}

 public void AgregarCliente(String id, String nombre)throws IllegalArgumentException {
        boolean existe = false;
        for (Cliente cliente : clientes) {

            if (cliente.getNombre().equals(nombre) || cliente.getId().equals(id)) {
                existe = true;
                break;
            }
        }
        if (existe) {
            System.out.println("Este cliente ya existe. El cliente no pudo ser agregado.");
        } else {            
            clientes.add(new Cliente(id, nombre));
            System.out.println("Cliente agregado!");
        }
    
}

    public static Cliente BuscarCliente(String codigo){
         if (clientes.isEmpty()) {
            System.out.println("La lista de clientes esta vacia.");
            return null;
        } 
        for (Cliente cliente : clientes) {
            if (cliente.getId().equals(codigo)) {
                return  cliente;
            }
        }
        return null;
    }
   
}
