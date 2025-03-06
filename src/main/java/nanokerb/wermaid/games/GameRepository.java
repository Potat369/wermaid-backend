package nanokerb.wermaid.games;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends MongoRepository<Game, String> {
    Page<Game> findByGenre(String genre, Pageable pageable);

    Optional<Game> findBySlug(String slug);
}
