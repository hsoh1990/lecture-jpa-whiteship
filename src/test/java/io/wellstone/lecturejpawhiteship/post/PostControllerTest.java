package io.wellstone.lecturejpawhiteship.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PostRepository postRepository;


    @Test
    public void getPost() throws Exception {
        // given
        Post post = Post.builder().title("test").build();
        Post save = postRepository.save(post);


        // when
        ResultActions resultActions = mockMvc.perform(get("/posts/" + save.getId()));

        // thne
        resultActions
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("title").value("test"));

    }

    @Test
    public void getPosts() throws Exception {
        // given
        createPost();


        // when
        ResultActions resultActions = mockMvc.perform(get("/posts")
                .param("page", "0")
                .param("size", "10")
                .param("sort", "created,desc")
                .param("title", "title"));

        // thne
        resultActions
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.postList[0].title").value("test1 "));

    }

    private void createPost() {
        int count = 30;
        while (count > 0) {
            postRepository.save(
                    Post.builder().title("test" + count).build()
            );
            count--;
        }


    }
}