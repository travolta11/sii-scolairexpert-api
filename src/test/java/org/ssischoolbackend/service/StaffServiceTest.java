package org.ssischoolbackend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.ssischoolbackend.dao.StaffDao;
import static org.assertj.core.api.Assertions.assertThat;
import org.ssischoolbackend.dto.StaffDto;
import org.ssischoolbackend.model.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StaffServiceTest {

    @Mock
    private StaffDao staffDao;

    @InjectMocks
    private StaffService staffService;

    private Staff staff;
    private StaffDto staffDto;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        staff = Staff.builder().
                id(1L).
                firstName("rosa").
                lastName("ella").
                email("rosa@32gmail.com").
                gender(Gender.Female).
                position(Position.TEACHER).
                department(Department.HR).
                build();
        staffDto = StaffDto.builder().
                id(1L).
                firstName("rosa").
                lastName("ella").
                email("rosa@32gmail.com").
                gender(Gender.Female).
                position(Position.TEACHER).
                department(Department.HR).
                build();

    }

    @Test
    public void should_create_staff_member(){
        when(staffDao.createStaffMember(staff)).thenReturn(1L);
        staffService.createStaffMember(staffDto);
        verify(staffDao,times(1)).createStaffMember(staff);
    }

    @Test
    public void should_get_staff_member_by_id(){
        when(staffDao.getStaffById(anyLong())).thenReturn(Optional.ofNullable(staff));
        Optional<StaffDto> staffDto = staffService.getStaffByID(1L);
        verify(staffDao,times(1)).getStaffById(anyLong());
        assertThat(staffDto).isNotEmpty();
    }
    @Test
    public void should_not_get_staff_member_by_id(){
        when(staffDao.getStaffById(anyLong())).thenReturn(Optional.ofNullable(null));
        Optional<StaffDto> staffDto = staffService.getStaffByID(10L);
        verify(staffDao,times(1)).getStaffById(anyLong());
        assertThat(staffDto).isEmpty();
    }

    @Test
    public void should_get_all_staff_members(){
        when(staffDao.getAllStaffMembers()).thenReturn(Arrays.asList(staff));
        List<StaffDto> staffDtos = staffService.getAllStaff();
        verify(staffDao,times(1)).getAllStaffMembers();
    }

    @Test
    public void should_delete_staff_member(){
        when(staffDao.deleteStaffMember(anyLong())).thenReturn(true);
        boolean deleted=staffService.deleteStaffMember(1L);
        verify(staffDao,times(1)).deleteStaffMember(anyLong());
        assertThat(deleted).isTrue();
    }

    @Test
    public void should_not_delete_staff_member(){
        when(staffDao.deleteStaffMember(anyLong())).thenReturn(false);
        boolean deleted=staffService.deleteStaffMember(20L);
        verify(staffDao,times(1)).deleteStaffMember(anyLong());
        assertThat(deleted).isFalse();
    }

    @Test
    public void should_update_staff_member(){
        when(staffDao.updateStaffMember(staff)).thenReturn(true);
        boolean updated=staffService.updateStaffMember(staffDto);
        verify(staffDao,times(1)).updateStaffMember(staff);
        assertThat(updated).isTrue();
    }

    @Test
    public void should_not_update_staff_member(){
        when(staffDao.updateStaffMember(staff)).thenReturn(false);
        boolean updated=staffService.updateStaffMember(staffDto);
        verify(staffDao,times(1)).updateStaffMember(staff);
        assertThat(updated).isFalse();
    }


}
