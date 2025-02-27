package nanokerb.wermaid.ratings;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ratings")
public class Rating {
    @Id
    public ObjectId id;
    public ObjectId userId;
    public ObjectId gameId;
    public int rating;
    public String comment;
}
