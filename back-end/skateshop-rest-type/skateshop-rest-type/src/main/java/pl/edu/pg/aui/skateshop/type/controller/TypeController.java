package pl.edu.pg.aui.skateshop.type.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.pg.aui.skateshop.type.dto.CreateTypeRequest;
import pl.edu.pg.aui.skateshop.type.dto.GetTypeResponse;
import pl.edu.pg.aui.skateshop.type.dto.GetTypesResponse;
import pl.edu.pg.aui.skateshop.type.dto.UpdateTypeRequest;
import pl.edu.pg.aui.skateshop.type.entity.Type;
import pl.edu.pg.aui.skateshop.type.service.TypeService;

import java.util.Optional;

@RestController
@RequestMapping("api/types")
public class TypeController {

    private TypeService typeService;

    @Autowired
    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }


    @GetMapping
    public ResponseEntity<GetTypesResponse> getTypes(){
        return ResponseEntity.ok(GetTypesResponse.entityToDtoMapper().apply(typeService.findAll()));
    }

    @GetMapping("/{name}")
    public ResponseEntity<GetTypeResponse> getType(@PathVariable("name") String name){
        Optional<Type> type = typeService.find(name);
        return type.map(value -> ResponseEntity.ok(GetTypeResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<Void> createType(@RequestBody CreateTypeRequest request, UriComponentsBuilder builder) {
        Type type = CreateTypeRequest.dtoToEntityMapper().apply(request);
        typeService.create(type);
        return ResponseEntity.created(builder.pathSegment("api", "types", "{typeName}")
                .buildAndExpand(type.getTypeName()).toUri()).build();
    }

    @PutMapping("{typeName}")
    public ResponseEntity<Void> updateType(@RequestBody UpdateTypeRequest request,
                                           @PathVariable("typeName") String name) {
        Optional<Type> type = typeService.find(name);
        if (type.isPresent()) {
            UpdateTypeRequest.dtoToEntityUpdater().apply(type.get(), request);
            typeService.update(type.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{typeName}")
    public ResponseEntity<Void> deleteType(@PathVariable("typeName") String name) {
        Optional<Type> type = typeService.find(name);
        if (type.isPresent()) {
            typeService.delete(type.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
