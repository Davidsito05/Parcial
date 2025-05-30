package com.adopcionmascotas.app.controller;

import com.adopcionmascotas.app.model.Adopcion;
import com.adopcionmascotas.app.model.Mascota;
import com.adopcionmascotas.app.model.Usuario;
import com.adopcionmascotas.app.service.AdopcionService;
import com.adopcionmascotas.app.service.MascotaService;
import com.adopcionmascotas.app.service.UsuarioService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AdopcionWebController {

    private final AdopcionService adopcionService;
    private final MascotaService mascotaService;
    private final UsuarioService usuarioService;

    public AdopcionWebController(AdopcionService adopcionService,
                                MascotaService mascotaService,
                                UsuarioService usuarioService) {
        this.adopcionService = adopcionService;
        this.mascotaService = mascotaService;
        this.usuarioService = usuarioService;
    }

    // Listado de adopciones
    @GetMapping("/adopciones")
    public String listar(Model model) {
        List<Adopcion> adopciones = adopcionService.findAll();
        model.addAttribute("adopciones", adopciones);
        return "adopciones/listar";
    }

    // Formulario para nueva adopción
    @GetMapping("/adopciones/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("adopcion", new Adopcion());

        // Solo mascotas disponibles
        List<Mascota> mascotasDisponibles = mascotaService.findAll()
            .stream()
            .filter(m -> "disponible".equalsIgnoreCase(m.getEstado()))
            .collect(Collectors.toList());
        model.addAttribute("mascotas", mascotasDisponibles);

        // Solo usuarios con rol ADOPTANTE
        List<Usuario> adoptantes = usuarioService.findAll()
            .stream()
            .filter(u -> "ADOPTANTE".equalsIgnoreCase(u.getRol()))
            .collect(Collectors.toList());
        model.addAttribute("adoptantes", adoptantes);

        return "adopciones/form";
    }

    // Formulario edición adopción
    @GetMapping("/adopciones/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Adopcion adopcion = adopcionService.findById(id);
        if (adopcion == null) {
            return "redirect:/adopciones";
        }
        model.addAttribute("adopcion", adopcion);

        List<Mascota> mascotasDisponibles = mascotaService.findAll();
        model.addAttribute("mascotas", mascotasDisponibles);

        List<Usuario> adoptantes = usuarioService.findAll()
            .stream()
            .filter(u -> "ADOPTANTE".equalsIgnoreCase(u.getRol()))
            .collect(Collectors.toList());
        model.addAttribute("adoptantes", adoptantes);

        return "adopciones/form";
    }

    // Guardar adopción (nuevo o editado)
    @PostMapping("/adopciones")
    public String guardar(@ModelAttribute Adopcion adopcion) {
        adopcionService.save(adopcion);
        return "redirect:/adopciones";
    }

    // Eliminar adopción
    @GetMapping("/adopciones/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        adopcionService.deleteById(id);
        return "redirect:/adopciones";
    }
}