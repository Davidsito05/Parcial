package com.adopcionmascotas.app.service;

import java.util.List;
import com.adopcionmascotas.app.model.RegistroVeterinario;

public interface RegistroVeterinarioService {
    List<RegistroVeterinario> findAll();
    RegistroVeterinario findById(Long id);
    RegistroVeterinario save(RegistroVeterinario registro);
    void deleteById(Long id);
}