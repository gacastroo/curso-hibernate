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

        // === DEMO: Tracking + Detach + Merge ===
        System.out.println("\n=== TRACKING + DETACH + MERGE ===");

        // 1. Buscar el teclado — queda MANAGED (Hibernate lo vigila)
        Producto teclado2 = em.find(Producto.class, 3L);
        System.out.println("Hibernate vigila teclado? " + em.contains(teclado2)); // true

        // 2. DESCONECTAR — Hibernate deja de vigilarlo
        em.detach(teclado2);
        System.out.println("Despues de detach, vigila? " + em.contains(teclado2)); // false

        // 3. Modificar el objeto desconectado — Hibernate NO se entera
        teclado2.setPrecio(59.99);

        // 4. RECONECTAR con merge — Hibernate lo vuelve a vigilar
        em.getTransaction().begin();
        Producto tecladoReconectado = em.merge(teclado2); // devuelve una COPIA managed
        em.getTransaction().commit();

        // 5. Comprobar tracking del original vs la copia
        System.out.println("Vigila el original (teclado2)?    " + em.contains(teclado2));          // false
        System.out.println("Vigila la copia (reconectado)?    " + em.contains(tecladoReconectado)); // true
        System.out.println("Precio en BD: " + tecladoReconectado.getPrecio() + " (59.99 = merge funciono)");

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