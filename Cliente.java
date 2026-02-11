package Entregable;

public class Cliente {
    private String id;
    private String nombre;
    static Cliente clientes[] = new Cliente[100];
    private static  int contadorClientes = 0;
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

    public Cliente(String id, String nombre){
        this.id = id;
        this.nombre = nombre;
    }

    public Cliente(){}

    public double CalcularDescuento(double subtotal){
        return 0;
}

 public void AgregarCliente(String id, String nombre) {
    if (contadorClientes < clientes.length) {
        boolean existe = false;
        for (int i = 0; i < contadorClientes; i++) {
            if (clientes[i].getNombre().equals(nombre) || clientes[i].getId().equals(id)) {
                existe = true;
                break;
            }
        }
        if (existe) {
            System.out.println("Este cliente ya existe. El cliente no pudo ser agregado.");
        } else {
            clientes[contadorClientes] = new Cliente(id, nombre);
            contadorClientes++;
            System.out.println("Cliente agregado!");
        }
    } else {
        System.out.println("Se ha alcanzado la cantidad maxima de clientes.");
    } 
}

    public static Cliente BuscarCliente(String id){
         for (int i = 0; i < contadorClientes; i++) {
            if (clientes[i].getId().equals(id)) {
                return clientes[i];
            }
        }
        return null;
    }
   
}
