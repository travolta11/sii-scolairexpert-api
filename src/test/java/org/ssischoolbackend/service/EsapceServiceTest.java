package org.ssischoolbackend.service;

import org.ssischoolbackend.dao.EspaceDao;
import org.ssischoolbackend.dto.EspaceDto;
import org.ssischoolbackend.model.Espace;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EsapceServiceTest {
    @Mock
    private EspaceDao espaceDao;
    @InjectMocks
    private EspaceService espaceService;

    private Espace espace;
    private EspaceDto espaceDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        espace = Espace.builder().id(1L).name("espace 1").build();
        espaceDto = EspaceDto.builder().id(1L).name("espace 1").build();
    }

    @Test
    public void should_create_espace() {
        when(espaceDao.createNewEspace(espace)).thenReturn(1L);
        espaceService.createEspace(espaceDto);
        verify(espaceDao,times(1)).createNewEspace(espace);
    }

    @Test
    public void should_get_espace_by_id() {
        when(espaceDao.getEspaceById(anyLong())).thenReturn(Optional.ofNullable(espace));
        Optional<EspaceDto> espaceDto = espaceService.getEspaceByID(1L);
        verify(espaceDao, times(1)).getEspaceById(anyLong());
        assertThat(espaceDto).isNotEmpty();
    }

    @Test
    public void should_not_get_espace_by_id() {
        when(espaceDao.getEspaceById(anyLong())).thenReturn(Optional.ofNullable(null));
        Optional<EspaceDto> espaceDto = espaceService.getEspaceByID(1L);
        verify(espaceDao, times(1)).getEspaceById(anyLong());
        assertThat(espaceDto).isEmpty();
    }

    @Test
    public void should_get_all_espaces() {
        when(espaceDao.getAllEspaces()).thenReturn(Arrays.asList(espace));
        espaceService.getAllEsapces();
        verify(espaceDao, times(1)).getAllEspaces();
    }

}
