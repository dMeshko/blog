package mk.ukim.finki.wp.service.impl;

import mk.ukim.finki.wp.model.User;
import mk.ukim.finki.wp.persistence.IUserRepository;
import mk.ukim.finki.wp.persistence.impl.UserRepository;
import mk.ukim.finki.wp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Darko on 3/1/2016.
 */

@Service
public class UserService implements IUserService {
    @Autowired
    IUserRepository userRepository;

    @Override
    public User getUser(Long id) {
        return userRepository.getUser(id);
    }

    public void addUser(User user){
        userRepository.saveOrUpdate(user);
    }
}
