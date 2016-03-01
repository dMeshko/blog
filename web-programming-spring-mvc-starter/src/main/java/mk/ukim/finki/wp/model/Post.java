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
    private Boolean enabled;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    public Post() {}

    public Post(String title, int rating, String content, String imageURL, Boolean enabled, Category category, User author)
    {
        this.title = title;
        this.rating = rating;
        this.content = content;
        this.imageURL = imageURL;
        this.enabled = enabled;
        this.category = category;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public int getRating() {
        return rating;
    }

    public String getContent() {
        return content;
    }

    public String getImageURL() {
        return imageURL;
    }

    public Boolean getEnabled(){
        return enabled;
    }

    public Category getCategory() {
        return category;
    }

    public User getAuthor() {
        return author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setEnabled(Boolean enabled){
        this.enabled = enabled;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
