package com.adopcionmascotas.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adopcionmascotas.app.model.Visita;
import com.adopcionmascotas.app.repository.VisitaRepository;

@Service
@Transactional
public class VisitaServiceImpl implements VisitaService {

    private final VisitaRepository visitaRepo;

    public VisitaServiceImpl(VisitaRepository visitaRepo) {
        this.visitaRepo = visitaRepo;
    }

    @Override
    public List<Visita> findAll() {
        return visitaRepo.findAll();
    }

    @Override
    public Visita findById(Long id) {
        Optional<Visita> opt = visitaRepo.findById(id);
        return opt.orElseThrow(() -> new RuntimeException("Visita no encontrada con id " + id));
    }

    @Override
    public Visita save(Visita visita) {
        return visitaRepo.save(visita);
    }

    @Override
    public void deleteById(Long id) {
        visitaRepo.deleteById(id);
    }
}