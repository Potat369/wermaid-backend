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

    private JwtUtil jwtUtil;
    private UserRepository userRepository;
    public UserController(JwtUtil jwtUtil, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }
    @GetMapping()
    @PreAuthorize("hasAuthority('USER')")
    public Optional<ResponseUser> getUser(HttpServletRequest request) {
        Optional<String> token = jwtUtil.parseToken(request);
        if (token.isPresent()) {
            String username = jwtUtil.getUsername(token.get());
            Optional<ResponseUser> userDetails = userRepository.findUserByUsername(username);
            return userDetails;

        }
        return Optional.empty();
    }

}
