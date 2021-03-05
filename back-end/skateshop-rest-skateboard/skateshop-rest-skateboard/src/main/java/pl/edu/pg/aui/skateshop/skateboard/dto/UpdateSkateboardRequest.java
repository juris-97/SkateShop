package pl.edu.pg.aui.skateshop.skateboard.dto;

import lombok.*;
import pl.edu.pg.aui.skateshop.skateboard.entity.SkateBoard;
import pl.edu.pg.aui.skateshop.type.entity.Type;

import java.util.function.BiFunction;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateSkateboardRequest {

    private String name;
    private String producer;
    private double price;

    public static BiFunction<SkateBoard, UpdateSkateboardRequest, SkateBoard> dtoToEntityUpdater() {
        return (skateBoard, request) -> {
            skateBoard.setName(request.getName());
            skateBoard.setProducer(request.getProducer());
            skateBoard.setPrice(request.getPrice());
            return skateBoard;
        };
    }
}
