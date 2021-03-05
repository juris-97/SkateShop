package pl.edu.pg.aui.skateshop.skateboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pg.aui.skateshop.skateboard.entity.SkateBoard;
import pl.edu.pg.aui.skateshop.type.entity.Type;

import java.util.List;
import java.util.Optional;

@Repository
public interface SkateBoardRepo extends JpaRepository<SkateBoard, Long> {

    /**
     * Seeks for single skateboard.
     *
     * @param id   skateboard's id
     * @return container (can be empty) with skateboard
     */
    Optional<SkateBoard> findById(Long id);

    Optional<SkateBoard> findByIdAndType(Long id, Type type);

    /**
     * Seeks for all skateboard.
     * @return list (can be empty) of user's skateboards
     */
    List<SkateBoard> findAll();

    List<SkateBoard> findAllByType(Type type);

}
