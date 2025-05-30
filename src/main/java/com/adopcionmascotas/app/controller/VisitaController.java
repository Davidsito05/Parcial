package com.adopcionmascotas.app.controller;

import com.adopcionmascotas.app.model.Visita;
import com.adopcionmascotas.app.service.VisitaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visitas")
public class VisitaController {

    private final VisitaService visitaService;

    public VisitaController(VisitaService visitaService) {
        this.visitaService = visitaService;
    }

    // Listar todas las visitas
    @GetMapping
    public ResponseEntity<List<Visita>> getAll() {
        List<Visita> lista = visitaService.findAll();
        return ResponseEntity.ok(lista);
    }

    // Obtener visita por ID
    @GetMapping("/{id}")
    public ResponseEntity<Visita> getById(@PathVariable Long id) {
        Visita visita = visitaService.findById(id);
        return ResponseEntity.ok(visita);
    }

    // Crear una nueva visita
    @PostMapping
    public ResponseEntity<Visita> create(@RequestBody Visita visita) {
        visita.setFechaHora(java.time.LocalDateTime.now());
        Visita creada = visitaService.save(visita);
        return ResponseEntity.ok(creada);
    }

    // Actualizar una visita existente
    @PutMapping("/{id}")
    public ResponseEntity<Visita> update(
            @PathVariable Long id,
            @RequestBody Visita detalles) {

        Visita existente = visitaService.findById(id);
        existente.setNotas(detalles.getNotas());
        existente.setFechaHora(detalles.getFechaHora());
        existente.setMascota(detalles.getMascota());
        existente.setVoluntario(detalles.getVoluntario());

        Visita actualizada = visitaService.save(existente);
        return ResponseEntity.ok(actualizada);
    }

    // Eliminar una visita
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        visitaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}