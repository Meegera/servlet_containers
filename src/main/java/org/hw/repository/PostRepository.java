package org.hw.repository;

import org.hw.model.Post;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostRepository {
    private final Map<Long, Post> posts = new ConcurrentHashMap<>();
    private final AtomicLong postIdCounter = new AtomicLong(1);

    public List<Post> all() {
        return List.copyOf(posts.values());
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(posts.get(id));
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            post.setId(postIdCounter.getAndIncrement());
        } else {
            if (!posts.containsKey(post.getId())) {
                throw new IllegalArgumentException("Поста с id= " + post.getId() + " не существует");
            }
        }
        posts.put(post.getId(), post);
        return post;
    }

    public void removeById(long id) {
        posts.remove(id);
    }
}
