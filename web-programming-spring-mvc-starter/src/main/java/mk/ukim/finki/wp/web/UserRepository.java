package mk.ukim.finki.wp.web;

import com.sun.org.apache.xpath.internal.operations.Bool;
import mk.ukim.finki.wp.model.User;
import mk.ukim.finki.wp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.CookieManager;

/**
 * Created by Darko on 3/5/2016.
 */

@RestController
@RequestMapping(value = "/api/blog/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRepository {
    @Autowired
    IUserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User logIn(@RequestParam String username, @RequestParam String password, @RequestParam Boolean remember, HttpServletRequest httpRequest){
        User u = userService.getUserByUsername(username);

        if (u != null)
            if (u.getPassword().equals(password)){
                if (remember == true){
                    httpRequest.getSession().setAttribute("remember", remember);
                }

                return u;
            }

        return null;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public void signUp(@RequestParam String name, @RequestParam  String surname, @RequestParam  String email, @RequestParam  String username, @RequestParam  String password){
        userService.addUser(name, surname, email, username, password);
    }
}
