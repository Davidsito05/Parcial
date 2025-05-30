package com.adopcionmascotas.app.controller;

import com.adopcionmascotas.app.model.Mascota;
import com.adopcionmascotas.app.model.Usuario;
import com.adopcionmascotas.app.service.MascotaService;
import com.adopcionmascotas.app.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MascotaWebController {

    private final MascotaService mascotaService;
    private final UsuarioService usuarioService;

    public MascotaWebController(MascotaService mascotaService, UsuarioService usuarioService) {
        this.mascotaService = mascotaService;
        this.usuarioService = usuarioService;
    }

    // Listado de mascotas
    @GetMapping("/mascotas")
    public String listar(Model m) {
        List<Mascota> mascotas = mascotaService.findAll();
        m.addAttribute("mascotas", mascotas);
        return "mascotas/listar";
    }

    // Formulario creación
    @GetMapping("/mascotas/nuevo")
    public String nuevo(Model m) {
        Mascota mascota = new Mascota();
        m.addAttribute("mascota", mascota);
        // traer solo usuarios con rol REFUGIO
        List<Usuario> refugios = usuarioService.findAll()
                .stream()
                .filter(u -> "REFUGIO".equalsIgnoreCase(u.getRol()))
                .collect(Collectors.toList());
        m.addAttribute("refugios", refugios);
        return "mascotas/form";
    }

    // Formulario edición
    @GetMapping("/mascotas/editar/{id}")
    public String editar(@PathVariable Long id, Model m) {
        Mascota mascota = mascotaService.findById(id);
        if (mascota == null) {
            // Aquí puedes redirigir o lanzar excepción si no existe la mascota
            // Por ejemplo, redirigir al listado
            return "redirect:/mascotas";
        }
        m.addAttribute("mascota", mascota);
        List<Usuario> refugios = usuarioService.findAll()
                .stream()
                .filter(u -> "REFUGIO".equalsIgnoreCase(u.getRol()))
                .collect(Collectors.toList());
        m.addAttribute("refugios", refugios);
        return "mascotas/form";
    }

    // Procesar creación/edición
    @PostMapping("/mascotas")
    public String guardar(@ModelAttribute Mascota mascota) {
        mascotaService.save(mascota);
        return "redirect:/mascotas";
    }

    // Eliminar
    @GetMapping("/mascotas/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        mascotaService.deleteById(id);
        return "redirect:/mascotas";
    }
}