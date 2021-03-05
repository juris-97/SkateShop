package pl.edu.pg.aui.skateshop.skateboard.entity;


import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.edu.pg.aui.skateshop.type.entity.Type;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "skateboards")
public class SkateBoard implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String name;
    private String producer;
    private double price;

    @ManyToOne(fetch = FetchType.LAZY) // by default is EAGER
    @JoinColumn(name = "types")
    private Type type;

}
