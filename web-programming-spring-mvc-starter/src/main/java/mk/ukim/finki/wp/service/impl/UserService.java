package mk.ukim.finki.wp.service.impl;

import mk.ukim.finki.wp.model.User;
import mk.ukim.finki.wp.persistence.IUserRepository;
import mk.ukim.finki.wp.persistence.impl.UserRepository;
import mk.ukim.finki.wp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    public void addUser(String name, String surname, String email, String username, String password, MultipartFile image){
        String uploadPath = "";
        String imageURL = "";
        String uploadPath1 = "";
        if (!image.isEmpty()) {
            try {
                File file = new File(image.getOriginalFilename());
                Path p = Paths.get(file.getAbsoluteFile().getParent() + "/src/main/webapp");
                byte[] bytes = image.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(file));
                stream.write(bytes);
                stream.close();
                p = Paths.get(p.toAbsolutePath() + "/resources/data/" + username);
                File f1 = new File(p.toAbsolutePath().toString());
                uploadPath1 = f1.getAbsolutePath();
                f1.mkdirs();
                p = Paths.get(p.toAbsolutePath() + "/" + image.getOriginalFilename());
                uploadPath = p.toAbsolutePath().toString();
                file.renameTo(new File(uploadPath));
                imageURL = "/resources/users/" + username + "/" + image.getOriginalFilename();
            } catch (Exception e) {
                e.getStackTrace();
            }
        } else {
            //return "You failed to upload " + name + " because the file was empty.";
            uploadPath = ""; //set default user image here
        }

        User user = new User(name, surname, email, username, password, imageURL, uploadPath1);
        userRepository.saveOrUpdate(user);
    }

    public void addUser(String name, String surname, String email, String username, String password){
        User user = new User(name, surname, email, username, password);
        userRepository.saveOrUpdate(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }
}
