package com.adopcionmascotas.app.controller;

import com.adopcionmascotas.app.model.Usuario;
import com.adopcionmascotas.app.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsuarioWebController {

    private final UsuarioService usuarioService;

    public UsuarioWebController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Mostrar listado de usuarios
    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.findAll());
        return "usuarios/listar";   // Thymeleaf buscará templates/usuarios/listar.html
    }

    // Mostrar formulario para crear un usuario
    @GetMapping("/usuarios/nuevo")
    public String nuevoUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuarios/form";     // templates/usuarios/form.html
    }

    // Mostrar formulario para editar un usuario existente
    @GetMapping("/usuarios/editar/{id}")
    public String editarUsuario(@PathVariable Long id, Model model) {
        model.addAttribute("usuario", usuarioService.findById(id));
        return "usuarios/form";
    }

    // Procesar el envío del formulario (creación o actualización)
    @PostMapping("/usuarios")
    public String guardarUsuario(@ModelAttribute Usuario usuario) {
        usuarioService.save(usuario);
        return "redirect:/usuarios";
    }

    // Eliminar un usuario
    @GetMapping("/usuarios/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return "redirect:/usuarios";
    }
}