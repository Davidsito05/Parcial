package com.adopcionmascotas.app.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "visita")
public class Visita {

    // ====================
    // Campos simples
    // ====================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaHora;

    @Column(length = 1000)
    private String notas;

    // ====================
    // Relaciones
    // ====================
    @ManyToOne
    @JoinColumn(name = "mascota_id", nullable = false)
    private Mascota mascota;

    @ManyToOne
    @JoinColumn(name = "voluntario_id", nullable = false)
    private Usuario voluntario;

    // ====================
    // Getters y Setters
    // ====================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Usuario getVoluntario() {
        return voluntario;
    }

    public void setVoluntario(Usuario voluntario) {
        this.voluntario = voluntario;
    }
}