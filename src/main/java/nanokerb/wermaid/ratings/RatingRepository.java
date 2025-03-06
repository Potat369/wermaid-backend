package nanokerb.wermaid.ratings;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends MongoRepository<Rating, String> {
    List<Rating> findAllByGameId(String gameId);

    List<Rating> findAllByUserId(String userId);
}
