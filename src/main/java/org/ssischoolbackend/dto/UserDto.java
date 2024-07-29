package org.ssischoolbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.ssischoolbackend.model.User;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String role;

    private final static ModelMapper modelMapper = new ModelMapper();

    public static UserDto fromEntity(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}
