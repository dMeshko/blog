package mk.ukim.finki.wp.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * Created by Darko on 3/1/2016.
 */

@Entity
public class Post extends BaseEntity{
    @Length(max = 30)
    private String title;
    private int rating;
    @Length(max = 800)
    private String content;
    private String imageURL;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    Category category;
}
