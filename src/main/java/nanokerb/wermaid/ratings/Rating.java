package nanokerb.wermaid.ratings;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ratings")
public class Rating {
    @Id
    public ObjectId id;
    public String username;
    public String game_id;
    public int rating;
    public String comment;
}
