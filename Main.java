package org.example;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class User {
    // ...
    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(min = 3, max = 20, message = "El nombre de usuario debe tener entre 3 y 20 caracteres")
    private String username;
    // ...
}

@RestController
@RequestMapping("/api/forums")
public class ForumController {
    @Autowired
    private ForumService forumService;

    @PostMapping
    public ResponseEntity<Forum> createForum(@Valid @RequestBody Forum forum) {
        // Validación automática gracias a las anotaciones en el modelo
        Forum createdForum = forumService.createForum(forum);
        return ResponseEntity.created(URI.create("/api/forums/" + createdForum.getId())).body(createdForum);
    }

    // ... otros métodos
}

// ... otros modelos (Forum, Topic, Comment) con validaciones similares

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // ... configuración de autenticación y autorización
}

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // ... construir un mapa con los errores de validación
    }
}

spring.datasource.url=jdbc:postgresql://localhost:5432/myforum
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update

spring.mvc.pathmatch.matching-strategy=ant_path_matcher