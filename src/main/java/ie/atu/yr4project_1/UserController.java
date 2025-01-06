package ie.atu.yr4project_1;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    private List<User> userList = new ArrayList<>();

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId){
        return ResponseEntity.ok(userService.getUserId(userId));
    }

    @PostMapping("/newUser")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        return ResponseEntity.ok(userService.createNewUser(user));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }




}
