package com.cursojava.modelo;

import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

@Entity
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false, length = 200)
    String titulo;
    @Column(nullable = false)
    String autor;
    @Column
    double precio;
    @Column(name = "anio_publicacion")
    int anioPublicacion;

    public Libro() {
    }
    public Libro(String titulo, String autor, double precio, int anioPublicacion) {
        this.titulo = titulo;
        this.autor = autor;
        this.precio = precio;
        this.anioPublicacion = anioPublicacion;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Libro {id= " + id + "titulo=" + titulo + ", autor=" + autor + ", precio=" + precio;
    }
}
