package com.upc.ep.services;

import com.upc.ep.DTO.ClienteDTO;
import com.upc.ep.entities.Cliente;

import java.util.List;

public interface ClienteService {
    public Cliente saveCliente(Cliente cliente);
    public List<Cliente> listarC();
    ClienteDTO actualizarCliente(Long id, ClienteDTO clienteDTO);
}
