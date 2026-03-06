package com.cursojava;

import com.cursojava.modelo.Producto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("curso-hibernate-pu");
        EntityManager em = emf.createEntityManager();

        // === CREATE ===
        System.out.println("=== CREAR PRODUCTOS ===");
        em.getTransaction().begin();

        Producto laptop = new Producto("Laptop Lenovo", 899.99, 15);
        Producto mouse = new Producto("Mouse Logitech", 29.99, 100);
        Producto teclado = new Producto("Teclado Mecanico", 79.99, 50);

        em.persist(laptop);
        em.persist(mouse);
        em.persist(teclado);

        em.getTransaction().commit();
        System.out.println("Creados 3 productos. IDs: " + laptop.getId() + ", " + mouse.getId() + ", " + teclado.getId());

        // === READ ===
        System.out.println("\n=== BUSCAR POR ID ===");
        Producto encontrado = em.find(Producto.class, 1L);
        System.out.println("ID 1: " + encontrado);

        // === UPDATE ===
        System.out.println("\n=== ACTUALIZAR PRECIO ===");
        em.getTransaction().begin();
        encontrado.setPrecio(799.99);
        em.getTransaction().commit();
        System.out.println("Nuevo precio: " + encontrado.getPrecio());

        // === DELETE ===
        System.out.println("\n=== ELIMINAR MOUSE ===");
        em.getTransaction().begin();
        Producto aEliminar = em.find(Producto.class, 2L);
        em.remove(aEliminar);
        em.getTransaction().commit();

        // === LISTAR TODOS ===
        System.out.println("\n=== LISTAR TODOS ===");
        List<Producto> todos = em.createQuery("SELECT p FROM Producto p", Producto.class)
                .getResultList();
        for (Producto p : todos) {
            System.out.println("  " + p);
        }
        System.out.println("Total: " + todos.size() + " productos");

        // === CONSULTA CON FILTRO ===
        System.out.println("\n=== PRODUCTOS CON PRECIO > 100 ===");
        List<Producto> caros = em.createQuery(
                        "SELECT p FROM Producto p WHERE p.precio > :precioMin", Producto.class)
                .setParameter("precioMin", 100.0)
                .getResultList();
        for (Producto p : caros) {
            System.out.println("  " + p);
        }

        em.close();
        emf.close();
        System.out.println("\n=== FIN ===");
    }
}