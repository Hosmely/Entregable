package Entregable;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String id;
    private String nombre;
    private List<Cliente> clientes =  new ArrayList<>();

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
        if(nombre.isBlank())
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
        else if(nombre.equals(null))
            throw new IllegalArgumentException("El nombre no puede ser nulo.");
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
        if (existe == true) {
            System.out.println("Este cliente ya existe. El cliente no pudo ser agregado.");
        } else {
            Cliente c = new Cliente(id, nombre);
            clientes.add(c);
            System.out.println("Cliente agregado!");
        }
    
}

    public Cliente BuscarCliente(String id){
         for (Cliente cliente : clientes) {
            if (cliente.getId().equals(id)) {
                return  cliente;
            }
        }
        return null;
    }
   
}
