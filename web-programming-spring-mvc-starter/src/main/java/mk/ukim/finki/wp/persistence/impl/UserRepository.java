package mk.ukim.finki.wp.persistence.impl;

import mk.ukim.finki.wp.model.User;
import mk.ukim.finki.wp.persistence.BaseRepository;
import mk.ukim.finki.wp.persistence.IUserRepository;
import mk.ukim.finki.wp.persistence.helper.PredicateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Darko on 3/1/2016.
 */

@Repository
public class UserRepository implements IUserRepository {
    @Autowired
    private BaseRepository baseRepository;

    @Override
    public User getUser(Long id) {
        return baseRepository.getById(User.class, id);
    }

    @Override
    public void saveOrUpdate(User user) {
        baseRepository.saveOrUpdate(user);
    }

    @Override
    public void delete(Long id) {
        baseRepository.delete(User.class, id);
    }

    @Override
    public User getUserByUsername(final String username) {
        List<User> u = baseRepository.find(User.class, new PredicateBuilder<User>() {
            @Override
            public Predicate toPredicate(CriteriaBuilder cb, CriteriaQuery<User> cq, Root<User> root) {
                return cb.equal(root.get("username"), username);
            }
        });

        if (u.size() == 0)
            return null;

        return u.get(0);
    }
}
