package org.ssischoolbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.ssischoolbackend.model.Materiel;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterielDto {
    private Long id;
    private String code;
    private String type;
    private String status;

    private final static ModelMapper modalMapper = new ModelMapper();

    public static MaterielDto fromEntity(Materiel materiel){
        return modalMapper.map(materiel,MaterielDto.class);
    }
}
