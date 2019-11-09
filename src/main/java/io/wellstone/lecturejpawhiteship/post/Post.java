package io.wellstone.lecturejpawhiteship.post;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@NamedQuery(name = "Post.findByTitle", query = "select p from Post AS p where p.title = ?1")
public class Post {
    @Id @GeneratedValue
    private Long id;

    private String title;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Date created;

}
