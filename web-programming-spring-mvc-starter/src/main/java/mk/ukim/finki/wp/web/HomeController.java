package mk.ukim.finki.wp.web;

import mk.ukim.finki.wp.model.Category;
import mk.ukim.finki.wp.model.Tag;
import mk.ukim.finki.wp.model.User;
import mk.ukim.finki.wp.service.IBlogService;
import mk.ukim.finki.wp.service.IUserService;
import mk.ukim.finki.wp.service.impl.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Darko on 3/1/2016.
 */

@Controller
public class HomeController {
    @Autowired
    IBlogService blogService;

    @Autowired
    IUserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("blog");

        long blogId = 1;
        modelAndView.addObject("blogTitle", blogService.getBlog(blogId).getName());
        modelAndView.addObject("blogCategories", blogService.getCategoriesByBlog(blogId));
        //modelAndView.addObject("blogTags", blogService.getTagsByBlog(blogId));

        return modelAndView;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelAndView post(@RequestParam String title, @RequestParam String content, @RequestParam List<MultipartFile> file, @RequestParam Boolean enabled, @RequestParam(required = false) List<String> categories, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView("blog");

        String[] tags = {"kladov", "sektata", "new", "drvo", "babata", "cool"};
        ArrayList<Tag> tgs = new ArrayList<Tag>();
        for (String s : tags){
            Tag t = blogService.getTagByName(s);
            if (t == null)
                t = blogService.addTag(s);
            tgs.add(t);
        }


        ArrayList<Category> cats = new ArrayList<Category>();
        if (categories != null)
            for (String s : categories)
                cats.add(blogService.getCategory(Long.parseLong(s)));

        User user = (User) httpSession.getAttribute("userId");
        user = userService.getUser((long) 1);
        blogService.addPost(title, content, file, enabled, user, cats, tgs);
        modelAndView.addObject("done", "done");

        return modelAndView;
    }
}
