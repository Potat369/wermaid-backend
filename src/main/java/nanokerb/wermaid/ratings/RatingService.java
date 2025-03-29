package nanokerb.wermaid.ratings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    private final RatingRepository repository;

    public RatingService(RatingRepository repository) {
        this.repository = repository;
    }

    public Optional<Rating> getById(String id) {
        return repository.findById(id);
    }

    public List<Rating> getByGameId(String gameId) {
        return repository.findAllByGameId(gameId);
    }

    public List<Rating> getByUserId(String userId) {
        return repository.findAllByUserId(userId);
    }

    public double getAverageRating(String gameId) {
        List<Rating> ratings = repository.findAllByGameId(gameId);
        return ratings.stream().mapToInt(r -> r.rating).average().orElse(0.0);
    }

    public void insert(String gameId, RatingRequest rating, String userId) {
        repository.insert(new Rating(gameId, rating.rating, rating.comment, userId));
    }
}
