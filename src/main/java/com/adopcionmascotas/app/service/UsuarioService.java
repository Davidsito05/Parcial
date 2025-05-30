package com.adopcionmascotas.app.service;

import java.util.List;
import com.adopcionmascotas.app.model.Usuario;

public interface UsuarioService {
    List<Usuario> findAll();
    Usuario findById(Long id);
    Usuario save(Usuario usuario);
    void deleteById(Long id);

    // Nuevo método para filtrar usuarios por rol
    List<Usuario> findByRol(String rol);
}