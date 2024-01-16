package todolist.service;

import todolist.dto.SimpleResponse;
import todolist.dto.ToDoListRequest;
import todolist.dto.ToDoListResponse;

import java.util.List;

public interface ToDoListService {
    List<ToDoListResponse> getAllToDoLists();
    ToDoListResponse getToDoListById(Long toDoListId);
    SimpleResponse createNewToDoList(ToDoListRequest toDoListRequest);
    SimpleResponse updateToDoListById( ToDoListRequest toDoListRequest, Long toDoListId);
    SimpleResponse deleteToDoListById(Long toDoListId);
}
