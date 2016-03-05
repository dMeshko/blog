package mk.ukim.finki.wp.persistence;

import mk.ukim.finki.wp.model.*;

import java.util.List;

/**
 * Created by Darko on 3/1/2016.
 */
public interface IBlogRepository {
    public Post getPostById(Long id);
    public List<Post> getPostsByUser(Long userId);
    public List<Post> getPostsByCategory(Long categoryId);
    public List<Comment> getCommentsByPost(Long postId);
    public List<Category> getCategoriesByBlog(Long blogId);
    public List<Post> getAllPosts();
    public Blog getBlog(Long blogId);
    public void saveOrUpdateBlog(Blog blog);
    public void saveOrUpdateCategory(Category category);
    public void saveOrUpdatePost(Post post);
    public void deleteBlog(Long blogId); //#never used, #always 1
    public void deleteCategory(Long categoryId);
    public void deletePost(Long id);
    public List<Tag> getTagsByBlog(Long blogId);
    public Tag getTagByName(String name);
    public Category getCategory(Long categoryId);
    public void addTag(Tag tag);
    public void addComment(Comment comment);
    public Comment getCommentById(Long commentId);
}
