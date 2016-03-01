package mk.ukim.finki.wp.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Darko on 3/1/2016.
 */

@Entity
public class Category extends BaseEntity{
    @Length(max = 20)
    private String name;
    @ManyToOne
    @JoinColumn(name = "blog_id", nullable = false)
    private Blog blog;

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

    public void setName(String name) {
        this.name = name;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }
}
