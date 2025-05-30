package com.adopcionmascotas.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adopcionmascotas.app.model.Usuario;
import com.adopcionmascotas.app.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepo;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepo.findAll();
    }

    @Override
    public Usuario findById(Long id) {
        return usuarioRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + id));
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepo.save(usuario);
    }

    @Override
    public void deleteById(Long id) {
        usuarioRepo.deleteById(id);
    }

    @Override
    public List<Usuario> findByRol(String rol) {
        // Filtra usuarios por rol (case insensitive)
        return usuarioRepo.findAll()
                .stream()
                .filter(u -> u.getRol() != null && u.getRol().equalsIgnoreCase(rol))
                .collect(Collectors.toList());
    }
}