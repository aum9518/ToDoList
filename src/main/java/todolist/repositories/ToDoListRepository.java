package todolist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import todolist.dto.ToDoListResponse;
import todolist.models.ToDoList;

import java.util.List;

public interface ToDoListRepository extends JpaRepository<ToDoList, Long> {
    @Query("select new todolist.dto.ToDoListResponse(t.id,t.description,t.status) from ToDoList t")
    List<ToDoListResponse> getAllToDoList();

    @Query("select new todolist.dto.ToDoListResponse(t.id,t.description,t.status) from ToDoList t where t.id=:toDoListId")
    ToDoListResponse getToDoListById(@Param("toDoListId") Long toDoListId);
}