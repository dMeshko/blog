package mk.ukim.finki.wp.service.impl;

import mk.ukim.finki.wp.model.*;
import mk.ukim.finki.wp.persistence.IBlogRepository;
import mk.ukim.finki.wp.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Darko on 3/1/2016.
 */

@Service
public class BlogService implements IBlogService {
    @Autowired
    IBlogRepository blogRepository;

    @Override
    public Post getPost(Long id) {
        return blogRepository.getPostById(id);
    }

    @Override
    public List<Post> getPostsByUser(Long userId) {
        return blogRepository.getPostsByUser(userId);
    }

    @Override
    public Blog getBlog(Long blogId) {
        return blogRepository.getBlog(blogId);
    }

    @Override
    public List<Category> getCategoriesByBlog(Long blogId) {
        return blogRepository.getCategoriesByBlog(blogId);
    }

    @Override
    public List<Comment> getCommentsByPost(Long postId) {
        return blogRepository.getCommentsByPost(postId);
    }

    @Override
    public void addPost(String title, String content, List<MultipartFile> images, Boolean enabled, User author, List<Category> categories, List<Tag> tags) {
        ArrayList<String> imageURLs = new ArrayList<String>();
        for (MultipartFile image : images)
        {
            String uploadPath = "";
            if (!image.isEmpty()) {
                try {
                    File file = new File(image.getOriginalFilename());
                    Path p = Paths.get(file.getAbsoluteFile().getParent() + "/src/main/webapp");
                    byte[] bytes = image.getBytes();
                    BufferedOutputStream stream =
                            new BufferedOutputStream(new FileOutputStream(file));
                    stream.write(bytes);
                    stream.close();
                    p = Paths.get(p.toAbsolutePath() + "/resources/data/" + author.getUsername() + "/posts/" + title);
                    File f1 = new File(p.toAbsolutePath().toString());
                    f1.mkdirs();
                    p = Paths.get(p.toAbsolutePath() + "/" + image.getOriginalFilename());
                    uploadPath = p.toAbsolutePath().toString();
                    file.renameTo(new File(uploadPath));
                    uploadPath = "/resources/" + author.getUsername() + "/posts/" + title+ "/" + image.getOriginalFilename();
                } catch (Exception e) {
                    e.getStackTrace();
                }
            } else {
                //return "You failed to upload " + name + " because the file was empty.";
                uploadPath = ""; //set default user image here
            }
            imageURLs.add(uploadPath);
        }
        Post post = new Post(title, content, imageURLs, enabled, author, categories, tags);
        blogRepository.saveOrUpdatePost(post);
    }

    @Override
    public List<Tag> getTagsByBlog(Long blogId) {
        return blogRepository.getTagsByBlog(blogId);
    }

    @Override
    public Category getCategory(Long categoryId) {
        return blogRepository.getCategory(categoryId);
    }

    @Override
    public Tag getTagByName(String name) {
        return blogRepository.getTagByName(name);
    }

    @Override
    public Tag addTag(String name) {
        Tag tag = new Tag(name);
        blogRepository.addTag(tag);
        return tag;
    }

    @Override
    public void addComment(String author, String content, String email, String website, Post post, Comment comment) {
        Comment c = new Comment(author, content, email, website, post, comment);
        blogRepository.addComment(c);
    }

    @Override
    public Comment getCommentById(Long commentId) {
        return blogRepository.getCommentById(commentId);
    }

    @Override
    public List<Post> getAllPosts() {
        return blogRepository.getAllPosts();
    }

    @Override
    public List<Post> getPostsByCategory(Long categoryId) {
        return blogRepository.getPostsByCategory(categoryId);
    }

    @Override
    public List<Post> getPostsByTag(Long tagId) {
        return blogRepository.getPostsByTag(tagId);
    }

    @Override
    public List<Post> search(String word) {
        return blogRepository.search(word);
    }

    @Override
    public void addPost(String title, String content, String imageURL, User user) {
        Post p = new Post(title, content, imageURL, user);
        blogRepository.saveOrUpdatePost(p);
    }
}
