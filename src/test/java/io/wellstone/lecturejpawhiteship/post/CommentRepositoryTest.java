package io.wellstone.lecturejpawhiteship.post;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;

import java.awt.print.Pageable;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Before
    public void setUp() {
        Comment comment = new Comment();
        comment.setComment("Hello Comment");

        commentRepository.save(comment);
    }

    @Test
    public void crud() {
        Comment comment = new Comment();
        comment.setComment("Hello Comment2");

        commentRepository.save(comment);

        List<Comment> all = commentRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        int count = commentRepository.count();
        assertThat(count).isEqualTo(2);
    }

    @Test
    public void createQuery() {
        Comment comment = new Comment();
        comment.setComment("Hello Comment2");
        comment.setLikeCount(20);
        commentRepository.save(comment);

        List<Comment> comments = commentRepository.findByCommentContains("Hello");
        assertThat(comments.size()).isEqualTo(2);

        comments = commentRepository.findByCommentContainsIgnoreCaseAndLikeCountGreaterThan("Hello", 10);
        assertThat(comments.size()).isEqualTo(1);

        comments = commentRepository.findByCommentContainsOrderByLikeCountDesc("Hello");
        assertThat(comments).first().hasFieldOrPropertyWithValue("likeCount", 20);


        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "likeCount"));

        Page<Comment> page = commentRepository.findByCommentContainsIgnoreCase("Hello", pageRequest);
        assertThat(page.getNumberOfElements()).isEqualTo(2);
        assertThat(page).first().hasFieldOrPropertyWithValue("likeCount", 20);
    }
    
    @Test
    public void asyncQuery() throws ExecutionException, InterruptedException {
        ListenableFuture<List<Comment>> future = commentRepository.findByCommentNotContains("test");
        System.out.println(" =================== ");
        System.out.println("is done? " + future.isDone());

        List<Comment> comments = future.get();
        comments.forEach(System.out::println);
    }

}