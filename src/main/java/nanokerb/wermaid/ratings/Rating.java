package nanokerb.wermaid.ratings;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.Date;

@Document(collection = "ratings")
public class Rating {
    @Id
    public String id;
    @Field(targetType = FieldType.OBJECT_ID)
    public String userId;
    @Field(targetType = FieldType.OBJECT_ID)
    public String gameId;
    @Min(1)
    @Max(5)
    public int rating;
    public String comment;
    public Date date;

    public Rating(String gameId, int rating, String comment, String userId) {
        this.gameId = gameId;
        this.rating = rating;
        this.comment = comment;
        this.userId = userId;
        this.date = new Date();
    }
}
