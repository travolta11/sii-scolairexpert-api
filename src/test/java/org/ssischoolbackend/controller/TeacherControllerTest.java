package org.ssischoolbackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.ssischoolbackend.dto.TeacherDto;
import org.ssischoolbackend.service.TeacherService;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class TeacherControllerTest {
     @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeacherService teacherService;

    private TeacherDto teacherDto;

    @BeforeEach
    public void setUp() {
        teacherDto = TeacherDto.builder()
                .id(1L)
                .fullName("John Doe")
                .email("johndoe@example.com")
                .phoneNo("123456789")
                .build();
    }

    @Test
    public void should_create_new_teacher() throws Exception {
        when(teacherService.createTeacher(any(TeacherDto.class))).thenReturn(1L);
        mockMvc.perform(post("/api/teacher")
                .content(asJsonString(teacherDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(teacherService, times(1)).createTeacher(any(TeacherDto.class));
    }

    @Test
    public void should_get_all_teachers() throws Exception {
        when(teacherService.getAllTeachers()).thenReturn(Collections.singletonList(teacherDto));
        mockMvc.perform(get("/api/teacher/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].fullName").value("John Doe"));
        verify(teacherService, times(1)).getAllTeachers();
    }

    @Test
    public void should_get_teacher_by_id() throws Exception {
        when(teacherService.getTeacherByID(1L)).thenReturn(Optional.of(teacherDto));
        mockMvc.perform(get("/api/teacher/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("John Doe"));
        verify(teacherService, times(1)).getTeacherByID(1L);
    }

    @Test
    public void should_not_get_teacher_by_id() throws Exception {
        when(teacherService.getTeacherByID(1L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/teacher/1"))
                .andExpect(status().isNotFound());
        verify(teacherService, times(1)).getTeacherByID(1L);
    }

    @Test
    public void should_update_teacher() throws Exception {
        when(teacherService.updateTeacher(any(TeacherDto.class))).thenReturn(1);
        mockMvc.perform(put("/api/teacher/1")
                .content(asJsonString(teacherDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(teacherService, times(1)).updateTeacher(any(TeacherDto.class));
    }

    @Test
    public void should_not_update_teacher() throws Exception {
        when(teacherService.updateTeacher(any(TeacherDto.class))).thenReturn(0);
        mockMvc.perform(put("/api/teacher/1")
                .content(asJsonString(teacherDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        verify(teacherService, times(1)).updateTeacher(any(TeacherDto.class));
    }

    @Test
    public void should_delete_teacher() throws Exception {
        when(teacherService.deleteTeacher(1L)).thenReturn(1);
        mockMvc.perform(delete("/api/teacher/1"))
                .andExpect(status().isOk());
        verify(teacherService, times(1)).deleteTeacher(1L);
    }

    @Test
    public void should_not_delete_teacher() throws Exception {
        when(teacherService.deleteTeacher(1L)).thenReturn(0);
        mockMvc.perform(delete("/api/teacher/1"))
                .andExpect(status().isNotFound());
        verify(teacherService, times(1)).deleteTeacher(1L);
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
