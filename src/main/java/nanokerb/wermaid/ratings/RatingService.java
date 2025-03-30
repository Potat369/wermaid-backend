package nanokerb.wermaid.ratings;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    private final RatingRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public RatingService(RatingRepository repository) {
        this.repository = repository;
    }

    public Optional<Rating> getById(String id) {
        return repository.findById(id);
    }

    public void insert(String gameId, RatingRequest rating, String userId) {
        repository.insert(new Rating(gameId, rating.rating, rating.comment, userId));
    }

    public List<RatingGameResponse> searchRatings(String gameId) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("gameId").is(new ObjectId(gameId))),
                Aggregation.lookup("users", "userId", "_id", "user"),
                Aggregation.project("id", "user.displayName", "user.username", "rating", "comment", "date")
        );
        AggregationResults<RatingGameResponse> results = mongoTemplate.aggregate(aggregation, "ratings", RatingGameResponse.class);
        return results.getMappedResults();
    }

    public void deleteRating(String id) {repository.deleteById(id);}
}
