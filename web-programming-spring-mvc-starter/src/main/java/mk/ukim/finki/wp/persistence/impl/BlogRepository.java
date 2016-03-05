package mk.ukim.finki.wp.persistence.impl;

import mk.ukim.finki.wp.model.*;
import mk.ukim.finki.wp.persistence.BaseRepository;
import mk.ukim.finki.wp.persistence.IBlogRepository;
import mk.ukim.finki.wp.persistence.helper.PredicateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by Darko on 3/1/2016.
 */

@Repository
public class BlogRepository implements IBlogRepository {
    @Autowired
    private BaseRepository baseRepository;

    @Override
    public Post getPostById(Long id) {
        return baseRepository.getById(Post.class, id);
    }

    @Override
    public List<Post> getPostsByUser(final Long userId) {
        return baseRepository.find(Post.class, new PredicateBuilder<Post>() {
            @Override
            public Predicate toPredicate(CriteriaBuilder cb, CriteriaQuery<Post> cq, Root<Post> root) {
                return cb.equal(root.get("author"), userId);
            }
        });
    }

    @Override
    public List<Post> getPostsByCategory(final Long categoryId) {
        return null;
    }

    @Override
    public void saveOrUpdatePost(Post post) {
        baseRepository.saveOrUpdate(post);
    }

    @Override
    public void deleteBlog(Long blogId) {
        baseRepository.delete(Blog.class, blogId);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        baseRepository.delete(Category.class, categoryId);
    }

    @Override
    public void deletePost(Long id) {
        baseRepository.delete(Post.class, id);
    }

    @Override
    public List<Tag> getTagsByBlog(final Long blogId) {
        return baseRepository.find(Tag.class, new PredicateBuilder<Tag>() {
            @Override
            public Predicate toPredicate(CriteriaBuilder cb, CriteriaQuery<Tag> cq, Root<Tag> root) {
                return cb.equal(root.get("post"), blogId);
            }
        });
    }

    @Override
    public Tag getTagByName(final String name) {
        List<Tag> t =  baseRepository.find(Tag.class, new PredicateBuilder<Tag>() {
            @Override
            public Predicate toPredicate(CriteriaBuilder cb, CriteriaQuery<Tag> cq, Root<Tag> root) {
                return cb.equal(root.get("name"), name);
            }
        });
        if (t.size() != 0)
            return t.get(0);

        return null;
    }

    @Override
    public Category getCategory(Long categoryId) {
        return baseRepository.getById(Category.class, categoryId);
    }

    @Override
    public void addTag(Tag tag) {
        baseRepository.saveOrUpdate(tag);
    }

    @Override
    public void addComment(Comment comment) {
        baseRepository.saveOrUpdate(comment);
    }

    @Override
    public Comment getCommentById(Long commentId) {
        return baseRepository.getById(Comment.class, commentId);
    }

    @Override
    public List<Comment> getCommentsByPost(final Long postId) {
        return baseRepository.find(Comment.class, new PredicateBuilder<Comment>() {
            @Override
            public Predicate toPredicate(CriteriaBuilder cb, CriteriaQuery<Comment> cq, Root<Comment> root) {
                return cb.equal(root.get("post"), postId);
            }
        });
    }

    @Override
    public List<Category> getCategoriesByBlog(final Long blogId) {
        return baseRepository.find(Category.class, new PredicateBuilder<Category>() {
            @Override
            public Predicate toPredicate(CriteriaBuilder cb, CriteriaQuery<Category> cq, Root<Category> root) {
                return cb.equal(root.get("blog"), blogId);
            }
        });
    }

    @Override
    public List<Post> getAllPosts() {
        return baseRepository.find(Post.class, null);
    }

    @Override
    public Blog getBlog(Long blogId) {
        return baseRepository.getById(Blog.class, blogId);
    }

    @Override
    public void saveOrUpdateBlog(Blog blog) {
        baseRepository.saveOrUpdate(blog);
    }

    @Override
    public void saveOrUpdateCategory(Category category) {
        baseRepository.saveOrUpdate(category);
    }
}
