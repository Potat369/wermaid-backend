package nanokerb.wermaid.games;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "games")
public class Game {
    @Id
    public ObjectId id;
    public String urlId;
    public String name;
    public String description;
    public String genre;
    public String releaseDate;
    public List<String> links;
    public String pictureUrl;
    public List<ObjectId> ratings;
}
