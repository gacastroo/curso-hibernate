package com.cursojava;

import com.cursojava.modelo.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class LibroMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("curso-hibernate-pu");
        EntityManager em = emf.createEntityManager();
        System.out.println("=== CREAR LIBROS ===");
        em.getTransaction().begin();

        Libro libro1 = new Libro("Hola", "autor", 2.5, 2009);
        Libro libro2 = new Libro("La sombra del viento", "Carlos Ruiz Zafón", 18.90, 2001);
        Libro libro3 = new Libro("Cien años de soledad", "Gabriel García Márquez", 15.50, 1967);
        Libro libro4 = new Libro("El principito", "Antoine de Saint-Exupéry", 9.95, 1943);
        Libro libro5 = new Libro("1984", "George Orwell", 12.75, 1949);

        em.persist(libro1);
        em.persist(libro2);
        em.persist(libro3);
        em.persist(libro4);
        em.persist(libro5);
        em.getTransaction().commit();

        System.out.println("\n=== LISTAR TODOS ===");
        List<Libro> todos = em.createQuery("SELECT p FROM Libro p", Libro.class)
                .getResultList();
        for (Libro p : todos) {
            System.out.println("  " + p);
        }
        System.out.println("Total: " + todos.size() + " libros");


        System.out.println("\n=== LIBROS CON PRECIO minimo de 100 y max 300 ===");
        List<Libro> caros = em.createQuery(
                        "SELECT l FROM Libro l WHERE l.precio BETWEEN :min AND :max", Libro.class)
                .setParameter("min", 5.0)
                .setParameter("max", 20.0)
                .getResultList();
        for (Libro p : caros) {
            System.out.println("  " + p);
        }

        System.out.println("\n=== ENCONTRAR POR AUTOR ====");
        String nombreAutor = "Gabriel García Márquez";
        List<Libro> autor = em.createQuery(
                        "SELECT l FROM Libro l WHERE l.autor = :autor", Libro.class)
                .setParameter("autor", nombreAutor)
                .getResultList();
        for (Libro p : autor) {
            System.out.println("Libros cuyo autor es:  " + nombreAutor + " " + p);
        }

        System.out.println("\n ===== ACTUALIZAR EL PRECIO DEL LIBRO ====");
        em.getTransaction().begin();
        libro2.setPrecio(12.99);
        em.getTransaction().commit();
        System.out.println("Nuevo precio: " + libro2.getPrecio());

        System.out.println("\n===== ELIMINAR UN LIBRO ====");
        em.getTransaction().begin();
        String tituloLibro = "El principito";

        List<Libro> libros = em.createQuery("SELECT l FROM Libro l WHERE l.titulo = :titulo", Libro.class)
                .setParameter("titulo", tituloLibro)
                .getResultList();
        // Eliminar cada libro encontrado
        if (libros.isEmpty()) {
            System.out.println("No se encontró ningún libro con el título: " + tituloLibro);
        } else {
            for (Libro libro : libros) {
                em.remove(libro);
                System.out.println("Libro eliminado: " + libro.getTitulo());
            }
        }
        em.close();
        emf.close();
        System.out.println("\n=== FIN ===");
    }

}