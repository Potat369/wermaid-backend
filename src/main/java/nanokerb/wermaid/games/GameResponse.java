package nanokerb.wermaid.games;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class GameResponse {
    @Id
    public String id;
    public String slug;
    public String name;
    public String description;
    public String[] genres;
    public String[] links;
    public Date releaseDate;
    public String pictureUrl;
    public float rating;

    public GameResponse(String id, String slug, String name, String description, String[] genres, Date releaseDate, String pictureUrl, float rating) {
        this.id = id;
        this.slug = slug;
        this.name = name;
        this.description = description;
        this.genres = genres;
        this.links = genres;
        this.releaseDate = releaseDate;
        this.pictureUrl = pictureUrl;
        this.rating = rating;
    }
}
