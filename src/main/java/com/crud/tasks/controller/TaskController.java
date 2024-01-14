package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final DbService service;
    private final TaskMapper taskMapper;
    @GetMapping
    public List<TaskDto> getTasks() {
        List<Task> tasks = service.getAllTasks();
        return taskMapper.mapToTaskDtoList(tasks);
    }
    @GetMapping(value = "{taskId}")
    public TaskDto getTask(@PathVariable Long taskId) {

        return new TaskDto(
                service.getTask(taskId).get().getId(),
                service.getTask(taskId).get().getTitle(),
                service.getTask(taskId).get().getContent());
    }
    @DeleteMapping(value = "{taskId}")
    public void deleteTask(@PathVariable Long taskId) {

    }
    @PutMapping
    public TaskDto updateTask( @RequestBody TaskDto task) {
        return new TaskDto(task.getId(), task.getTitle(), task.getContent());
    }
    @PostMapping
    public void createTask(@RequestBody TaskDto taskDto) {
        System.out.println(taskDto);
    }
}