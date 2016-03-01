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
public class Category extends BaseEntity{
    @Length(max = 20)
    private String name;
}
