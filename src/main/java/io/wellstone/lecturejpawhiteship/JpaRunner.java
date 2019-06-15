package io.wellstone.lecturejpawhiteship;

import io.wellstone.lecturejpawhiteship.account.Account;
import io.wellstone.lecturejpawhiteship.account.Study;
import io.wellstone.lecturejpawhiteship.post.Comment;
import io.wellstone.lecturejpawhiteship.post.Post;
import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
@Transactional
public class JpaRunner implements ApplicationRunner {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Post post = new Post();
        post.setTitle("whiteship JPA");

        Comment comment1 = new Comment();
        comment1.setComment("nice lecture");
        post.addComment(comment1);

        Comment comment2 = new Comment();
        comment2.setComment("nice lecture~");
        post.addComment(comment2);

        Session session = entityManager.unwrap(Session.class);
        session.save(post);
    }
}
