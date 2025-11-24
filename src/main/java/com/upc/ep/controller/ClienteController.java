package com.upc.ep.controller;


import com.upc.ep.DTO.AsesorDTO;
import com.upc.ep.DTO.ClienteDTO;
import com.upc.ep.entities.Asesor;
import com.upc.ep.entities.Cliente;
import com.upc.ep.services.AsesorService;
import com.upc.ep.services.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/CrediHome")
@CrossOrigin(
        origins = "http://localhost:4200",
        allowCredentials = "true",
        exposedHeaders = "Authorization",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE},
        allowedHeaders = "*"
)
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/cliente")
    @PreAuthorize("hasRole('CLIENTE')")
    public ClienteDTO saveCliente(@RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
        cliente = clienteService.saveCliente(cliente);
        return modelMapper.map(cliente, ClienteDTO.class);
    }

    @GetMapping("/clientes")
    @PreAuthorize("hasRole('CLIENTE')")
    public List<ClienteDTO> listarC() {
        List<Cliente> clientes = clienteService.listarC();
        ModelMapper modelMapper = new ModelMapper();
        return clientes.stream()
                .map(cliente -> modelMapper.map(cliente, ClienteDTO.class))
                .collect(Collectors.toList());
    }

    @PutMapping("/cliente/modificar/{id}")
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<ClienteDTO> actualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO actualizado = clienteService.actualizarCliente(id, clienteDTO);
        return new ResponseEntity<>(actualizado, HttpStatus.OK);
    }
}
