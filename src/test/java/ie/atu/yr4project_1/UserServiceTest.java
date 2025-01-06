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
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.validation.FieldError;

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
    void testNameForExistingUser() {
        User user = new User("1", 321L, "", "Smith", "John.smith@gmail.com", "0861234567");
        MethodArgumentNotValidException exception = assertThrows(MethodArgumentNotValidException.class, () -> {
            userService.createNewUser(user);
        });

        FieldError fieldError = exception.getBindingResult().getFieldError("name");
        assertNotNull(fieldError);
        assertEquals("Name can't be blank", fieldError.getDefaultMessage());
    }

    @Test
    void testLastNameForExistingUser(){
        User user = new User("2", 222L, "John", "", "John.smith@gmail.com", "0861234567");
            MethodArgumentNotValidException exception = assertThrows(MethodArgumentNotValidException.class, () -> {
                userService.createNewUser(user);
            });

            FieldError fieldError = exception.getBindingResult().getFieldError("lastName");
            assertNotNull(fieldError);
            assertEquals("Last name can't be blank", fieldError.getDefaultMessage());
    }





}
