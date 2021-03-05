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
public class GetTypeResponse {

    private String typeName;
    private double avgSpeed;
    private int maxWeight;


    public static Function<Type, GetTypeResponse> entityToDtoMapper(){
        return type -> GetTypeResponse.builder()
                .typeName(type.getTypeName())
                .avgSpeed(type.getAvgSpeed())
                .maxWeight(type.getMaxWeight())
                .build();
    }
}