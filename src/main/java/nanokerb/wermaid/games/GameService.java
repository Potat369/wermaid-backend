package nanokerb.wermaid.games;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ConditionalOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private MongoTemplate mongoTemplate;

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<GameResponse> getGamesByGenre(String genre, int page) {
        Aggregation aggregation = Aggregation.newAggregation(

                Aggregation.match(Criteria.where("genres").is(genre)),

                Aggregation.lookup("ratings", "_id", "gameId", "ratings"),

                Aggregation.unwind("ratings", true),

                Aggregation.group("_id")
                        .first("slug").as("slug")
                        .first("name").as("name")
                        .first("description").as("description")
                        .first("genres").as("genres")
                        .first("releaseDate").as("releaseDate")
                        .first("pictureUrl").as("pictureUrl")
                        .avg("ratings.rating").as("averageRating"),

                Aggregation.project("slug", "name", "description", "genres", "releaseDate", "pictureUrl")
                        .and(ConditionalOperators.ifNull("averageRating").then(0)).as("rating")
        );

        AggregationResults<GameResponse> result = mongoTemplate.aggregate(aggregation, "games", GameResponse.class);
        return result.getMappedResults();
    }

    public Optional<Game> getGame(String id) {
        return gameRepository.findById(id);
    }

    public GameResponse getGameBySlug(String slug) {
        Aggregation aggregation = Aggregation.newAggregation(

                Aggregation.match(Criteria.where("slug").is(slug)),

                Aggregation.lookup("ratings", "_id", "gameId", "ratings"),

                Aggregation.unwind("ratings", true),

                Aggregation.group("_id")
                        .first("slug").as("slug")
                        .first("name").as("name")
                        .first("description").as("description")
                        .first("genres").as("genres")
                        .first("links").as("links")
                        .first("releaseDate").as("releaseDate")
                        .first("pictureUrl").as("pictureUrl")
                        .avg("ratings.rating").as("averageRating"),

                Aggregation.project("slug", "name", "description", "genres", "releaseDate", "pictureUrl", "links")
                        .and(ConditionalOperators.ifNull("averageRating").then(0)).as("rating")
        );

        AggregationResults<GameResponse> result = mongoTemplate.aggregate(aggregation, "games", GameResponse.class);
        return result.getUniqueMappedResult();
    }

    public void addGame(Game game) {
        gameRepository.insert(game);
    }

    public void deleteGame(String id) {gameRepository.deleteById(id);}
}
