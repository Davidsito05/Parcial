package com.adopcionmascotas.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.adopcionmascotas.app.model.RegistroVeterinario;

@Repository
public interface RegistroVeterinarioRepository extends JpaRepository<RegistroVeterinario, Long> {
}