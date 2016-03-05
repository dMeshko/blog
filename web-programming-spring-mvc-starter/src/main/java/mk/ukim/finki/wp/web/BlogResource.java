package mk.ukim.finki.wp.web;

import mk.ukim.finki.wp.model.*;
import mk.ukim.finki.wp.service.IBlogService;
import mk.ukim.finki.wp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Darko on 3/2/2016.
 */

@RestController
@RequestMapping(value = "/api/blog", produces = MediaType.APPLICATION_JSON_VALUE)
public class BlogResource {
    @Autowired
    IBlogService blogService;

    @Autowired
    IUserService userService;

    @RequestMapping(value = "/{blogId}", method = RequestMethod.GET)
    public Blog getBlog(@PathVariable Long blogId){
        return blogService.getBlog(blogId);
    }

    @RequestMapping(value = "/category/{categoryId}", method = RequestMethod.GET)
    public Category getCategory(@PathVariable Long categoryId){
        return blogService.getCategory(categoryId);
    }

    @RequestMapping(value = "/tag/byName/{tagName}", method = RequestMethod.GET)
    public Tag getTagByName(@PathVariable String tagName){
        return blogService.getTagByName(tagName);
    }

    @RequestMapping(value = "/post/byTag/{tagId}")
    public List<Post> getPostsByTag(@PathVariable Long tagId){
        return blogService.getPostsByTag(tagId);
    }

    @RequestMapping(value = "/category/byBlog/{blogId}", method = RequestMethod.GET)
    public List<Category> getCategoriesByBlog(@PathVariable Long blogId){
        return blogService.getCategoriesByBlog(blogId);
    }

    @RequestMapping(value = "/post", method = RequestMethod.GET)
    public List<Post> getAllPosts(){
        List<Post> posts = blogService.getAllPosts();
        Collections.reverse(posts);
        return posts;
    }

    @RequestMapping(value = "/post/recent", method = RequestMethod.GET)
    public List<Post> getRecentPosts(){
        List<Post> all = getAllPosts();
        int gb = 5;
        if (all.size() < 5)
            gb = all.size();
        return all.subList(0, gb);
    }

    @RequestMapping(value = "/post/{postId}", method = RequestMethod.GET)
    public Post getPost(@PathVariable Long postId){
        return blogService.getPost(postId);
    }

    @RequestMapping(value = "/post/byCategory/{categoryId}", method = RequestMethod.GET)
    public List<Post> getPostsByCategory(@PathVariable Long categoryId){
        List<Post> posts = blogService.getPostsByCategory(categoryId);
        Collections.reverse(posts);
        return posts;
    }

    @RequestMapping(value = "/comment/{commentId}", method = RequestMethod.GET)
    public Comment getCommentById(@PathVariable Long commentId){
        return blogService.getCommentById(commentId);
    }

    @RequestMapping(value = "/comment/byPost/{postId}", method = RequestMethod.GET)
    public List<Comment> getCommentsByPost(@PathVariable Long postId){
        return blogService.getCommentsByPost(postId);
    }

    @RequestMapping(value = "/comment/add/{postId}/{parentCommentId}", method = RequestMethod.POST)
    public void postComment(@PathVariable Long postId, @PathVariable Long parentCommentId, @RequestParam String author, @RequestParam String content, @RequestParam String email, @RequestParam(required = false) String website){
        Post post = blogService.getPost(postId);
        Comment comment = blogService.getCommentById(parentCommentId);
        blogService.addComment(author, content, email, website, post, comment);
    }

    @RequestMapping(value = "/post/search", method = RequestMethod.GET)
    public List<Post> search(@RequestParam String word){
        return blogService.search(word);
    }

    @RequestMapping(value = "/post/add", method = RequestMethod.POST)
    public void createPost(@RequestParam String title, @RequestParam String content, @RequestParam String imageURL, @RequestParam Long userId){
        User user = userService.getUser(userId);
        blogService.addPost(title, content, imageURL, user);
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public void postPost(@RequestParam String title, @RequestParam String content, @RequestParam ArrayList<MultipartFile> images, @RequestParam Boolean enabled, @RequestParam(required = false) List<String> categories, @RequestParam(required = false) List<String> tags, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("userId");

        ArrayList<Tag> tgs = new ArrayList<Tag>();
        if (tags != null)
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

        blogService.addPost(title, content, images, enabled, user, cats, new ArrayList());
    }
}
