package com.adopcionmascotas.app.controller;

import com.adopcionmascotas.app.model.Usuario;
import com.adopcionmascotas.app.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Listar todos los usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> getAll() {
        List<Usuario> usuarios = usuarioService.findAll();
        return ResponseEntity.ok(usuarios);
    }

    // Obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Long id) {
        Usuario usuario = usuarioService.findById(id);
        return ResponseEntity.ok(usuario);
    }

    // Crear un nuevo usuario
    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
        Usuario creado = usuarioService.save(usuario);
        return ResponseEntity.ok(creado);
    }

    // Actualizar un usuario existente
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(
            @PathVariable Long id,
            @RequestBody Usuario usuarioDetalles) {

        Usuario existente = usuarioService.findById(id);
        existente.setNombre(usuarioDetalles.getNombre());
        existente.setEmail(usuarioDetalles.getEmail());
        existente.setContrasena(usuarioDetalles.getContrasena());
        existente.setRol(usuarioDetalles.getRol());
        // No tocamos listas de relaciones aquí; se gestionan por otros endpoints si es necesario

        Usuario actualizado = usuarioService.save(existente);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar un usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}