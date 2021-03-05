package pl.edu.pg.aui.skateshop.type.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pg.aui.skateshop.type.entity.Type;
import pl.edu.pg.aui.skateshop.type.repository.TypeRepo;

import java.util.Optional;

@Service
public class TypeService {
    /**
     * Repository for type entity.
     */
    private TypeRepo repository;

    /**
     * @param typeRepo repository for type entity
     */
    @Autowired
    public TypeService(TypeRepo typeRepo){
        this.repository = typeRepo;
    }


    /**
     * @param name name of the type
     * @return container with type entity
     */
    public Optional<Type> find(String name) {
        return repository.findByTypeName(name);
    }


    @Transactional
    public Type create(Type type) {
        return repository.save(type);
    }


    @Transactional
    public void delete(String name){
        repository.deleteByTypeName(name);
    }

}
