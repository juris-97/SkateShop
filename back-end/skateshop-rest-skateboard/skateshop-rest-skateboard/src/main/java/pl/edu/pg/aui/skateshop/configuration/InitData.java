package pl.edu.pg.aui.skateshop.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pg.aui.skateshop.skateboard.entity.SkateBoard;
import pl.edu.pg.aui.skateshop.skateboard.service.SkateBoardService;
import pl.edu.pg.aui.skateshop.type.entity.Type;
import pl.edu.pg.aui.skateshop.type.service.TypeService;

import javax.annotation.PostConstruct;

/**
 * Listener started automatically on CDI application context initialized. Injects proxy to the services and fills
 * database with default content. When using persistence storage application instance should be initialized only during
 * first run in order to init database with starting data. Good place to create first default admin user.
 */

@Component
public class InitData {
    private final SkateBoardService skateBoardService;
    private final TypeService typeService;

    @Autowired
    public InitData(SkateBoardService skateBoardService,
                    TypeService typeService) {
        this.skateBoardService = skateBoardService;
        this.typeService = typeService;
    }

    @PostConstruct
    private synchronized void init(){

        Type longboard = Type.builder()
                .typeName("Longboard")
                .build();

        Type skateboard = Type.builder()
                .typeName("Skateboard")
                .build();

        Type penny = Type.builder()
                .typeName("Penny")
                .build();

        Type cruiser = Type.builder()
                .typeName("Cruiser")
                .build();

        typeService.create(longboard);
        typeService.create(skateboard);
        typeService.create(cruiser);
        typeService.create(penny);


        SkateBoard streetDrop = SkateBoard.builder()
                .name("D Street Drop Down Hawaiian")
                .producer("DStreet")
                .price(100.0)
                .type(longboard)
                .build();

        SkateBoard streetPintail = SkateBoard.builder()
                .name("D Street Pintail Malibu")
                .producer("DStreet")
                .price(120.0)
                .type(longboard)
                .build();

        SkateBoard tonyHawk = SkateBoard.builder()
                .name("Tony Hawk SS 180 Complete")
                .producer("Tony Hawk")
                .price(40.0)
                .type(skateboard)
                .build();

        SkateBoard bluePrint = SkateBoard.builder()
                .name("BluePrint Complete")
                .producer("BluePrint")
                .price(75.0)
                .type(skateboard)
                .build();

        SkateBoard madridPicket = SkateBoard.builder()
                .name("Madrid Picked Slant Gold")
                .producer("madrid")
                .price(180.0)
                .type(cruiser)
                .build();

        SkateBoard arborDraplin = SkateBoard.builder()
                .name("Arbor Cruiser Draplin Pilsner")
                .producer("Arbor")
                .price(150.0)
                .type(cruiser)
                .build();

        SkateBoard classicPenny23 = SkateBoard.builder()
                .name("Classic Penny board 23")
                .producer("Penny")
                .price(80.0)
                .type(penny)
                .build();

        SkateBoard classicPenny27 = SkateBoard.builder()
                .name("Classic Penny board 27")
                .producer("Penny")
                .price(150.0)
                .type(penny)
                .build();

        skateBoardService.create(streetDrop);
        skateBoardService.create(streetPintail);
        skateBoardService.create(tonyHawk);
        skateBoardService.create(bluePrint);
        skateBoardService.create(madridPicket);
        skateBoardService.create(classicPenny23);
        skateBoardService.create(classicPenny27);
    }
}

