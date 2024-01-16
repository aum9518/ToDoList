package todolist.dto;

import lombok.Getter;
import lombok.Setter;
import todolist.enums.Status;
@Getter
@Setter
public class ToDoListRequest {
    private String description;
    private Status status;
}
