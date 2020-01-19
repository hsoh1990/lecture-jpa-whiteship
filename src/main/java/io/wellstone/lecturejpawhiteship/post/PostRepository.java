package io.wellstone.lecturejpawhiteship.post;


import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTitleStartingWith(String title);

//    @Query("select p from Post AS p where p.title = ?1")
    @Query("select p from #{#entityName} AS p where p.title = :title")
    List<Post> findByTitle(@Param("title") String title, Sort sort);
}
