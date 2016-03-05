package mk.ukim.finki.wp.persistence;

import mk.ukim.finki.wp.model.User;

/**
 * Created by Darko on 3/1/2016.
 */
public interface IUserRepository {
    public User getUser(Long id);
    public void saveOrUpdate(User user);
    public void delete(Long id);
    public User getUserByUsername(String username);
}
