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
import org.ssischoolbackend.dto.EspaceDto;
import org.ssischoolbackend.service.EspaceService;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class EspaceControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EspaceService espaceService;

    EspaceDto espaceDto;

    @BeforeEach
    public void setUp() throws Exception {
        espaceDto = EspaceDto.builder().id(1L).name("espace 1").build();
    }

    @Test
    void should_create_new_espace() throws Exception {
        when(espaceService.createEspace(any(EspaceDto.class))).thenReturn(1L);
        mockMvc.perform(post("/api/espace")
                .content(asJsonString(espaceDto))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
        verify(espaceService, times(1)).createEspace(any(EspaceDto.class));
    }

    @Test
    public void should_get_all_espaces() throws Exception {
        when(espaceService.getAllEsapces()).thenReturn(Collections.singletonList(espaceDto));
        mockMvc.perform(get("/api/espace/all"))
                .andExpect(status().isOk());
        verify(espaceService, times(1)).getAllEsapces();
    }

    @Test
    public void should_get_espace() throws Exception {
        when(espaceService.getEspaceByID(1L)).thenReturn(Optional.ofNullable(espaceDto));
        mockMvc.perform(get("/api/espace/1"))
                .andExpect(status().isOk());
        verify(espaceService, times(1)).getEspaceByID(1L);
    }

    @Test
    public void should_not_get_espace() throws Exception {
        when(espaceService.getEspaceByID(2L)).thenReturn(Optional.ofNullable(null));
        mockMvc.perform(get("/api/espace/2"))
                .andExpect(status().isNotFound());
        verify(espaceService, times(1)).getEspaceByID(2L);
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
