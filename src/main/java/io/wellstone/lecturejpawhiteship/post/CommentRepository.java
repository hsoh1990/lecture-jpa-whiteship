package io.wellstone.lecturejpawhiteship.post;

import io.wellstone.lecturejpawhiteship.common.MyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

//@RepositoryDefinition(domainClass = Comment.class, idClass = Long.class)
//@Repository
public interface CommentRepository extends MyRepository<Comment, Long> {

//    @Query(value = "SELECT c FROM Comment AS c", nativeQuery = true)
    List<Comment> findByCommentContains(String keyword);

    List<Comment> findByCommentContainsIgnoreCaseAndLikeCountGreaterThan(String keyword, int likeCount);

    List<Comment> findByCommentContainsOrderByLikeCountDesc(String keyword);

    Page<Comment> findByCommentContainsIgnoreCase(String keyword, Pageable pageable);

}
