package mk.ukim.finki.wp.model;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

    private String email;

    @Length(max = 100)
    private String website;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "sub_comment_id")
    private Comment subComment;

    public Comment(){}

    public Comment(String author, String content, String email, String website, Post post, Comment subComment){
        this.author = author;
        this.content = content;
        this.email = email;
        this.website = website;
        this.post = post;
        this.subComment = subComment;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date = simpleDateFormat.format(new Date()).toString();
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

    public String getWebsite() {
        return website;
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

    public void setWebsite(String website) {
        this.website = website;
    }

    public Comment getSubComment(){
        return subComment;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }
}
