package nanokerb.wermaid.ratings;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class RatingUserResponse {
    @Id
    public String id;
    public String name;
    public String pictureUrl;
    public String slug;
    public int rating;
    public String comment;
    public Date date;

    public RatingUserResponse(String id, String name, String pictureUrl, int rating, String comment, Date date, String slug) {
        this.id = id;
        this.name = name;
        this.pictureUrl = pictureUrl;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
        this.slug = slug;
    }
}
