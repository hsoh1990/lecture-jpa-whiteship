package io.wellstone.lecturejpawhiteship.post;

import io.wellstone.lecturejpawhiteship.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(PostRepositoryTestConfig.class  )
public class PostRepositoryTest {
    @Autowired
    PostRepository postRepository;

    @Autowired
    ApplicationContext applicationContext;

    @Test
    @Rollback(false)
    public void crudRepository() {
        // Given
        Post post = new Post();
        post.setTitle("hello spring boot common");
        assertThat(post.getId()).isNull();

        // When
        Post newPost = postRepository.save(post);
        // Then
        assertThat(newPost.getId()).isNotNull();

        // When
        List<Post> posts = postRepository.findAll();
        // Then
        assertThat(posts.size()).isEqualTo(1);
        assertThat(posts).contains(newPost);

        // When
        Page<Post> page = postRepository.findAll(PageRequest.of(0, 10));
        // Then
        assertThat(page.getTotalElements()).isEqualTo(1);
        assertThat(page.getNumber()).isEqualTo(0);
        assertThat(page.getSize()).isEqualTo(10);
        assertThat(page.getNumberOfElements()).isEqualTo(1);

        // When
        page = postRepository.findByTitleContaining("spring", PageRequest.of(0, 10));
        // Then
        assertThat(page.getTotalElements()).isEqualTo(1);
        assertThat(page.getNumber()).isEqualTo(0);
        assertThat(page.getSize()).isEqualTo(10);
        assertThat(page.getNumberOfElements()).isEqualTo(1);

        // When
        long string = postRepository.countByTitleContains("spring");
        // Then
        assertThat(string).isEqualTo(1);
    }

    @Test
    public void customRepository() {
        // Given
        Post post = new Post();
        post.setTitle("hello spring boot common");
        Post newPost = postRepository.save(post);

        // When
        List<Post> myPost = postRepository.findMyPost();

        // Then
        assertThat(myPost.size()).isEqualTo(1);
        assertThat(myPost).contains(newPost);

        postRepository.delete(post);
        postRepository.flush();

    }

    @Test
    public void event() {
//        Post post = new Post();
//        post.setTitle("event");
//
//        PostPublishedEvent event = new PostPublishedEvent(post);
//        applicationContext.publishEvent(event);

        Post post = new Post();
        post.setTitle("event");
        Post newPost = postRepository.save(post.publish());

    }


}