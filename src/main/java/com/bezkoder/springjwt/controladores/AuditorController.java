package com.bezkoder.springjwt.controladores;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/auditor")
public class AuditorController {

    public record ReportDTO(Long id, String titulo, String estado, LocalDateTime fecha) {}

    @GetMapping("/reportes")
    @PreAuthorize("hasRole('AUDITOR') or hasRole('ADMIN')")
    public List<ReportDTO> listarReportes() {
        System.out.println("Acceso privado a la ruta /api/auditor/reportes");
        return List.of(
            new ReportDTO(1L, "Accesos fallidos del día", "ABIERTO", LocalDateTime.now().minusHours(3)),
            new ReportDTO(2L, "Usuarios creados esta semana", "CERRADO", LocalDateTime.now().minusDays(2))
        );
    }

    @GetMapping("/resumen")
    @PreAuthorize("hasRole('AUDITOR')")
    public Map<String, Object> resumen() {
        System.out.println("Acceso privado a la ruta /api/auditor/resumen");
        return Map.of(
            "totalReportes", 2,
            "abiertos", 1,
            "generado", LocalDateTime.now().toString()
        );
    }
}