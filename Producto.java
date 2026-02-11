package Entregable;

public class Producto {
    private String id;
    private String nombre;
    private double precio;
    private int stock;
    static Producto productos[] = new Producto[100];
    private static int contador = 0;

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        if (precio <= 0)
            this.precio = 100;
        else
            this.precio = precio;
    }

    public void setStock(int stock) {
        if (stock < 0)
            this.stock = 1;
        else
            this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public void AgregarProducto(String id, String nombre, double precio, int stock) {
        for (int i = 0; i < contador; i++) {
            if (productos[i].getNombre().equals(nombre) || productos[i].getId().equals(id)) {
                System.out.println("Este producto ya existe. El producto no pudo ser agregado.");
                return; 
            }
        }

        if (contador < productos.length) {
            productos[contador] = new Producto(); 
            productos[contador].setId(id);
            productos[contador].setNombre(nombre);
            productos[contador].setPrecio(precio);
            productos[contador].setStock(stock);
            contador++;
            System.out.println("Producto agregado!");
        } else {
            System.out.println("Se ha alcanzado la cantidad maxima de productos.");
        }
    }

    
    public void listarProductos() {
        System.out.println("-----Listado de productos:-----");
        for (int i = 0; i < contador; i++) {
            System.out.println("ID: " + productos[i].getId() + ", Nombre: " + productos[i].getNombre() + ", Precio: " + productos[i].getPrecio() + ", Stock: " + productos[i].getStock());
        }
    }

    public static Producto buscarProducto(String id) {
        for (int i = 0; i < contador; i++) {
            if (productos[i].getId().equals(id) || productos[i].getNombre().equals(id)) {
                return productos[i];
            }
        }
        return null;
    }
    }


