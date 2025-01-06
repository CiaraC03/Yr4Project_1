package ie.atu.yr4project_1;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @BeforeEach
    void setUp(){
    }

    @Test
    void testNameForExistingUser(){
        User user = new User("1", 321L, "", "Smith", "John.smith@gmail.com", "0861234567");
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, ()-> userService.createNewUser(user));
        assertEquals("First name cannot be blank", iae.getMessage());
    }

    @Test
    void testLastNameForExistingUser(){
        User user = new User("2", 222L, "John", "", "John.smith@gmail.com", "0861234567");
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, ()-> userService.createNewUser(user));
        assertEquals("Last name cannot be blank", iae.getMessage());
    }





}
