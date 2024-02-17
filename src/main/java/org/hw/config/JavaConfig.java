package org.hw.config;

import org.hw.controller.PostController;
import org.hw.repository.PostRepository;
import org.hw.service.PostService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {
    @Bean
    public PostController postController(PostService service){
        return new PostController(service);
    }

    @Bean
    public PostService postService(PostRepository repository){
        return new PostService(repository);
    }

    @Bean
    public PostRepository postRepository(){
        return new PostRepository();
    }
}
