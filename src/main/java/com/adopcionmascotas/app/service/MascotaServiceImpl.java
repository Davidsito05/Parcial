package com.adopcionmascotas.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adopcionmascotas.app.model.Mascota;
import com.adopcionmascotas.app.repository.MascotaRepository;

@Service
@Transactional
public class MascotaServiceImpl implements MascotaService {

    private final MascotaRepository mascotaRepo;

    public MascotaServiceImpl(MascotaRepository mascotaRepo) {
        this.mascotaRepo = mascotaRepo;
    }

    @Override
    public List<Mascota> findAll() {
        return mascotaRepo.findAll();
    }

    @Override
    public Mascota findById(Long id) {
        Optional<Mascota> opt = mascotaRepo.findById(id);
        return opt.orElseThrow(() -> new RuntimeException("Mascota no encontrada con id " + id));
    }

    @Override
    public Mascota save(Mascota mascota) {
        return mascotaRepo.save(mascota);
    }

    @Override
    public void deleteById(Long id) {
        mascotaRepo.deleteById(id);
    }
}