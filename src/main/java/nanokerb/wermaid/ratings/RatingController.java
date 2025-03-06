package nanokerb.wermaid.ratings;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("api/v1/ratings")
public class RatingController {
    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping("id/{id}")
    public Optional<Rating> getRatingById(@PathVariable String id) {
        return ratingService.getById(id);
    }

    @GetMapping("game/{gameId}")
    public List<Rating> getRatingByGameId(@PathVariable String gameId) {
        return ratingService.getByGameId(gameId);
    }
}
