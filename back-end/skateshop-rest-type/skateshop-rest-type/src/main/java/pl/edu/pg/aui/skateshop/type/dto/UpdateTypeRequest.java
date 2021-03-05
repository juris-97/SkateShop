package pl.edu.pg.aui.skateshop.type.dto;

import lombok.*;
import pl.edu.pg.aui.skateshop.type.entity.Type;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateTypeRequest {

    private double avgSpeed;
    private int maxWeight;


    public static BiFunction<Type, UpdateTypeRequest, Type> dtoToEntityUpdater() {
        return (type, request) -> {
            type.setAvgSpeed(request.getAvgSpeed());
            type.setMaxWeight(request.getMaxWeight());
            return type;
        };
    }

}
