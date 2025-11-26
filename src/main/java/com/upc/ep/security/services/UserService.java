package com.upc.ep.security.services;

import com.upc.ep.security.entities.Role; // --- AÑADIDO ---
import com.upc.ep.security.entities.User;
import com.upc.ep.security.repositories.RoleRepository; // --- AÑADIDO ---
import com.upc.ep.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public User save(User user) {
        Set<Role> managedRoles = new HashSet<>();

        for (Role role : user.getRoles()) {
            Role managedRole = roleRepository.findByName(role.getName())
                .orElseThrow(() -> new RuntimeException("Error: El rol '" + role.getName() + "' no fue encontrado en la base de datos."));
            managedRoles.add(managedRole);
        }

        user.setRoles(managedRoles);

        return userRepository.save(user);
    }

    public Integer insertUserRol(Long user_id, Long rol_id) {
        userRepository.insertUserRol(user_id, rol_id);
        return 1;
    }
}