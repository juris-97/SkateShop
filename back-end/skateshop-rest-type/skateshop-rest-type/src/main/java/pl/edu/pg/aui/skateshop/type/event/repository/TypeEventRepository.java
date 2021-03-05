package pl.edu.pg.aui.skateshop.type.event.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import pl.edu.pg.aui.skateshop.type.entity.Type;
import pl.edu.pg.aui.skateshop.type.event.dto.CreateTypeRequest;

@Repository
public class TypeEventRepository {

    RestTemplate restTemplate;

    @Autowired
    public TypeEventRepository(@Value("${skateshop.skateboard.url}") String baseUrl) {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(Type type) {
        restTemplate.delete("/types/{typeName}", type.getTypeName());
    }

    public void create(Type type) {
        restTemplate.postForLocation("/types", CreateTypeRequest.entityToDtoMapper().apply(type));
    }
}
