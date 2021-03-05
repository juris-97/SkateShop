package pl.edu.pg.aui.skateshop.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pg.aui.skateshop.type.entity.Type;
import pl.edu.pg.aui.skateshop.type.service.TypeService;


import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
public class InitData {

    private final TypeService typeService;

    @Autowired
    public InitData(TypeService typeService){
        this.typeService = typeService;
    }

    @PostConstruct
    private synchronized void init() {

        Type longboard = Type.builder()
                .typeName("Longboard")
                .avgSpeed(35.0)
                .maxWeight(100)
                .build();

        Type skateboard = Type.builder()
                .typeName("Skateboard")
                .avgSpeed(18.0)
                .maxWeight(80)
                .build();

        Type penny = Type.builder()
                .typeName("Penny")
                .avgSpeed(13.0)
                .maxWeight(110)
                .build();

        Type cruiser = Type.builder()
                .typeName("Cruiser")
                .avgSpeed(25.0)
                .maxWeight(90)
                .build();

        typeService.create(longboard);
        typeService.create(skateboard);
        typeService.create(cruiser);
        typeService.create(penny);
    }
}
