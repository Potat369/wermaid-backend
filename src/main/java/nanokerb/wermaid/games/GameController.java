package nanokerb.wermaid.games;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nanokerb.wermaid.ratings.RatingRequest;
import nanokerb.wermaid.ratings.RatingService;
import nanokerb.wermaid.security.JwtUtil;
import nanokerb.wermaid.users.User;
import nanokerb.wermaid.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Field;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.Format;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/games")
public class GameController {

    private final GameService gameService;
    private final RatingService ratingService;
    private JwtUtil jwtUtil;
    private UserService userService;
    private GameRepository gameRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public GameController(GameService gameService, RatingService ratingService, JwtUtil jwtUtil, UserService userService, GameRepository gameRepository) {
        this.gameService = gameService;
        this.ratingService = ratingService;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.gameRepository = gameRepository;
    }

    @GetMapping
    public ResponseEntity<List<GameResponse>> getGames(@RequestParam String genre, @RequestParam int page) {
        return new ResponseEntity<>(gameService.getGamesByGenre(genre, page), HttpStatus.OK);
    }

    @GetMapping("slug/{slug}")
    public ResponseEntity<GameResponse> getGame(@PathVariable String slug) {
        return new ResponseEntity<>(gameService.getGameBySlug(slug), HttpStatus.OK);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<Optional<Game>> getGameById(@PathVariable String id) {
        return new ResponseEntity<>(gameService.getGame(id), HttpStatus.OK);
    }

    @PostMapping
    public void addGame(@RequestBody Game game) {
        gameService.addGame(game);
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

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/id/{id}")
    public ResponseEntity<Game> editGame(@PathVariable String id, @RequestBody Map<String, Object> updates) {
        Query query = Query.query(Criteria.where("_id").is(id));
        Update update = new Update();

        updates.forEach(update::set);

        mongoTemplate.updateFirst(query, update, Game.class);

        Game updatedGame = mongoTemplate.findById(id, Game.class);

        return updatedGame != null ? ResponseEntity.ok(updatedGame) : ResponseEntity.notFound().build();

    }
}
