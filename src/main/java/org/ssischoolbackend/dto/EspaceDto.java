package org.ssischoolbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import org.ssischoolbackend.model.Espace;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EspaceDto {
    private Long id;
    private String name;
    private final static ModelMapper modelMapper = new ModelMapper();

    public static EspaceDto getTestDto(Espace espace) {
        return modelMapper.map(espace, EspaceDto.class);
    }

}
