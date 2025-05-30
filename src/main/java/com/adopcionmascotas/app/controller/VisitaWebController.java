package com.adopcionmascotas.app.controller;

import com.adopcionmascotas.app.model.Visita;
import com.adopcionmascotas.app.model.Usuario;
import com.adopcionmascotas.app.service.VisitaService;
import com.adopcionmascotas.app.service.UsuarioService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class VisitaWebController {

    private final VisitaService visitaService;
    private final UsuarioService usuarioService;

    public VisitaWebController(VisitaService visitaService, UsuarioService usuarioService) {
        this.visitaService = visitaService;
        this.usuarioService = usuarioService;
    }

    // Listar visitas
    @GetMapping("/visitas")
    public String listar(Model model) {
        List<Visita> visitas = visitaService.findAll();
        model.addAttribute("visitas", visitas);
        return "visitas/listar";
    }

    // Formulario nuevo
    @GetMapping("/visitas/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("visita", new Visita());

        // Solo usuarios voluntarios para seleccionar
        List<Usuario> voluntarios = usuarioService.findAll()
            .stream()
            .filter(u -> "VOLUNTARIO".equalsIgnoreCase(u.getRol()))
            .collect(Collectors.toList());
        model.addAttribute("voluntarios", voluntarios);

        return "visitas/form";
    }

    // Formulario editar
    @GetMapping("/visitas/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Visita visita = visitaService.findById(id);
        if (visita == null) {
            return "redirect:/visitas";
        }
        model.addAttribute("visita", visita);

        List<Usuario> voluntarios = usuarioService.findAll()
            .stream()
            .filter(u -> "VOLUNTARIO".equalsIgnoreCase(u.getRol()))
            .collect(Collectors.toList());
        model.addAttribute("voluntarios", voluntarios);

        return "visitas/form";
    }

    // Guardar visita
    @PostMapping("/visitas")
    public String guardar(@ModelAttribute Visita visita) {
        visitaService.save(visita);
        return "redirect:/visitas";
    }

    // Eliminar visita
    @GetMapping("/visitas/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        visitaService.deleteById(id);
        return "redirect:/visitas";
    }
}