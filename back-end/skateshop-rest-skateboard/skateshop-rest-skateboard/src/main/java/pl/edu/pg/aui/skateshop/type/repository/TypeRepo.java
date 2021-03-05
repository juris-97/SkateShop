package pl.edu.pg.aui.skateshop.type.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pg.aui.skateshop.type.entity.Type;

import java.util.Optional;

@Repository
public interface TypeRepo extends JpaRepository<Type, String> {
    Optional<Type> findByTypeName(String name);
    Optional<Type> deleteByTypeName(String name);
}
