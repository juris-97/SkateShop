package pl.edu.pg.aui.skateshop.type.event.dto;

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

    /**
     * Type name.
     */
    private String typeName;

    /**
     * @return mapper for convenient converting dto object to entity object
     */
    public static Function<Type, CreateTypeRequest> entityToDtoMapper() {
        return entity -> CreateTypeRequest.builder()
                .typeName(entity.getTypeName())
                .build();
    }
}
