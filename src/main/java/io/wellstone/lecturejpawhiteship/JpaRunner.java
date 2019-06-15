package io.wellstone.lecturejpawhiteship;

import io.wellstone.lecturejpawhiteship.post.Post;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class JpaRunner implements ApplicationRunner {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        jpql..
        TypedQuery<Post> query = entityManager.createQuery("SELECT p FROM Post As p", Post.class);

        List<Post> jpqlPosts = query.getResultList();
        System.out.println("jpql ....");
        jpqlPosts.forEach(System.out::println);

//        criteria..
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Post> criteria = builder.createQuery(Post.class);
        Root<Post> root = criteria.from(Post.class);
        criteria.select(root);
        List<Post> criteriaPosts = entityManager.createQuery(criteria).getResultList();
        System.out.println("criteria ....");
        criteriaPosts.forEach(System.out::println);

//        NativeQuery..
        List<Post> nativeQueryPosts = entityManager.createNativeQuery("SELECT * FROM POST", Post.class).getResultList();

        System.out.println("NativeQuery ....");
        nativeQueryPosts.forEach(System.out::println);


    }
}
