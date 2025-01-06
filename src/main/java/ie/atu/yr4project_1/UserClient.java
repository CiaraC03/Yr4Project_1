package ie.atu.yr4project_1;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "task-service", url = "${feign.task-service.url}")
public interface UserClient {
    @GetMapping("/tasks/{taskId}")
    String getTaskById(@PathVariable Long taskId);
}
