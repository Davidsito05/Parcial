package com.adopcionmascotas.app.controller;

import com.adopcionmascotas.app.model.Mascota;
import com.adopcionmascotas.app.service.MascotaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaController {

    private final MascotaService mascotaService;

    public MascotaController(MascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }

    // Listar todas las mascotas
    @GetMapping
    public ResponseEntity<List<Mascota>> getAll() {
        List<Mascota> mascotas = mascotaService.findAll();
        return ResponseEntity.ok(mascotas);
    }

    // Obtener mascota por ID
    @GetMapping("/{id}")
    public ResponseEntity<Mascota> getById(@PathVariable Long id) {
        Mascota mascota = mascotaService.findById(id);
        return ResponseEntity.ok(mascota);
    }

    // Crear una nueva mascota
    @PostMapping
    public ResponseEntity<Mascota> create(@RequestBody Mascota mascota) {
        Mascota creada = mascotaService.save(mascota);
        return ResponseEntity.ok(creada);
    }

    // Actualizar una mascota existente
    @PutMapping("/{id}")
    public ResponseEntity<Mascota> update(
            @PathVariable Long id,
            @RequestBody Mascota detalles) {

        Mascota existente = mascotaService.findById(id);
        existente.setNombre(detalles.getNombre());
        existente.setEspecie(detalles.getEspecie());
        existente.setRaza(detalles.getRaza());
        existente.setEdad(detalles.getEdad());
        existente.setEstado(detalles.getEstado());
        existente.setRefugio(detalles.getRefugio());
        // relaciones (solicitudesAdopcion, historialMedico, visitas) se gestionan en sus propios endpoints

        Mascota actualizada = mascotaService.save(existente);
        return ResponseEntity.ok(actualizada);
    }

    // Eliminar una mascota
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        mascotaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}