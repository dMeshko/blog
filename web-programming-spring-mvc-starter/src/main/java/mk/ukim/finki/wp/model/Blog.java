package mk.ukim.finki.wp.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Darko on 3/1/2016.
 */

@Entity
public class Blog extends BaseEntity{
    @Length(max = 30)
    private String name;

    public Blog() {}

    public Blog(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
