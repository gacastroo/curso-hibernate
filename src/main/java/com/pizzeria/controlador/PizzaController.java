package com.pizzeria.controlador;

import com.pizzeria.modelo.Categoria;
import com.pizzeria.modelo.Pizza;
import com.pizzeria.servicio.PizzaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {

    private final PizzaService pizzaService;

    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    // GET http://localhost:8080/api/pizzas
    @GetMapping
    public List<Pizza> listarTodas() {
        return pizzaService.listarTodas();
    }

    // GET http://localhost:8080/api/pizzas/1
    @GetMapping("/{id}")
    public ResponseEntity<Pizza> buscarPorId(@PathVariable Long id) {
        return pizzaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST http://localhost:8080/api/pizzas
    @PostMapping
    public ResponseEntity<Pizza> crear(@RequestBody Pizza pizza) {
        Pizza nueva = pizzaService.crear(pizza);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    // PUT http://localhost:8080/api/pizzas/1
    @PutMapping("/{id}")
    public ResponseEntity<Pizza> actualizar(
            @PathVariable Long id, @RequestBody Pizza pizza) {
        try {
            Pizza actualizada = pizzaService.actualizar(id, pizza);
            return ResponseEntity.ok(actualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE http://localhost:8080/api/pizzas/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            pizzaService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // GET http://localhost:8080/api/pizzas/categoria/PREMIUM
    @GetMapping("/categoria/{categoria}")
    public List<Pizza> buscarPorCategoria(
            @PathVariable Categoria categoria) {
        return pizzaService.buscarPorCategoria(categoria);
    }

    // GET http://localhost:8080/api/pizzas/baratas?precioMax=10.0
    @GetMapping("/baratas")
    public List<Pizza> buscarBaratas(@RequestParam double precioMax) {
        return pizzaService.buscarBaratas(precioMax);
    }
}