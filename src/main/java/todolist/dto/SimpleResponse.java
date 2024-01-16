package todolist.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
@Builder
public class SimpleResponse {
    private HttpStatus status;
    private String message;
}
