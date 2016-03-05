package mk.ukim.finki.wp.model;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Darko on 3/1/2016.
 */

@Entity
public class Post extends BaseEntity{
    @Length(max = 30)
    private String title;

    @Value(value = "0")
    private int rating;

    @Length(max = 800)
    private String content;

    @Column(name = "image_url")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "post_image_urls", joinColumns = @JoinColumn(name = "id"), uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "image_url"})})
    private List<String> imageURLs;

    private Boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "post_categories",
            joinColumns = {@JoinColumn(name = "id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "cat_id", nullable = false)})
    private List<Category> categories;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "post_tags",
            joinColumns = {@JoinColumn(name = "id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "tag_id", nullable = false)})
    private List<Tag> tags;

    @Length(max = 10)
    private String date;

    public Post() {}

    public Post(String title, String content, ArrayList<String> imageURLs, Boolean enabled, User author, List<Category> categories, List<Tag> tags)
    {
        this.title = title;
        this.content = content;
        this.imageURLs = imageURLs;
        this.enabled = enabled;
        this.author = author;
        this.categories = categories;
        this.tags = tags;
        rating = 0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date = simpleDateFormat.format(new Date()).toString();
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

    public List<String> getImageURL() {
        return imageURLs;
    }

    public Boolean getEnabled(){
        return enabled;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public User getAuthor() {
        return author;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public String getDate(){
        return date;
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
        this.imageURLs.add(imageURL);
    }

    public void setEnabled(Boolean enabled){
        this.enabled = enabled;
    }

    public void addCategory(Category category) {
        this.categories.add(category);
    }

    public void setCategories(List<Category> categories){
        this.categories = categories;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void addTag(Tag tag){
        this.tags.add(tag);
    }
}
