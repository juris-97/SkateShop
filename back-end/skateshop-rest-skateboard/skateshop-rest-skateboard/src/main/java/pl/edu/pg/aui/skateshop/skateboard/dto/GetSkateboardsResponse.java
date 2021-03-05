package pl.edu.pg.aui.skateshop.skateboard.dto;

import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetSkateboardsResponse {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Skateboard{
        private Long id;
        private String name;
        private String type;
    }

    @Singular
    private List<Skateboard> skateboards;

    public static Function<Collection<pl.edu.pg.aui.skateshop.skateboard.entity.SkateBoard>,
            GetSkateboardsResponse> entityToDtoMapper() {
        return skateboards -> {
            GetSkateboardsResponseBuilder response = GetSkateboardsResponse.builder();
            skateboards.stream()
                    .map(skateboard -> Skateboard.builder()
                            .id(skateboard.getId())
                            .name(skateboard.getName())
                            .type(skateboard.getType().getTypeName())
                            .build())
                    .forEach(response::skateboard);
            return response.build();
        };
    }
}
