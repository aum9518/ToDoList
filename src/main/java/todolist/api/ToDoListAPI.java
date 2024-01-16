package todolist.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import todolist.dto.SimpleResponse;
import todolist.dto.ToDoListRequest;
import todolist.dto.ToDoListResponse;
import todolist.service.ToDoListService;

import java.util.List;

@RestController
@RequestMapping("/api/todolist")
@RequiredArgsConstructor
public class ToDoListAPI {

    private final ToDoListService toDoListService;
    @GetMapping
    @Operation(summary = "Запрос получение", description = "Получение списка всех задач.")
    public List<ToDoListResponse> getAllToDoLists(){
        return toDoListService.getAllToDoLists();
    }

    @GetMapping("/getById/{toDoListId}")
    @Operation(summary = "Запрос получение", description = "Получение информации о конкретной задаче по идентификатору.")
    public ToDoListResponse getToDoListById(@PathVariable Long toDoListId){
        return toDoListService.getToDoListById(toDoListId);
    }

    @PostMapping
    @Operation(summary = "Запрос добавление", description = "Добавление новой задачи.")
    public SimpleResponse createToDoList(@RequestBody ToDoListRequest toDoListRequest){
        return toDoListService.createNewToDoList(toDoListRequest);
    }
    @PutMapping("/{toDoListId}")
    @Operation(summary = "Запрос обновление", description = "Обновление информации о задаче (изменение статуса выполнения или текстового описания).")
    public SimpleResponse updateToDoListById(@RequestBody ToDoListRequest toDoListRequest, @PathVariable Long toDoListId){
        return toDoListService.updateToDoListById(toDoListRequest, toDoListId);
    }

    @DeleteMapping("/{toDoListId}")
    @Operation(summary = "Запрос удаление", description = "Удаление задачи.")
    public SimpleResponse deleteToDoListById(@PathVariable Long toDoListId){
        return toDoListService.deleteToDoListById(toDoListId);
    }
}
