package ie.atu.yr4project_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Yr4Project1Application {

    public static void main(String[] args) {
        SpringApplication.run(Yr4Project1Application.class, args);
    }

}
