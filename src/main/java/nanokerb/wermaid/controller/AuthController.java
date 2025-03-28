package nanokerb.wermaid.controller;

import nanokerb.wermaid.requests.AuthRequest;
import nanokerb.wermaid.security.JwtUtil;
import nanokerb.wermaid.requests.RegisterRequest;
import nanokerb.wermaid.users.Role;
import nanokerb.wermaid.users.User;
import nanokerb.wermaid.users.UserRepository;
import nanokerb.wermaid.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        String hashedPassword = passwordEncoder.encode(registerRequest.password());
        User user = new User();
        user.username = registerRequest.username();
        user.password = hashedPassword;
        user.displayName = registerRequest.displayName();
        user.role = Collections.singletonList(Role.USER);
        return ResponseEntity.ok(userRepository.save(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        Optional<User> userOptional = userRepository.findByUsername(authRequest.username());
        if (userOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
        User user = userOptional.get();
        if (!authenticate(authRequest.password(), user.password)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

        String token = jwtUtil.generateToken(authRequest.username());
        return ResponseEntity.ok(token);
    }

    public boolean authenticate(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }
}
