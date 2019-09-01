package io.wellstone.lecturejpawhiteship.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {
    @Autowired
    PostRepository postRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Test
    public void crud(){
        Post post = new Post();
        post.setTitle("test");
        postRepository.save(post);

        List<Post> all = postRepository.findAll();
        assertThat(all.size()).isEqualTo(1);

    }


    @Test
    public void save(){
        Post post = new Post();
        post.setTitle("test");
        Post savedPost = postRepository.save(post); //persist

        assertThat(entityManager.contains(post)).isTrue();
        assertThat(entityManager.contains(savedPost)).isTrue();
        assertThat(post == savedPost);

        Post postUpdate = new Post();
        postUpdate.setId(savedPost.getId());
        postUpdate.setTitle("hibernate");
        Post updatePost = postRepository.save(postUpdate);// merge

        assertThat(entityManager.contains(postUpdate)).isFalse();
        assertThat(entityManager.contains(updatePost)).isTrue();
        assertThat(postUpdate != updatePost);


    }

}