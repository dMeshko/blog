package mk.ukim.finki.wp.persistence.impl;

import mk.ukim.finki.wp.model.Blog;
import mk.ukim.finki.wp.model.Category;
import mk.ukim.finki.wp.model.Comment;
import mk.ukim.finki.wp.model.Post;
import mk.ukim.finki.wp.persistence.BaseRepository;
import mk.ukim.finki.wp.persistence.IBlogRepository;
import mk.ukim.finki.wp.persistence.helper.PredicateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
        return baseRepository.find(Post.class, new PredicateBuilder<Post>() {
            @Override
            public Predicate toPredicate(CriteriaBuilder cb, CriteriaQuery<Post> cq, Root<Post> root) {
                return cb.equal(root.get("category"), categoryId);
            }
        });
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
    public void saveOrUpdateBlog(Blog blog) {
        baseRepository.saveOrUpdate(blog);
    }

    @Override
    public void saveOrUpdateCategory(Category category) {
        baseRepository.saveOrUpdate(category);
    }
}
