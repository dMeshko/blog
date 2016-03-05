package mk.ukim.finki.wp.service;

import mk.ukim.finki.wp.model.User;

/**
 * Created by Darko on 3/1/2016.
 */
public interface IUserService {
    public User getUser(Long id);
    public void addUser(User user);
}
