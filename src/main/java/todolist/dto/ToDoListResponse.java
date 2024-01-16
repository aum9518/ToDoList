package todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import todolist.enums.Status;
@Data
@NoArgsConstructor
@Builder
public class ToDoListResponse {
    private Long id;
    private String description;
    private Status status;

    public ToDoListResponse(Long id, String description, Status status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }
}
