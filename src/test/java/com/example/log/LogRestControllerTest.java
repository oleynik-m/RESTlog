package com.example.log;

import com.example.log.controller.LogController;
import com.example.log.model.Level;
import com.example.log.model.Log;
import com.example.log.model.Type;
import com.example.log.service.LogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.data.domain.Example;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = LogController.class)
public class LogRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LogService logService;

    private List<Log> logs;

    @BeforeEach
    void setUp () {
        this.logs = Arrays.asList(
                new Log("Error message", Type.JSON, Level.SEVERE),
                new Log("Warning message", Type.XML, Level.WARNING),
                new Log("Text message", Type.TEXT,Level.INFO)
        );
    }

    @Test
    void createLogTest () throws Exception {
        Log mockedLog = new Log("Error message", Type.JSON, Level.SEVERE);
        mockMvc.perform(post("/log")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(mockedLog)))
                .andExpect(status().isCreated());
    }

    @Test
    void updateLogTest () throws Exception {
        Log mockedLog = new Log("Error message", Type.JSON, Level.SEVERE);
        when(logService.getById(1L))
                .thenReturn(Optional.of(mockedLog));
        mockMvc.perform(put("/log/{id}",1L)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(mockedLog)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteLogTest () throws Exception {
        Log mockedLog = new Log("Error message", Type.JSON, Level.SEVERE);
        when(logService.getById(1L))
                .thenReturn(Optional.of(mockedLog));
        mockMvc.perform(delete("/log/{id}",1L)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(mockedLog)))
                .andExpect(status().isOk());
    }

    /*@Test
    public void getAllLogsTest ()
            throws Exception {
        Log mockedLog = new Log();
        when(logService.getAll(Example.of(mockedLog)))
                .thenReturn(this.logs);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/log"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2));
    }*/

    @Test
    public void getLogById ()
            throws Exception {
        Log mockedLog = new Log("Error message", Type.JSON, Level.SEVERE);
        when(logService.getById(1L))
                .thenReturn(Optional.of(mockedLog));

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/log/id/{id}",1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Error message"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.type").value("JSON"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.level").value("SEVERE"));
    }
}
