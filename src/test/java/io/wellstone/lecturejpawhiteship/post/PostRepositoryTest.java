package io.wellstone.lecturejpawhiteship.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

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
    public void save() {
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

    @Test
    public void findByTitleStartingWith() {
        Post post = new Post();
        post.setTitle("test1");
        postRepository.save(post); //persist

        Post post1 = new Post();
        post1.setTitle("test2");
        postRepository.save(post1);

        List<Post> all = postRepository.findByTitleStartingWith("test");
        assertThat(all.size()).isEqualTo(2);
    }

    @Test
    public void findByTitle() {
        Post post = new Post();
        post.setTitle("test");
        postRepository.save(post); //persist

        List<Post> all = postRepository.findByTitle("test", Sort.by("title"));
//        List<Post> all = postRepository.findByTitle("test", JpaSort.unsafe("LENGTH(title"));
        assertThat(all.size()).isEqualTo(1);
    }

    public Post savePost(){
        Post post = new Post();
        post.setTitle("spring");
        return postRepository.save(post);
    }

    @Test
    public void updateTitle(){
        Post spring = savePost();
        int update = postRepository.updateTitle("Hibernate", spring.getId());
        assertThat(update).isEqualTo(1);

//        --> transaction 끝나기 전에 다시 호출해도 1차 캐쉬에서 가지고 있는 Post객체를 리턴하기 때문에 변경 X 사용 주의 필요
//        --> @Modifying(clearAutomatically = true) 를 통해 1차 캐쉬 값을 지우고 다시 조회
        Optional<Post> byId = postRepository.findById(spring.getId());
        assertThat(byId.get().getTitle()).isEqualTo("Hibernate");
    }
}