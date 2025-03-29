package nanokerb.wermaid.ratings;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class RatingGameResponse {
    @Id
    public String id;
    public String displayName;
    public String username;
    public int rating;
    public String comment;
    public Date date;


    public RatingGameResponse(String id, String displayName, String username, int rating, String comment, Date date) {
        this.id = id;
        this.displayName = displayName;
        this.username = username;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
    }
}
