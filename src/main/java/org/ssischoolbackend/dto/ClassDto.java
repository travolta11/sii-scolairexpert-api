package org.ssischoolbackend.dto;

import org.modelmapper.ModelMapper;
import org.ssischoolbackend.model.Class;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassDto {
	private Long id;
	private String name;
	private Long nbOfStudents;
	private final static ModelMapper modelMapper = new ModelMapper();

	public static ClassDto getClassDto(Class clazz) {
		return modelMapper.map(clazz, ClassDto.class);
	}
}
