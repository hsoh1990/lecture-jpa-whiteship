package io.wellstone.lecturejpawhiteship.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PostCustomRepositoryImpl implements PostCustomRepository<Post>{

    @Autowired
    EntityManager entityManager;

    @Override
    public List<Post> findMyPost() {
        System.out.println("Custom my Post");
        return entityManager.createQuery("SELECT p from Post as p", Post.class).getResultList();
    }

    @Override
    public void delete(Post entity) {
        System.out.println("Custom delete");
        entityManager.remove(entity); ;
    }
}
