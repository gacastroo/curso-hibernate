package com.pizzeria.servicio;
import com.pizzeria.modelo.Cliente;
import com.pizzeria.modelo.TipoCliente;
import com.pizzeria.repositorio.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }
    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }
    public void crear(Cliente cliente) {
        clienteRepository.save(cliente);
    }
    public void eliminar(Long id) {
        clienteRepository.deleteById(id);
    }
    public void actualizar(Long id, Cliente clienteActualizado) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

        clienteExistente.setNombre(clienteActualizado.getNombre());
        clienteExistente.setCategoriaCliente(clienteActualizado.getCategoriaCliente());
        clienteExistente.setTipoCliente(clienteActualizado.getTipoCliente());

        clienteRepository.save(clienteExistente);
    }

    public List<Cliente>buscarPorTipoCliente(TipoCliente tipo) {

        return clienteRepository.findByTipoCliente(tipo);
    }

}
