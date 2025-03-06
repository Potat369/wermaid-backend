package nanokerb.wermaid.ratings;

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

    public void insert(String gameId, RatingRequest rating) {
        repository.insert(new Rating(gameId, rating.rating, rating.comment));
    }
}
