package nanokerb.wermaid.games;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "games")

public class Game {



    @Id
    public String id;
    public String name;
    public String description;
    public Date release_date;
    public int rating;
    public int rating_count;


}
