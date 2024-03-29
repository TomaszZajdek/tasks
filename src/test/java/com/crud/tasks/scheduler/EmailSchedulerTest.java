package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.*;

class EmailSchedulerTest {

    @InjectMocks
    private EmailScheduler emailScheduler;

    @Mock
    private SimpleEmailService simpleEmailService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private AdminConfig adminConfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(adminConfig.getAdminMail()).thenReturn("test@example.com");
    }

    @Test
    void shouldSendInformationEmail() {
        // Given
        long tasksCount = 5L;
        when(taskRepository.count()).thenReturn(tasksCount);

        // When
        emailScheduler.sendInformationEmail();

        // Then
        verify(simpleEmailService).send(any(Mail.class));
    }

    @Test
    void shouldUseCorrectTaskWordInEmail() {
        // Given
        long singleTaskCount = 1L;
        when(taskRepository.count()).thenReturn(singleTaskCount);

        // When
        emailScheduler.sendInformationEmail();

        // Then
        verify(simpleEmailService).send(argThat(mail ->
                mail.getMessage().contains("Currently in database you got: " + singleTaskCount + " task")));
    }
}