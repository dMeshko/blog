package mk.ukim.finki.wp.persistence.impl;

import mk.ukim.finki.wp.model.User;
import mk.ukim.finki.wp.persistence.BaseRepository;
import mk.ukim.finki.wp.persistence.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
