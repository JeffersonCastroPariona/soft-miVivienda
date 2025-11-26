package com.upc.ep.security.services;

import com.upc.ep.security.entities.Role;
import com.upc.ep.security.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

  @Autowired
  private RoleRepository roleRepository;

  @Override
  public void run(String... args) throws Exception {
    // Crear rol CLIENTE si no existe
    if (roleRepository.findByName("ROLE_CLIENTE").isEmpty()) {
      Role clienteRole = new Role();
      clienteRole.setName("ROLE_CLIENTE");
      roleRepository.save(clienteRole);
      System.out.println("Rol ROLE_CLIENTE creado.");
    }

    // Crear rol ASESOR si no existe
    if (roleRepository.findByName("ROLE_ASESOR").isEmpty()) {
      Role asesorRole = new Role();
      asesorRole.setName("ROLE_ASESOR");
      roleRepository.save(asesorRole);
      System.out.println("Rol ROLE_ASESOR creado.");
    }
  }
}