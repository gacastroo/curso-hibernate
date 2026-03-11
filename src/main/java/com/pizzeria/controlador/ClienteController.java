package com.pizzeria.controlador;
import com.pizzeria.modelo.Cliente;
import com.pizzeria.modelo.TipoCliente;
import com.pizzeria.repositorio.ClienteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {


    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    @GetMapping
    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Cliente> findById(@RequestParam(value = "id") Long id){
        return clienteRepository.findById(id);
    }

    @GetMapping("/tipo/{tipo}")
    public List<Cliente> findByTipoCliente(@PathVariable(value = "tipo") TipoCliente tipoCliente){
        return clienteRepository.findByTipoCliente(tipoCliente);
    }

    @PostMapping
    public Cliente create(@RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }

    @PutMapping("/{id}")
    public Cliente actualizar(@RequestBody Cliente cliente, @RequestParam(value = "id") Long id){
        return clienteRepository.save(cliente);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable @RequestParam(value = "id") Long id){
        clienteRepository.deleteById(id);
    }

}
