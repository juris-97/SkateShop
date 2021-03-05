package pl.edu.pg.aui.skateshop.type.dto;

import lombok.*;
import pl.edu.pg.aui.skateshop.type.entity.Type;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateTypeRequest {

    private String typeName;
    private double avgSpeed;
    private int maxWeight;



    public static Function<CreateTypeRequest, Type> dtoToEntityMapper() {
        return request -> Type.builder()
                .typeName(request.getTypeName())
                .avgSpeed(request.getAvgSpeed())
                .maxWeight(request.getMaxWeight())
                .build();
    }
}
