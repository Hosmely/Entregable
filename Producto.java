package Entregable;

import java.util.ArrayList;
import java.util.List;

public class Producto {
    private String id;
    private String nombre;
    private double precio;
    private int stock;
    List<Producto> productos = new ArrayList<>();

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

    public List<Producto> getProductos() {
        return productos;
    }

    public Producto(String id, String nombre, double precio, int stock) throws IllegalArgumentException{
        setId(id);
        if(nombre.isBlank())
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
        else if(nombre.equals(null))
            throw new IllegalArgumentException("El nombre no puede ser nulo.");
        else
            setNombre(nombre);
        setPrecio(precio);
        setStock(stock);
    }

    public Producto(){}

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

    public void AgregarProducto(String id, String nombre, double precio, int stock) throws IllegalArgumentException{
        for (Producto producto: productos) {
            if (producto.getNombre().equalsIgnoreCase(nombre) || producto.getId().equalsIgnoreCase(id)) {
                System.out.println("Este producto ya existe. El producto no pudo ser agregado.");
                return; 
            }
        }
            productos.add(new Producto(id, nombre, precio, stock));
            System.out.println("Producto agregado!");
       
    }

    @Override
    public String toString(){
        return String.format("ID: %s\tNombre: %s\tPrecio: %s\tStock: %s", getId(), getNombre(), getPrecio(), getStock());
    }
    
    public void listarProductos() {
        System.out.println("-----Listado de productos:-----");
        for (Producto producto : productos) {
            System.out.println(producto.toString());
        }
    }

    public Producto buscarProducto(String id) throws ProductoNoEncontradoException{ 
        for (Producto producto : productos) {
            if (producto.getId().equals(id) || producto.getNombre().equals(id)) {
                return producto;
            }
        }
        throw new ProductoNoEncontradoException("El producto que se quiere agregar no existe.");
        
    }
    }


