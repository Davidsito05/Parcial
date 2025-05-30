package com.adopcionmascotas.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.adopcionmascotas.app.model.Mascota;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {
}