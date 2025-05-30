package com.adopcionmascotas.app.controller;

import com.adopcionmascotas.app.model.Mascota;
import com.adopcionmascotas.app.model.RegistroVeterinario;
import com.adopcionmascotas.app.model.Usuario;
import com.adopcionmascotas.app.service.MascotaService;
import com.adopcionmascotas.app.service.RegistroVeterinarioService;
import com.adopcionmascotas.app.service.UsuarioService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class RegistroVeterinarioWebController {

    private final RegistroVeterinarioService registroService;
    private final MascotaService mascotaService;
    private final UsuarioService usuarioService;

    public RegistroVeterinarioWebController(RegistroVeterinarioService registroService,
                                            MascotaService mascotaService,
                                            UsuarioService usuarioService) {
        this.registroService = registroService;
        this.mascotaService = mascotaService;
        this.usuarioService = usuarioService;
    }

    // Listado de registros
    @GetMapping("/registros")
    public String listar(Model model) {
        List<RegistroVeterinario> registros = registroService.findAll();
        model.addAttribute("registros", registros);
        return "registros/listar";
    }

    // Formulario nuevo registro
    @GetMapping("/registros/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("registro", new RegistroVeterinario());

        // Solo mascotas para elegir
        List<Mascota> mascotas = mascotaService.findAll();
        model.addAttribute("mascotas", mascotas);

        // Solo usuarios con rol VETERINARIO
        List<Usuario> veterinarios = usuarioService.findAll()
                .stream()
                .filter(u -> "VETERINARIO".equalsIgnoreCase(u.getRol()))
                .collect(Collectors.toList());
        model.addAttribute("veterinarios", veterinarios);

        return "registros/form";
    }

    // Formulario edición
    @GetMapping("/registros/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        RegistroVeterinario registro = registroService.findById(id);
        if (registro == null) {
            return "redirect:/registros";
        }
        model.addAttribute("registro", registro);

        List<Mascota> mascotas = mascotaService.findAll();
        model.addAttribute("mascotas", mascotas);

        List<Usuario> veterinarios = usuarioService.findAll()
                .stream()
                .filter(u -> "VETERINARIO".equalsIgnoreCase(u.getRol()))
                .collect(Collectors.toList());
        model.addAttribute("veterinarios", veterinarios);

        return "registros/form";
    }

    // Guardar registro
    @PostMapping("/registros")
    public String guardar(@ModelAttribute RegistroVeterinario registro) {
        registroService.save(registro);
        return "redirect:/registros";
    }

    // Eliminar registro
    @GetMapping("/registros/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        registroService.deleteById(id);
        return "redirect:/registros";
    }
}