package nanokerb.wermaid.games;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
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
}
