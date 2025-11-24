package com.upc.ep.impl;

import com.upc.ep.DTO.AsesorDTO;
import com.upc.ep.DTO.ClienteDTO;
import com.upc.ep.entities.Asesor;
import com.upc.ep.entities.Cliente;
import com.upc.ep.repositories.AsesorRepos;
import com.upc.ep.repositories.ClienteRepos;
import com.upc.ep.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClienteIMPL implements ClienteService {
    @Autowired
    private ClienteRepos clienteRepos;

    @Override
    public Cliente saveCliente(Cliente cliente) {
        return clienteRepos.save(cliente);
    }

    @Override
    public List<Cliente> listarC() {
        return clienteRepos.findAll();
    }

    @Override
    public ClienteDTO actualizarCliente(Long id, ClienteDTO clienteDTO) {
        Cliente cliente = clienteRepos.findById(id)
                .orElseThrow(() -> new RuntimeException("cliente no encontrado con ID: " + id));

        cliente.setNombre(clienteDTO.getNombre());
        cliente.setDni(clienteDTO.getDni());
        cliente.setCorreo(clienteDTO.getCorreo());
        cliente.setIngresoMensual(clienteDTO.getIngresoMensual());
        cliente.setGastoMensual(clienteDTO.getGastoMensual());
        cliente.setOcupacion(clienteDTO.getOcupacion());

        Cliente actualizado = clienteRepos.save(cliente);

        ClienteDTO dtoActualizado = new ClienteDTO();
        dtoActualizado.setNombre(actualizado.getNombre());
        dtoActualizado.setDni(actualizado.getDni());
        dtoActualizado.setCorreo(actualizado.getCorreo());
        dtoActualizado.setIngresoMensual(actualizado.getIngresoMensual());
        dtoActualizado.setGastoMensual(actualizado.getGastoMensual());
        dtoActualizado.setOcupacion(actualizado.getOcupacion());


        return dtoActualizado;
    }

}

