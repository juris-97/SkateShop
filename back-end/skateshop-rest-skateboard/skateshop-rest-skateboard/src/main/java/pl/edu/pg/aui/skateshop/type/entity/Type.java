package pl.edu.pg.aui.skateshop.type.entity;

import lombok.*;
import pl.edu.pg.aui.skateshop.skateboard.entity.SkateBoard;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "types")
public class Type implements Serializable {

    @Id
    private String typeName;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "type")
    @ToString.Exclude
    private List<SkateBoard> skateBoards;
}
