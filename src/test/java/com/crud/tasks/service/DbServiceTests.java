package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DbServiceTests {

    @InjectMocks
    private DbService dbService;
    @Mock
    private TaskRepository repository;

    @Test
    void testFindAll(){
        //Given
        Task task = new Task(1L, "title1", "content1");
        when(repository.findAll()).thenReturn(List.of(task));
        //When
        List<Task> allTasks = dbService.getAllTasks();
        //Then
        assertEquals(List.of(task),allTasks);
    }

    @Test
    void shouldGetTask() throws TaskNotFoundException {
        //Given
        long id = 1L;
        Task task = new Task(id, "title1", "content1");
        when(repository.findById(id)).thenReturn(Optional.of(task));
        //When
        Task actualTask = dbService.getTask(id);
        //Then
        assertEquals(task, actualTask);
    }

    @Test
    void shouldThrowExceptionWhenTaskNotFound(){
        //Given
        when(repository.findById(1L)).thenReturn(Optional.empty());
        //When&Then
        assertThrows(TaskNotFoundException.class, () -> dbService.getTask(1L));
    }

    @Test
    void shouldSaveTask(){
        //Given
        Task task = new Task(1L, "title1", "content1");
        when(repository.save(task)).thenReturn(task);
        //When
        Task savedTask = dbService.saveTask(task);
        //Then
        assertEquals(savedTask, task);
    }
    @Test
    void shouldThrowExceptionWhenDeletingNonExistingTask() {
        // Given
        long taskId = 2L;
        when(repository.existsById(taskId)).thenReturn(false);

        // When & Then
        assertThrows(TaskNotFoundException.class, () -> dbService.deleteTask(taskId));

        verify(repository, never()).deleteById(anyLong());
    }
    @Test
    void shouldDeleteExistingTask() throws TaskNotFoundException {
        // Given
        long taskId = 1L;
        when(repository.existsById(taskId)).thenReturn(true);

        // When
        dbService.deleteTask(taskId);

        // Then
        verify(repository).deleteById(taskId);
    }
}