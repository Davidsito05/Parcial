package com.adopcionmascotas.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adopcionmascotas.app.model.Adopcion;
import com.adopcionmascotas.app.repository.AdopcionRepository;

@Service
@Transactional
public class AdopcionServiceImpl implements AdopcionService {

    private final AdopcionRepository adopcionRepo;

    public AdopcionServiceImpl(AdopcionRepository adopcionRepo) {
        this.adopcionRepo = adopcionRepo;
    }

    @Override
    public List<Adopcion> findAll() {
        return adopcionRepo.findAll();
    }

    @Override
    public Adopcion findById(Long id) {
        Optional<Adopcion> opt = adopcionRepo.findById(id);
        return opt.orElseThrow(() -> new RuntimeException("Adopción no encontrada con id " + id));
    }

    @Override
    public Adopcion save(Adopcion adopcion) {
        return adopcionRepo.save(adopcion);
    }

    @Override
    public void deleteById(Long id) {
        adopcionRepo.deleteById(id);
    }
}