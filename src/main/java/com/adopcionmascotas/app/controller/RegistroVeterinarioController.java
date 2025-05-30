package com.adopcionmascotas.app.controller;

import com.adopcionmascotas.app.model.RegistroVeterinario;
import com.adopcionmascotas.app.service.RegistroVeterinarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registros")
public class RegistroVeterinarioController {

    private final RegistroVeterinarioService registroService;

    public RegistroVeterinarioController(RegistroVeterinarioService registroService) {
        this.registroService = registroService;
    }

    // Listar todos los registros veterinarios
    @GetMapping
    public ResponseEntity<List<RegistroVeterinario>> getAll() {
        List<RegistroVeterinario> lista = registroService.findAll();
        return ResponseEntity.ok(lista);
    }

    // Obtener registro por ID
    @GetMapping("/{id}")
    public ResponseEntity<RegistroVeterinario> getById(@PathVariable Long id) {
        RegistroVeterinario registro = registroService.findById(id);
        return ResponseEntity.ok(registro);
    }

    // Crear un nuevo registro veterinario
    @PostMapping
    public ResponseEntity<RegistroVeterinario> create(@RequestBody RegistroVeterinario registro) {
        registro.setFechaConsulta(java.time.LocalDateTime.now());
        RegistroVeterinario creado = registroService.save(registro);
        return ResponseEntity.ok(creado);
    }

    // Actualizar un registro existente
    @PutMapping("/{id}")
    public ResponseEntity<RegistroVeterinario> update(
            @PathVariable Long id,
            @RequestBody RegistroVeterinario detalles) {

        RegistroVeterinario existente = registroService.findById(id);
        existente.setDiagnostico(detalles.getDiagnostico());
        existente.setTratamiento(detalles.getTratamiento());
        existente.setFechaConsulta(detalles.getFechaConsulta());
        existente.setMascota(detalles.getMascota());
        existente.setVeterinario(detalles.getVeterinario());

        RegistroVeterinario actualizado = registroService.save(existente);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar un registro veterinario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        registroService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}