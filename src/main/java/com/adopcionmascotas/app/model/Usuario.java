package com.adopcionmascotas.app.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario {

    // ====================
    // Campos simples
    // ====================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    private String rol;  // e.g. ADMIN, REFUGIO, ADOPTANTE, VETERINARIO, VOLUNTARIO

    // ====================
    // Relaciones
    // ====================
    @OneToMany(mappedBy = "refugio")
    private List<Mascota> mascotasPublicadas;

    @OneToMany(mappedBy = "adoptante")
    private List<Adopcion> adopciones;

    @OneToMany(mappedBy = "veterinario")
    private List<RegistroVeterinario> registrosMedicos;

    @OneToMany(mappedBy = "voluntario")
    private List<Visita> visitasProgramadas;


    // ====================
    // Getters y Setters
    // ====================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public List<Mascota> getMascotasPublicadas() {
        return mascotasPublicadas;
    }

    public void setMascotasPublicadas(List<Mascota> mascotasPublicadas) {
        this.mascotasPublicadas = mascotasPublicadas;
    }

    public List<Adopcion> getAdopciones() {
        return adopciones;
    }

    public void setAdopciones(List<Adopcion> adopciones) {
        this.adopciones = adopciones;
    }

    public List<RegistroVeterinario> getRegistrosMedicos() {
        return registrosMedicos;
    }

    public void setRegistrosMedicos(List<RegistroVeterinario> registrosMedicos) {
        this.registrosMedicos = registrosMedicos;
    }

    public List<Visita> getVisitasProgramadas() {
        return visitasProgramadas;
    }

    public void setVisitasProgramadas(List<Visita> visitasProgramadas) {
        this.visitasProgramadas = visitasProgramadas;
    }
}