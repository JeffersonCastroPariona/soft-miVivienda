package com.upc.ep.security.controllers;

import com.upc.ep.security.entities.User;
import com.upc.ep.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "${ip.frontend}", allowCredentials = "true", exposedHeaders = "Authorization")
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder bcrypt;

    // --- CORREGIDO ---
    // Ahora devuelve el usuario creado para que el frontend obtenga el ID.
    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        String bcryptPassword = bcrypt.encode(user.getPassword());
        user.setPassword(bcryptPassword);
        // Se captura el usuario guardado, que ya tiene el ID asignado por la base de datos.
        User savedUser = userService.save(user);
        // Se devuelve el usuario completo en la respuesta con un estado 201 CREATED.
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/save/{user_id}/{rol_id}")
    public ResponseEntity<Integer> saveUseRol(@PathVariable("user_id") Long user_id,
                                              @PathVariable("rol_id") Long rol_id){
        return new ResponseEntity<>(userService.insertUserRol(user_id, rol_id), HttpStatus.OK);
    }
}