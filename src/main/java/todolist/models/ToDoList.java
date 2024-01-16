package todolist.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import todolist.enums.Status;
@Entity
@Table(name = "toDoLists")
@Getter
@Setter
@NoArgsConstructor
public class ToDoList {
    @Id
    @GeneratedValue(generator = "toDoList_gen",
            strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "toDoList_gen",
            sequenceName = "toDoList_seq",
            allocationSize = 1)
    private Long id;
    private String description;
    private Status status;
}
