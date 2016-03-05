package mk.ukim.finki.wp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Darko on 3/1/2016.
 */

@Entity
@JsonIgnoreProperties({"post", "blog"})
public class Category extends BaseEntity{
    @Column(unique = true)
    @Length(max = 20)
    private String name;

    @ManyToOne
    @JoinColumn(name = "blog_id", nullable = false)
    private Blog blog;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "categories")
    private List<Post> post;

    public Category() {}

    public Category(String name, Blog blog){
        this.name = name;
        this.blog = blog;
    }

    public String getName() {
        return name;
    }

    public Blog getBlog() {
        return blog;
    }

    public List<Post> getPost() {
        return post;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }
}
