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
        User user = new User(1L, 321L, "", "Smith", "John.smith@gmail.com", "0861234567");
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, ()-> userService.createNewUser(user));
        assertEquals("First name cannot be blank", iae.getMessage());
    }

    @Test
    void testLastNameForExistingUser(){
        User user = new User(1L, 222L, "John", "", "John.smith@gmail.com", "0861234567");
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, ()-> userService.createNewUser(user));
        assertEquals("Last name cannot be blank", iae.getMessage());
    }

    @Test
    void testCreateNewUser(){
        User user = new User(1L, 444L, "John", "", "John.smith@gmail.com", "0861234567");
        when(userRepository.save(user)).thenReturn(user);
        User savedUser = userService.createNewUser(user);
        assertEquals(user, savedUser);
        verify(userRepository, times(1)).save(user);
        verify(rabbitTemplate, times(1)).convertAndSend("userQueue", user);
    }

    @Test
    void testDeleteUserForExistingUser() {
        User user = new User(3L, 666L, "Ciara", "Crowe", "Ciara.Crowe@gmail.com", "0861534767");
        when(userRepository.findByUserId(3L)).thenReturn(Optional.of(user));

        userService.deleteUser(3L);

        verify(userRepository, times(1)).findByUserId(3L);
        verify(userRepository, times(1)).deleteByUserId(3L);
    }

    @Test
    void testDeleteUserForNonExistingUser() {
        when(userRepository.findByUserId(5L)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> userService.deleteUser(1L)
        );

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("User not found", exception.getReason());
        verify(userRepository, times(1)).findByUserId(5L);
        verify(userRepository, never()).deleteByUserId(anyLong());
    }



}
