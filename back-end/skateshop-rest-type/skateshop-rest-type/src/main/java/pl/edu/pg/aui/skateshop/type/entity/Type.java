package pl.edu.pg.aui.skateshop.type.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

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
    private double avgSpeed;
    private int maxWeight;
}

