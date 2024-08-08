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
import org.ssischoolbackend.dto.StaffDto;
import org.ssischoolbackend.model.Department;
import org.ssischoolbackend.model.Gender;
import org.ssischoolbackend.model.Position;
import org.ssischoolbackend.service.StaffService;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class StaffControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StaffService staffService;
    StaffDto staffDto;
    @BeforeEach
    public void setUp() throws Exception {
        staffDto = StaffDto.builder()
                .id(1L)
                .firstName("Alice")
                .lastName("Smith")
                .email("alice.smith@example.com")
                .address("123 Main St")
                .phone("555-1234")
                .cin("12345678")
                .date_of_birth(new Date(473385600000L))
                .date_of_start(new Date(1577836800000L))
                .gender(Gender.FEMALE)
                .position(Position.TEACHER)
                .department(Department.IT)
                .build();

    }
    @Test
    public void should_get_all_staff() throws Exception{
        when(staffService.getAllStaff()).thenReturn(Collections.singletonList(staffDto));
        mockMvc.perform(get("/api/staff/all")).andExpect(status().isOk());
        verify(staffService, times(1)).getAllStaff();
    }

    @Test
    void should_create_new_staff_member() throws Exception {
        when(staffService.createStaffMember(any(StaffDto.class))).thenReturn(1L);
        mockMvc.perform(post("/api/staff")
                .content(asJsonString(staffDto))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
        verify(staffService, times(1)).createStaffMember(any(StaffDto.class));
    }

    @Test
    public void should_get_staff_member() throws Exception {
        when(staffService.getStaffByID(1L)).thenReturn(Optional.ofNullable(staffDto));
        mockMvc.perform(get("/api/staff/1"))
                .andExpect(status().isOk());
        verify(staffService, times(1)).getStaffByID(1L);
    }
    @Test
    public void should_not_get_staff_member() throws Exception {
        when(staffService.getStaffByID(2L)).thenReturn(Optional.ofNullable(null));
        mockMvc.perform(get("/api/staff/2"))
                .andExpect(status().isNotFound());
        verify(staffService, times(1)).getStaffByID(2L);
    }

    @Test
    public void should_delete_staff_member() throws Exception {
        when(staffService.deleteStaffMember(1L)).thenReturn(true);
        mockMvc.perform(delete("/api/staff/1")).andExpect(status().isOk());
        verify(staffService, times(1)).deleteStaffMember(1L);
    }
    @Test
    public void should_not_delete_staff_member() throws Exception {  //because the user is not found
        when(staffService.deleteStaffMember(2L)).thenReturn(false);
        mockMvc.perform(delete("/api/staff/2")).andExpect(status().isNotFound());
        verify(staffService, times(1)).deleteStaffMember(2L);
    }

    @Test
    public void should_update_staff_member() throws Exception {
        when(staffService.updateStaffMember(any(StaffDto.class))).thenReturn(true);
        mockMvc.perform(put("/api/staff/1")
               .content(asJsonString(staffDto))
               .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
        verify(staffService, times(1)).updateStaffMember(any(StaffDto.class));
    }

    @Test
    public void should_not_update_staff_member() throws Exception {
        when(staffService.updateStaffMember(any(StaffDto.class))).thenReturn(false);
        mockMvc.perform(put("/api/staff/2")
                .content(asJsonString(staffDto))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
        verify(staffService, times(1)).updateStaffMember(any(StaffDto.class));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
