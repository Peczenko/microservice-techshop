package project.org.techshop.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import project.org.techshop.entity.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username, String email);
    Optional<User> findByEmail(String email);
}
