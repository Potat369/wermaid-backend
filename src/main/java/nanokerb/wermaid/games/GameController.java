package nanokerb.wermaid.games;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nanokerb.wermaid.ratings.RatingRequest;
import nanokerb.wermaid.ratings.RatingService;
import nanokerb.wermaid.security.JwtUtil;
import nanokerb.wermaid.users.User;
import nanokerb.wermaid.users.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/games")
public class GameController {

    private final GameService gameService;
    private final RatingService ratingService;
    private JwtUtil jwtUtil;
    private UserService userService;

    public GameController(GameService gameService, RatingService ratingService, JwtUtil jwtUtil, UserService userService) {
        this.gameService = gameService;
        this.ratingService = ratingService;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<Game>> getGames(@RequestParam String genre, @RequestParam int page) {
        return new ResponseEntity<>(gameService.getGamesByGenre(genre, page), HttpStatus.OK);
    }

    @GetMapping("slug/{slug}")
    public ResponseEntity<Optional<Game>> getGame(@PathVariable String slug) {
        return new ResponseEntity<>(gameService.getGameBySlug(slug), HttpStatus.OK);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<Optional<Game>> getGameById(@PathVariable String id) {
        return new ResponseEntity<>(gameService.getGame(id), HttpStatus.OK);
    }

    @PostMapping
    public void addGame(@RequestBody Game game) {
        if (gameService.getGameBySlug(game.slug).isEmpty()) {
            gameService.addGame(game);
        }
    }

    @PostMapping("id/{id}/rate")
    public void rateGame(@PathVariable String id, @RequestBody RatingRequest rating, HttpServletRequest request) {
        String token = jwtUtil.parseToken(request);
        String username = jwtUtil.getUsername(token);
        Optional<User> user = userService.findByUsername(username);
        if(user.isPresent()) {
            String userId = user.get().id;
            ratingService.insert(id, rating, userId);
        }
    }

    @DeleteMapping("id/{id}")
    public void deleteGame(@PathVariable String id) {
        gameService.deleteGame(id);
    }
}
