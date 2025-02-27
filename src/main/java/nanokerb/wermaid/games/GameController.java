package nanokerb.wermaid.games;

import org.bson.types.ObjectId;
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
    public ResponseEntity<List<Game>> getGames(@RequestParam(required = false) String genre) {
        if (genre != null) {

            return new ResponseEntity<>(gameService.getGamesByGenre(genre), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(gameService.getGames(), HttpStatus.OK);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Optional<Game>> getGame(@PathVariable ObjectId id) {
        return new ResponseEntity<>(gameService.getGame(id), HttpStatus.OK);
    }

    @PostMapping
    public void addGame(@RequestBody Game game) {
        if (gameService.getGameByUrlId(game.urlId).isEmpty()) {
            gameService.addGame(game);
        }
    }
}
