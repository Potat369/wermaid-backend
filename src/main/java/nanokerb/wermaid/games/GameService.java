package nanokerb.wermaid.games;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getGamesByGenre(String genre, int page) {
        return gameRepository.findByGenres(genre, PageRequest.of(page, 10)).getContent();
    }

    public Optional<Game> getGame(String id) {
        return gameRepository.findById(id);
    }

    public Optional<Game> getGameBySlug(String slug) {
        return gameRepository.findBySlug(slug);
    }

    public void addGame(Game game) {
        gameRepository.insert(game);
    }

    public void deleteGame(String id) {gameRepository.deleteById(id);}
}
