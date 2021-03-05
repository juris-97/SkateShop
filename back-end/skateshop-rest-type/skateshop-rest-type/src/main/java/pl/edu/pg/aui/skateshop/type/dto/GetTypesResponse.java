package pl.edu.pg.aui.skateshop.type.dto;

import lombok.*;
import pl.edu.pg.aui.skateshop.type.entity.Type;

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
public class GetTypesResponse {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Type{
        String typeName;
    }

    @Singular
    private List<String> types;

    public static Function<Collection<pl.edu.pg.aui.skateshop.type.entity.Type>,
            GetTypesResponse> entityToDtoMapper() {
        return types -> {
            GetTypesResponseBuilder response = GetTypesResponse.builder();
            types.stream()
                    .map(type -> type.getTypeName())
                    .forEach(response::type);
            return response.build();
        };
    }
}

