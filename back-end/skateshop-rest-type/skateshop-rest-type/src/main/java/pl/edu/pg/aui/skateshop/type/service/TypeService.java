package pl.edu.pg.aui.skateshop.type.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pg.aui.skateshop.type.entity.Type;
import pl.edu.pg.aui.skateshop.type.event.repository.TypeEventRepository;
import pl.edu.pg.aui.skateshop.type.repository.TypeRepo;

import java.util.List;
import java.util.Optional;

@Service
public class TypeService {
    /**
     * Repository for type entity.
     */
    private TypeRepo repository;
    private TypeEventRepository eventRepository;

    /**
     * @param typeRepo repository for type entity
     */
    @Autowired
    public TypeService(TypeRepo typeRepo, TypeEventRepository eventRepository){
        this.repository = typeRepo;
        this.eventRepository = eventRepository;
    }


    /**
     * @param name name of the type
     * @return container with type entity
     */
    public Optional<Type> find(String name) {
        return repository.findByTypeName(name);
    }

    /**
     *
     * @return list of all types
     */
    public List<Type> findAll(){
        return repository.findAll();
    }


    @Transactional
    public void create(Type type) {
        repository.save(type);
        eventRepository.create(type);
    }

    @Transactional
    public void update(Type type){
        repository.save(type);
    }

    @Transactional
    public void delete(Type type) {
        eventRepository.delete(type);
        repository.delete(type);
    }

}