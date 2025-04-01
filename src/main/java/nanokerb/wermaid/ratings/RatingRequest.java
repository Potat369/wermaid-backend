package nanokerb.wermaid.ratings;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class RatingRequest {
    @Min(0)
    @Max(5)
    public int rating;
    @Size(max = 1000)
    public String comment;
}
