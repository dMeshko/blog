package mk.ukim.finki.wp.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.Set;

/**
 * Created by Darko on 3/3/2016.
 */

@Entity
public class Tag extends BaseEntity{
    @Column(unique = true)
    @Length(max = 20)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "tags")
    private List<Post> posts;

    public Tag() {}

    public Tag(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
