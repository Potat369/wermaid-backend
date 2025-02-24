package nanokerb.wermaid.games;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getGames() {
        return gameRepository.findAll();
    }

    public List<Game> getGamesByGenre(String genre) {
        return gameRepository.findByGenre(genre);
    }

    public Optional<Game> getGame(String id) {
        return gameRepository.findById(id);
    }
}
