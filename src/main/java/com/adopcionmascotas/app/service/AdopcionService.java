package com.adopcionmascotas.app.service;

import java.util.List;
import com.adopcionmascotas.app.model.Adopcion;

public interface AdopcionService {
    List<Adopcion> findAll();
    Adopcion findById(Long id);
    Adopcion save(Adopcion adopcion);
    void deleteById(Long id);
}