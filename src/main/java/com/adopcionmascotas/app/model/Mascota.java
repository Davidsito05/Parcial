package com.adopcionmascotas.app.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "mascota")
public class Mascota {

    // ====================
    // Campos simples
    // ====================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String especie;
    private String raza;
    private Integer edad;
    private String estado;  // e.g. "disponible", "adoptada"

    // ====================
    // Relaciones
    // ====================
    @ManyToOne
    @JoinColumn(name = "refugio_id", nullable = false)
    private Usuario refugio;

    @OneToMany(mappedBy = "mascota")
    private List<Adopcion> solicitudesAdopcion;

    @OneToMany(mappedBy = "mascota")
    private List<RegistroVeterinario> historialMedico;

    @OneToMany(mappedBy = "mascota")
    private List<Visita> visitas;

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

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Usuario getRefugio() {
        return refugio;
    }

    public void setRefugio(Usuario refugio) {
        this.refugio = refugio;
    }

    public List<Adopcion> getSolicitudesAdopcion() {
        return solicitudesAdopcion;
    }

    public void setSolicitudesAdopcion(List<Adopcion> solicitudesAdopcion) {
        this.solicitudesAdopcion = solicitudesAdopcion;
    }

    public List<RegistroVeterinario> getHistorialMedico() {
        return historialMedico;
    }

    public void setHistorialMedico(List<RegistroVeterinario> historialMedico) {
        this.historialMedico = historialMedico;
    }

    public List<Visita> getVisitas() {
        return visitas;
    }

    public void setVisitas(List<Visita> visitas) {
        this.visitas = visitas;
    }
}