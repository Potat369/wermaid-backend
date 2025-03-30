package nanokerb.wermaid.games;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "games")
public class Game {
    @Id
    public String id;
    public String slug;
    public String name;
    public String description;
    public String[] genres;
    public String[] links;
    public String releaseDate;
    public String pictureUrl;

    public Game(
            String name,
            String description,
            String[] genres,
            String[] links,
            String releaseDate,
            String pictureUrl
    ) {
        this.slug = name.toLowerCase().replaceAll("[^\\w\\d\\s]", "").replace(' ', '_');
        this.name = name;
        this.description = description;
        this.genres = genres;
        this.links = links;
        this.releaseDate = releaseDate;
        this.pictureUrl = pictureUrl;
    }
}
