package ie.atu.yr4project_1;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUserId(String id){
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }

    public User createNewUser(User user){
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    public Void deleteUser(String id){
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return null;
    }
}
