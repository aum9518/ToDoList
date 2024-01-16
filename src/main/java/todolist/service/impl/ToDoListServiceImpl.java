package todolist.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import todolist.dto.SimpleResponse;
import todolist.dto.ToDoListRequest;
import todolist.dto.ToDoListResponse;
import todolist.exceptions.NotFoundException;
import todolist.models.ToDoList;
import todolist.repositories.ToDoListRepository;
import todolist.service.ToDoListService;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class ToDoListServiceImpl implements ToDoListService {
    private final ToDoListRepository toDoListRepository;
    @Override //Получение списка всех задач.
    public List<ToDoListResponse> getAllToDoLists() {
        return toDoListRepository.getAllToDoList();
    }

    @Override //Получение информации о конкретной задаче по идентификатору.
    public ToDoListResponse getToDoListById(Long toDoListId) {
        toDoListRepository.findById(toDoListId).orElseThrow(() -> new NotFoundException(String.format("Обьект с идентификатором:%s не найдено", toDoListId)));
        return toDoListRepository.getToDoListById(toDoListId);
    }

    @Override //Добавление новой задачи.
    public SimpleResponse createNewToDoList(ToDoListRequest toDoListRequest) {
        ToDoList toDoList = new ToDoList();
        toDoList.setDescription(toDoListRequest.getDescription());
        toDoList.setStatus(toDoListRequest.getStatus());
        toDoListRepository.save(toDoList);
        return SimpleResponse
                .builder()
                .message("Успешно добавлено")
                .status(HttpStatus.OK)
                .build();
    }

    @Override //Обновление информации о задаче (изменение статуса выполнения или текстового описания).
    public SimpleResponse updateToDoListById(ToDoListRequest toDoListRequest, Long toDoListId) {
        ToDoList toDoList = toDoListRepository.findById(toDoListId).orElseThrow(() -> new NotFoundException(String.format("Обьект с идентификатором:%s не найдено", toDoListId)));
        toDoList.setDescription(toDoListRequest.getDescription());
        toDoList.setStatus(toDoListRequest.getStatus());
        toDoListRepository.save(toDoList);
        return SimpleResponse
                .builder()
                .message("Успешно обновлено")
                .status(HttpStatus.OK)
                .build();
    }

    @Override //Удаление задачи.
    public SimpleResponse deleteToDoListById(Long toDoListId) {
        ToDoList toDoList = toDoListRepository.findById(toDoListId).orElseThrow(() -> new NotFoundException(String.format("Обьект с идентификатором:%s не найдено", toDoListId)));
        toDoListRepository.deleteById(toDoList.getId());
        return SimpleResponse
                .builder()
                .message("Успешно удалено")
                .status(HttpStatus.OK)
                .build();
    }
}
