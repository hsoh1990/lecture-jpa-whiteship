package io.wellstone.lecturejpawhiteship.post;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostRepository postRepository;

    @GetMapping(value = "/posts/{id}")
//    public Post getPost(@PathVariable Long id){
//        Optional<Post> byId = postRepository.findById(post.getId());
//        Post getPost = byId.get();
//    }
    public Post getPost(@PathVariable("id") Post post) {
        return post;
    }


    @GetMapping(value = "/posts")
    public PagedResources<Resource<Post>> getPosts(Pageable pageable, PagedResourcesAssembler<Post> assembler){
        return assembler.toResource(postRepository.findAll(pageable));
    }

}
