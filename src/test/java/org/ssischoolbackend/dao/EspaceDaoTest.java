package org.ssischoolbackend.dao;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import org.ssischoolbackend.model.Espace;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = {"classpath:test-data/espace-test.sql"},
        config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
public class EspaceDaoTest {
    @Autowired
    EspaceDao espaceDao;

    @Test
    public void should_get_all_espace() {
        List<Espace> espaces = espaceDao.getAllEspaces();
        assertThat(espaces).hasSize(3);
    }

    @Test
    public void should_not_get_espace() {
        Optional<Espace> espace = espaceDao.getEspaceById(6L);
        assertThat(espace.isPresent()).isFalse();
    }

    @Test
    public void should_get_espace() {
        Optional<Espace> espace = espaceDao.getEspaceById(2L);
        assertThat(espace.isPresent()).isTrue();
        assertThat(espace.get().getName()).isEqualTo("espace 2");
    }


    @Test
    public void should_create_new_espace(){
        long idEspace = espaceDao.createNewEspace(Espace.builder().name("espace 4").build());
        assertThat(espaceDao.getAllEspaces()).hasSize(4);
        assertThat(espaceDao.getEspaceById(idEspace).get().getName()).isEqualTo("espace 4");
    }

}
