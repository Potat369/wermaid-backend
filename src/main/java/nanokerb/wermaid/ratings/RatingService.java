package nanokerb.wermaid.ratings;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {
    private final RatingRepository repository;

    public RatingService(RatingRepository repository) {
        this.repository = repository;
    }

    public Optional<Rating> getById(ObjectId id) {
        return repository.findById(id);
    }

    public List<Rating> getByGameId(ObjectId gameId) {
        return repository.findAllByGameId(gameId);
    }

    public List<Rating> getByUserId(ObjectId userId) {
        return repository.findAllByUserId(userId);
    }
}
