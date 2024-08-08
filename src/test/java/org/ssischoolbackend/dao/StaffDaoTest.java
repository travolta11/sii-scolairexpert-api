package org.ssischoolbackend.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.ssischoolbackend.model.Department;
import org.ssischoolbackend.model.Gender;
import org.ssischoolbackend.model.Position;
import org.ssischoolbackend.model.Staff;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = {"classpath:test-data/staff-test.sql"},
        config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
public class StaffDaoTest {
    @Autowired
    StaffDao staffDao;

    @Test
    public void should_get_all_staff() {
        List<Staff> staffList = staffDao.getAllStaffMembers();
        assertThat(staffList).hasSize(3);
    }

    @Test
    public void should_not_get_staff_member() {
        Optional<Staff> staffMember= staffDao.getStaffById(20L);
        assertThat(staffMember.isPresent()).isFalse();
    }

    @Test
    public void should_get_staff_member() {
        Optional<Staff> staffMember = staffDao.getStaffById(2L);
        assertThat(staffMember.isPresent()).isTrue();
        assertThat(staffMember.get().getFirstName()).isEqualTo("Bob");
    }


    @Test
    public void should_create_new_staff_member(){
        long id = staffDao.createStaffMember(Staff.builder().firstName("rosa").lastName("ella").email("rosa@32gmail.com").gender(Gender.FEMALE).position(Position.TEACHER).department(Department.HR).build());
        assertThat(staffDao.getAllStaffMembers()).hasSize(4);
        assertThat(staffDao.getStaffById(id).get().getFirstName()).isEqualTo("rosa");
    }

    @Test
    public void should_delete_staff_member(){
        boolean deleted = staffDao.deleteStaffMember(1L);
        assertThat(deleted).isTrue();
    }

    @Test
    public void should_update_staff_member(){
        Staff staff = Staff.builder().id(1L).firstName("Alice").lastName("Bella").email("alice@32gmail.com").gender(Gender.FEMALE).position(Position.ASSISTANT).department(Department.HR).build();
        boolean updated = staffDao.updateStaffMember(staff);
        assertThat(updated).isTrue();
        assertThat(staffDao.getStaffById(1L).get().getLastName()).isEqualTo("Bella");
    }


}
