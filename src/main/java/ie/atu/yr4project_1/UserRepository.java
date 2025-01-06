package ie.atu.yr4project_1;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUserId(Long userId); //Using the userId for the get and delete by userId

    @Query(value = "{ 'userId': ?0 }", delete = true)
    void deleteByUserId(Long userId); //Using this custom query so that i can delete the userId
}
