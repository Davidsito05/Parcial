package com.adopcionmascotas.app.service;

import java.util.List;
import com.adopcionmascotas.app.model.Visita;

public interface VisitaService {
    List<Visita> findAll();
    Visita findById(Long id);
    Visita save(Visita visita);
    void deleteById(Long id);
}