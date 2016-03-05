package mk.ukim.finki.wp.service;

import mk.ukim.finki.wp.model.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Darko on 3/1/2016.
 */
public interface IBlogService {
    public Post getPost(Long id);
    public List<Post> getPostsByUser(Long userId);
    public Blog getBlog(Long blogId);
    public List<Category> getCategoriesByBlog(Long blogId);
    public List<Comment> getCommentsByPost(Long postId);
    public void addPost(String title, String content, List<MultipartFile> images, Boolean enabled, User author, List<Category> categories, List<Tag> tags);
    public List<Tag> getTagsByBlog(Long blogId);
    public Category getCategory(Long categoryId);
    public Tag getTagByName(String name);
    public Tag addTag(String name);
    public void addComment(String author, String content, String email, String website, Post post, Comment comment);
    public Comment getCommentById(Long commentId);
    public List<Post> getAllPosts();
    public List<Post> getPostsByCategory(Long categoryId);
    public List<Post> getPostsByTag(Long tagId);
    public List<Post> search(String word);
    public void addPost(String title, String content, String imageURL, User user);
}
