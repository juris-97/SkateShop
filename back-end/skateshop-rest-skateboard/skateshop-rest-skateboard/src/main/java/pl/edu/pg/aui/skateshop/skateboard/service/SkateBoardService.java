package pl.edu.pg.aui.skateshop.skateboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pg.aui.skateshop.skateboard.entity.SkateBoard;
import pl.edu.pg.aui.skateshop.type.entity.Type;
import pl.edu.pg.aui.skateshop.skateboard.repository.SkateBoardRepo;

import java.util.List;
import java.util.Optional;

@Service
public class SkateBoardService {

    /**
     * Repository for skateboards entity.
     */

    private SkateBoardRepo skateBoardRepo;

    /**
     * @param skateBoardRepo repository for skateboards entity
     */
    @Autowired
    public SkateBoardService(SkateBoardRepo skateBoardRepo){
        this.skateBoardRepo = skateBoardRepo;
    }

    /**
     * Finds single skateboards.
     *
     * @param id character's id
     * @return container with character
     */
    public Optional<SkateBoard> find(Long id){
        return skateBoardRepo.findById(id);
    }

    public Optional<SkateBoard> find(Type type, long id) {
        return skateBoardRepo.findByIdAndType(id, type);
    }

    /**
     * @return all available skateboards
     */
    public List<SkateBoard> findAll(){
        return skateBoardRepo.findAll();
    }

    public List<SkateBoard> findAll(Type type) {
        return skateBoardRepo.findAllByType(type);
    }

    /**
     * Creates new skateboards.
     *
     * @param skateboard new skateboards
     */
    @Transactional
    public SkateBoard create(SkateBoard skateboard) {
        return skateBoardRepo.save(skateboard);
    }

    @Transactional
    public void update(SkateBoard skateBoard) {
        skateBoardRepo.save(skateBoard);
    }

    @Transactional
    public void delete(Long skateBoard) {
        skateBoardRepo.deleteById(skateBoard);
    }

}
