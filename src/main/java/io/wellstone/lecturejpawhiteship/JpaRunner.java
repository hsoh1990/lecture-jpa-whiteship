package io.wellstone.lecturejpawhiteship;

import io.wellstone.lecturejpawhiteship.post.Comment;
import io.wellstone.lecturejpawhiteship.post.Post;
import io.wellstone.lecturejpawhiteship.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class JpaRunner implements ApplicationRunner {

    @Autowired
    PostRepository postRepository;

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

        postRepository.save(post);
        postRepository.findAll().forEach(System.out::println);
    }
}
