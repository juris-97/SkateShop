package pl.edu.pg.aui.skateshop.type.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.pg.aui.skateshop.type.dto.CreateTypeRequest;
import pl.edu.pg.aui.skateshop.type.entity.Type;
import pl.edu.pg.aui.skateshop.type.service.TypeService;

import java.util.Optional;

@RestController
@RequestMapping("api/types")
public class TypeController {

    private TypeService typeService;

    @Autowired
    public TypeController(TypeService typeService){
        this.typeService = typeService;
    }

    @PostMapping
    public ResponseEntity<Void> createType(@RequestBody CreateTypeRequest request, UriComponentsBuilder builder){
        Type type = CreateTypeRequest
                .dtoToEntityMapper(name -> typeService.find(name).orElseThrow())
                .apply(request);
        type = typeService.create(type);
        return ResponseEntity.created(builder.pathSegment("api", "types", "{typeName}")
                .buildAndExpand(type.getTypeName()).toUri()).build();
    }

    @DeleteMapping("{typeName}")
    public ResponseEntity<Void> deleteType(@PathVariable("typeName") String name) {
        Optional<Type> type = typeService.find(name);
        if (type.isPresent()) {
            typeService.delete(type.get().getTypeName());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
