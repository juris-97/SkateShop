package pl.edu.pg.aui.skateshop.skateboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.pg.aui.skateshop.skateboard.dto.CreateSkateboardRequest;
import pl.edu.pg.aui.skateshop.skateboard.dto.GetSkateboardResponse;
import pl.edu.pg.aui.skateshop.skateboard.dto.GetSkateboardsResponse;
import pl.edu.pg.aui.skateshop.skateboard.dto.UpdateSkateboardRequest;
import pl.edu.pg.aui.skateshop.skateboard.entity.SkateBoard;
import pl.edu.pg.aui.skateshop.skateboard.service.SkateBoardService;
import pl.edu.pg.aui.skateshop.type.entity.Type;
import pl.edu.pg.aui.skateshop.type.service.TypeService;

import java.util.Optional;


@RestController
@RequestMapping("api/types/{typeName}/skateboards")
public class SkateBoardController {

    private SkateBoardService skateBoardService;
    private TypeService typeService;

    @Autowired
    public SkateBoardController(SkateBoardService skateBoardService, TypeService typeService){
        this.skateBoardService = skateBoardService;
        this.typeService = typeService;
    }

    @GetMapping
    public ResponseEntity<GetSkateboardsResponse> getSkateboards(@PathVariable("typeName") String typeName) {
        Optional<Type> type = typeService.find(typeName);
        return type.map(value -> ResponseEntity.ok(GetSkateboardsResponse.entityToDtoMapper().apply(
                skateBoardService.findAll(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{id}")
    public ResponseEntity<GetSkateboardResponse> getSkateboard(@PathVariable("typeName") String typeName,
                                                               @PathVariable("id") long id) {
        Optional<Type> type = typeService.find(typeName);
        if (type.isPresent()) {
            Optional<SkateBoard> skateBoard = skateBoardService.find(type.get(), id);
            return skateBoard.map(value -> ResponseEntity.ok(GetSkateboardResponse.entityToDtoMapper().apply(value)))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> createSkateboard(
            @RequestBody CreateSkateboardRequest request,
            @PathVariable("typeName") String typeName,
            UriComponentsBuilder builder) {

        request.setType(typeName);
        SkateBoard skateBoard = CreateSkateboardRequest
                .dtoToEntityMapper(name -> typeService.find(name).orElseThrow())
                .apply(request);
        skateBoard = skateBoardService.create(skateBoard);
        return ResponseEntity.created(builder.pathSegment("api", "types", typeName, "skateboards", "{id}")
                .buildAndExpand(skateBoard.getId()).toUri()).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSkateboard(@PathVariable("id") long id) {
        Optional<SkateBoard> skateBoard = skateBoardService.find(id);
        if (skateBoard.isPresent()) {
            skateBoardService.delete(skateBoard.get().getId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateSkateboard(@RequestBody UpdateSkateboardRequest request, @PathVariable("id") long id) {
        Optional<SkateBoard> skateBoard = skateBoardService.find(id);
        if (skateBoard.isPresent()) {
            UpdateSkateboardRequest.dtoToEntityUpdater().apply(skateBoard.get(), request);
            skateBoardService.update(skateBoard.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
