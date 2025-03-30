package nanokerb.wermaid.ratings;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public List<RatingGameResponse> getRatingByGameId(@PathVariable String gameId) {
        return ratingService.searchRatings(gameId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/id/{id}")
    public void deleteRatingById(@PathVariable String id) {
        ratingService.deleteRating(id);
    }
}
