package com.adopcionmascotas.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adopcionmascotas.app.model.RegistroVeterinario;
import com.adopcionmascotas.app.repository.RegistroVeterinarioRepository;

@Service
@Transactional
public class RegistroVeterinarioServiceImpl implements RegistroVeterinarioService {

    private final RegistroVeterinarioRepository registroRepo;

    public RegistroVeterinarioServiceImpl(RegistroVeterinarioRepository registroRepo) {
        this.registroRepo = registroRepo;
    }

    @Override
    public List<RegistroVeterinario> findAll() {
        return registroRepo.findAll();
    }

    @Override
    public RegistroVeterinario findById(Long id) {
        Optional<RegistroVeterinario> opt = registroRepo.findById(id);
        return opt.orElseThrow(() -> new RuntimeException("Registro veterinario no encontrado con id " + id));
    }

    @Override
    public RegistroVeterinario save(RegistroVeterinario registro) {
        return registroRepo.save(registro);
    }

    @Override
    public void deleteById(Long id) {
        registroRepo.deleteById(id);
    }
}