package nanokerb.wermaid.games;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "games")
public class Game {
    @Id
    public ObjectId id;
    public String urlId;
    public String name;
    public String description;
    public String genre;
    public Date releaseDate;
    public String pictureUrl;
    public List<ObjectId> ratings;

    public Game(
            String name,
            String description,
            String genre,
            Date releaseDate,
            String pictureUrl
    ) {
        this.urlId = name;
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.pictureUrl = pictureUrl;
        this.ratings = new ArrayList<>();
    }
}
