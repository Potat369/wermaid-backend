package nanokerb.wermaid.users;

import jakarta.servlet.http.HttpServletRequest;
import nanokerb.wermaid.security.JwtUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public UserController(JwtUtil jwtUtil, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('USER')")
    public Optional<ResponseUser> getUser(HttpServletRequest request) {
        String token = jwtUtil.parseToken(request);
        String username = jwtUtil.getUsername(token);
        Optional<ResponseUser> userDetails = userRepository.findUserByUsername(username);
        return userDetails;

    }

}
