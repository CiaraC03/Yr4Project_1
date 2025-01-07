package ie.atu.yr4project_1;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User {
    @NotNull(message = "User Id can't be blank")
    private Long userId;
    @NotBlank(message = "Name can't be blank")
    private String name;
    @NotBlank(message = "Last name can't be blank")
    private String lastName;
    @Email(message = "Must be a valid email")
    private String email;
    @Positive(message = "Number must be positive")
    private String num;



}
