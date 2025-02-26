package nanokerb.wermaid.games;

import nanokerb.wermaid.ratings.Rating;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "games")
public class Game {
    @Id
    public String id;
    public String name;
    public String description;
    public String genre;
    public Date releaseDate;
    public String[] links;
    public String pictureUrl;
    public Rating[] ratings;
}
