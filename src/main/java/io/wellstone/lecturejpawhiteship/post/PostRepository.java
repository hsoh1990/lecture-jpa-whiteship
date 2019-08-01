package io.wellstone.lecturejpawhiteship.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>, PostCustomRepository<Post>, QuerydslPredicateExecutor<Post> {
    Page<Post> findByTitleContaining(String title, Pageable pageable);

    long countByTitleContains(String title);
}
