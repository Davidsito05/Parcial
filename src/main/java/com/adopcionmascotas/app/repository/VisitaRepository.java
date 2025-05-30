package com.adopcionmascotas.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.adopcionmascotas.app.model.Visita;

@Repository
public interface VisitaRepository extends JpaRepository<Visita, Long> {
}