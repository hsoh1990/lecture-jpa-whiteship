package io.wellstone.lecturejpawhiteship.post;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostRepositoryTestConfig {
    @Bean
    PostListener postListener() {
        return new PostListener();
    }
}
