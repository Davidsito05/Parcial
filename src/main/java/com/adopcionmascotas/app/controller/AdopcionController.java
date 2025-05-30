package com.adopcionmascotas.app.controller;

import com.adopcionmascotas.app.model.Adopcion;
import com.adopcionmascotas.app.service.AdopcionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adopciones")
public class AdopcionController {

    private final AdopcionService adopcionService;

    public AdopcionController(AdopcionService adopcionService) {
        this.adopcionService = adopcionService;
    }

    // Listar todas las solicitudes de adopción
    @GetMapping
    public ResponseEntity<List<Adopcion>> getAll() {
        List<Adopcion> lista = adopcionService.findAll();
        return ResponseEntity.ok(lista);
    }

    // Obtener solicitud de adopción por ID
    @GetMapping("/{id}")
    public ResponseEntity<Adopcion> getById(@PathVariable Long id) {
        Adopcion adopcion = adopcionService.findById(id);
        return ResponseEntity.ok(adopcion);
    }

    // Crear una nueva solicitud de adopción
    @PostMapping
    public ResponseEntity<Adopcion> create(@RequestBody Adopcion adopcion) {
        // fechaSolicitud debe establecerse al momento de creación
        adopcion.setFechaSolicitud(java.time.LocalDateTime.now());
        Adopcion creada = adopcionService.save(adopcion);
        return ResponseEntity.ok(creada);
    }

    // Actualizar una solicitud de adopción existente
    @PutMapping("/{id}")
    public ResponseEntity<Adopcion> update(
            @PathVariable Long id,
            @RequestBody Adopcion detalles) {

        Adopcion existente = adopcionService.findById(id);
        // No cambiamos el ID ni la fechaSolicitud original
        existente.setEstado(detalles.getEstado());
        existente.setFechaAprobacion(detalles.getFechaAprobacion());
        // si se actualizan relaciones:
        existente.setMascota(detalles.getMascota());
        existente.setAdoptante(detalles.getAdoptante());

        Adopcion actualizada = adopcionService.save(existente);
        return ResponseEntity.ok(actualizada);
    }

    // Eliminar una solicitud de adopción
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        adopcionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}