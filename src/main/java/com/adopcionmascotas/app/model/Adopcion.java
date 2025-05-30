package com.adopcionmascotas.app.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "adopcion")
public class Adopcion {

    // ====================
    // Campos simples
    // ====================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaSolicitud;
    private LocalDateTime fechaAprobacion;
    private String estado;  // e.g. "pendiente", "aprobada", "rechazada"

    // ====================
    // Relaciones
    // ====================
    @ManyToOne
    @JoinColumn(name = "mascota_id", nullable = false)
    private Mascota mascota;

    @ManyToOne
    @JoinColumn(name = "adoptante_id", nullable = false)
    private Usuario adoptante;

    // ====================
    // Getters y Setters
    // ====================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public LocalDateTime getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(LocalDateTime fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Usuario getAdoptante() {
        return adoptante;
    }

    public void setAdoptante(Usuario adoptante) {
        this.adoptante = adoptante;
    }
}