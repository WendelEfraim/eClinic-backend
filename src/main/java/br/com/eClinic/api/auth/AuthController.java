package br.com.eClinic.api.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        if (request.getUsername().equals("medico")) {
            return ResponseEntity.ok(new AuthResponse("Login bem-sucedido como médico!"));
        } else if (request.getUsername().equals("paciente")) {
            return ResponseEntity.ok(new AuthResponse("Login bem-sucedido como paciente!"));
        }
        System.out.println(request);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponse("Credenciais inválidas"));
    }
}