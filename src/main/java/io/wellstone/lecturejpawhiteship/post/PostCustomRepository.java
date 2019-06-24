package io.wellstone.lecturejpawhiteship.post;

import java.util.List;

public interface PostCustomRepository<T> {
    List<Post> findMyPost();

    void delete(T entity);
}
