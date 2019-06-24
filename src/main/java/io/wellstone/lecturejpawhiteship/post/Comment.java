package io.wellstone.lecturejpawhiteship.post;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@ToString
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    private String comment;

    private Integer likeCount;

    @ManyToOne
    private Post post;


}
