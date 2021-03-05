package pl.edu.pg.aui.skateshop.skateboard.dto;


import lombok.*;
import pl.edu.pg.aui.skateshop.skateboard.entity.SkateBoard;
import pl.edu.pg.aui.skateshop.type.entity.Type;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateSkateboardRequest {

    private String name;
    private String producer;
    private double price;

    private String type;

    public static Function<CreateSkateboardRequest, SkateBoard> dtoToEntityMapper(
            Function<String, Type> typeFunction) {
        return request -> SkateBoard.builder()
                .name(request.getName())
                .producer(request.getProducer())
                .price(request.getPrice())
                .type(typeFunction.apply(request.getType()))
                .build();
    }
}
