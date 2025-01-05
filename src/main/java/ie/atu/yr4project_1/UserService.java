package ie.atu.yr4project_1;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public UserService(UserRepository userRepository, RabbitTemplate rabbitTemplate){
        this.userRepository = userRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUserId(Long userId){
        Optional<User> user = userRepository.findByUserId(userId);
        return user.get();
    }

    public User createNewUser(User user){
        User savedUser = userRepository.save(user);
        rabbitTemplate.convertAndSend("userQueue", user);
        return savedUser;
    }

    public Void deleteUser(Long userId){
        userRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        userRepository.deleteByUserId(userId);
        return null;
    }
}
