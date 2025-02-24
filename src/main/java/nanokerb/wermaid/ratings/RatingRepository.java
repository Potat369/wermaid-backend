package nanokerb.wermaid.ratings;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends MongoRepository<Rating, ObjectId> {
    List<Rating> findAllByGameId(String gameId);

    List<Rating> findAllByUsername(String username);
}
