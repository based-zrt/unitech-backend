package tech.unideb.backend.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.unideb.backend.dto.LoginForm;
import tech.unideb.backend.dto.RegisterForm;
import tech.unideb.backend.dto.RegisterResponse;
import tech.unideb.backend.exception.BackendApiException;
import tech.unideb.backend.security.annotations.Anyone;
import tech.unideb.backend.security.annotations.LoggedIn;
import tech.unideb.backend.service.TokenService;
import tech.unideb.backend.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {
    private final TokenService tokenService;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginForm form, HttpServletRequest request) {
        Object data = userService.login(form, request.getRemoteAddr());
        return ResponseEntity.ok(data);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterForm form, HttpServletRequest request) {
        var user = userService.register(form, request.getRemoteAddr());
        return ResponseEntity.ok(new RegisterResponse("ok"));
    }

    @LoggedIn
    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
