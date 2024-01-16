package todolist;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import todolist.dto.SimpleResponse;
import todolist.dto.ToDoListRequest;
import todolist.enums.Status;
import todolist.models.ToDoList;
import todolist.repositories.ToDoListRepository;
import todolist.service.impl.ToDoListServiceImpl;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@SpringBootTest
class ToDoListApplicationTests {
    @Mock
    private ToDoListRepository toDoListRepository;
    @InjectMocks
    private ToDoListServiceImpl toDoListService;
    @Test
    void contextLoads() {
    }

    @Test
    public void testCreateNewToDoList() {
        ToDoListRequest toDoListRequest = new ToDoListRequest();
        ToDoList toDoList = new ToDoList();
        toDoList.setDescription(toDoListRequest.getDescription());
        toDoList.setStatus(toDoListRequest.getStatus());
        SimpleResponse result = toDoListService.createNewToDoList(toDoListRequest);

        verify(toDoListRepository, times(1)).save(any(ToDoList.class));
        assertEquals("Успешно добавлено", result.getMessage());
        assertEquals(HttpStatus.OK, result.getStatus());
    }

    @Test
    public void testUpdateToDoListById() {
        Long toDoListId = 1L;
        ToDoListRequest toDoListRequest = new ToDoListRequest();
        toDoListRequest.setDescription("Описание");
        toDoListRequest.setStatus(Status.COMPLETED);

        ToDoList existingToDoList = new ToDoList();
        existingToDoList.setDescription(toDoListRequest.getDescription());
        existingToDoList.setStatus(toDoListRequest.getStatus());

        when(toDoListRepository.findById(toDoListId)).thenReturn(Optional.of(existingToDoList));

        SimpleResponse result = toDoListService.updateToDoListById(toDoListRequest, toDoListId);

        verify(toDoListRepository, times(1)).findById(toDoListId);
        verify(toDoListRepository, times(1)).save(any(ToDoList.class));
        assertEquals("Успешно обновлено", result.getMessage());
        assertEquals(HttpStatus.OK, result.getStatus());
        assertEquals("Описание", existingToDoList.getDescription());
        assertEquals(Status.COMPLETED, existingToDoList.getStatus());
    }
}
