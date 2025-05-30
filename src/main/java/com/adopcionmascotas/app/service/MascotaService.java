package com.adopcionmascotas.app.service;

import java.util.List;
import com.adopcionmascotas.app.model.Mascota;

public interface MascotaService {
    List<Mascota> findAll();
    Mascota findById(Long id);
    Mascota save(Mascota mascota);
    void deleteById(Long id);
}