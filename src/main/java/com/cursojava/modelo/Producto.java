package com.cursojava.modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity                              // "Esta clase es una tabla"
@Table(name = "productos")          // Nombre de la tabla en la BD
public class Producto {

    @Id                              // "Este campo es la Primary Key"
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-increment
    private Long id;

    @Column(nullable = false, length = 100)   // NOT NULL, max 100 chars
    private String nombre;

    @Column                          // Columna normal (nullable por defecto)
    private double precio;

    @Column
    private int stock;

    // Constructor VACIO — OBLIGATORIO para JPA
    public Producto() {
    }

    // Constructor con datos (sin id — la BD lo genera)
    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    @Override
    public String toString() {
        return "Producto{id=" + id + ", nombre='" + nombre + "', precio=" + precio + ", stock=" + stock + "}";
    }
}