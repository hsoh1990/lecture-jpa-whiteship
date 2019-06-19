package io.wellstone.lecturejpawhiteship.post;

import io.wellstone.lecturejpawhiteship.common.MyRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import java.util.List;

//@RepositoryDefinition(domainClass = Comment.class, idClass = Long.class)
@Repository
public interface CommentRepository extends MyRepository<Comment, Long> {

}
