package pl.edu.pg.aui.skateshop.skateboard.dto;

import lombok.*;
import pl.edu.pg.aui.skateshop.skateboard.entity.SkateBoard;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetSkateboardResponse {

    private Long id;
    private String name;
    private String producer;
    private double price;
    private String type;

    public static Function<SkateBoard, GetSkateboardResponse> entityToDtoMapper() {
        return skateBoard -> GetSkateboardResponse.builder()
                .id(skateBoard.getId())
                .name(skateBoard.getName())
                .producer(skateBoard.getProducer())
                .price(skateBoard.getPrice())
                .type(skateBoard.getType().getTypeName())
                .build();
    }

}
