package com.bezkoder.springjwt.controladores;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/test")
public class TestController {
  @GetMapping("/all")
  public String allAccess() {
    System.out.println("Acceso publico a la ruta /api/test/all");  
    return "Contenido publico";
  }

  @GetMapping("/user")
  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
  public String userAccess() {
    System.out.println("Acceso privado a la ruta /api/test/user");
    return "Contenido privado. Datos retornados para el api de usuarios.";
  }

  @GetMapping("/mod")
  @PreAuthorize("hasRole('MODERATOR')")
  public String moderatorAccess() {
    System.out.println("Acceso privado a la ruta /api/test/mod");
    return "Contenido privado. Datos retornados para el api de moderador.";
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ADMIN')")
  public String adminAccess() {
    System.out.println("Acceso privado a la ruta /api/test/admin");
    return "Contenido privado. Datos retornados para el api de administrador.";
  }
}
