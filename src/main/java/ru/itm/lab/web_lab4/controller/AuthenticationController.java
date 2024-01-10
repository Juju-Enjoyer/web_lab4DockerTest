package ru.itm.lab.web_lab4.controller;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import ru.itm.lab.web_lab4.repository.UserRepository;
import ru.itm.lab.web_lab4.security.AuthenticationRequest;
import ru.itm.lab.web_lab4.security.AuthenticationResponse;
import ru.itm.lab.web_lab4.security.RegisterRequest;
import ru.itm.lab.web_lab4.security.errorMessage;
import ru.itm.lab.web_lab4.service.AuthenticationService;
import ru.itm.lab.web_lab4.service.UserService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (userService.isExist(request.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new errorMessage("username \'" + request.getUsername() + "\' is already taken"));
        } else {
            return ResponseEntity.ok(authenticationService.register(request));
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
        try {
            return ResponseEntity.ok(authenticationService.authenticate(request));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new errorMessage("User \'" + request.getUsername() + "\' is not found"));
        }
        catch(BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.resolve(400)).body(new errorMessage("Incorrect password"));
        }

    }
}
