package mk.ukim.finki.wp.service;

import mk.ukim.finki.wp.model.User;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Darko on 3/1/2016.
 */
public interface IUserService {
    public User getUser(Long id);
    public void addUser(String name, String surname, String email, String username, String password, MultipartFile file);
    public User getUserByUsername(String username);
    public void addUser(String name, String surname, String email, String username, String password);
}
