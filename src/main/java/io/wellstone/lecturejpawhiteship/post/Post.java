package io.wellstone.lecturejpawhiteship.post;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude = "comments")
public class Post {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @OneToMany(mappedBy = "post", cascade = {CascadeType.PERSIST, CascadeType.REMOVE })
    private List<Comment> comments = new ArrayList<>();

    public void addComment(Comment comment) {
        this.getComments().add(comment);
        comment.setPost(this);
    }
}
