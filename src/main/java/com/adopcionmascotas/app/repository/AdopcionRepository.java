package com.adopcionmascotas.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.adopcionmascotas.app.model.Adopcion;

@Repository
public interface AdopcionRepository extends JpaRepository<Adopcion, Long> {
}