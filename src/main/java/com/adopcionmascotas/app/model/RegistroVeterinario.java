package com.adopcionmascotas.app.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "registro_veterinario")
public class RegistroVeterinario {

    // ====================
    // Campos simples
    // ====================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaConsulta;

    @Column(length = 1000)
    private String diagnostico;

    @Column(length = 1000)
    private String tratamiento;

    // ====================
    // Relaciones
    // ====================
    @ManyToOne
    @JoinColumn(name = "mascota_id", nullable = false)
    private Mascota mascota;

    @ManyToOne
    @JoinColumn(name = "veterinario_id", nullable = false)
    private Usuario veterinario;

    // ====================
    // Getters y Setters
    // ====================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(LocalDateTime fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Usuario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Usuario veterinario) {
        this.veterinario = veterinario;
    }
}
