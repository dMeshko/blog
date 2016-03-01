package mk.ukim.finki.wp.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by Darko on 3/1/2016.
 */

@Entity
public class Comment extends BaseEntity {
    @Length(max = 50)
    private String author;
    @Length(max = 10)
    private String date; //format: dd/MM/yyyy
    @Length(max = 500)
    private String content;
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public Comment(){}

    public Comment(String author, String date, String content){
        this.author = author;
        this.date = date;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
