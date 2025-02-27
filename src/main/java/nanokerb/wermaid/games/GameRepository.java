package nanokerb.wermaid.games;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends MongoRepository<Game, ObjectId> {
    List<Game> findByGenre(String genre);

    Optional<Game> findBySlug(String slug);
}
