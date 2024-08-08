package org.ssischoolbackend.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Class {
	private Long id;
	private String name;
	private Long nbOfStudents;

	public static Class baseMapper(ResultSet resultSet, int rowNumber) throws SQLException {
		Class clazz = new Class();
		clazz.setId(resultSet.getLong("id"));
		clazz.setName(resultSet.getString("class_name"));
		clazz.setNbOfStudents(resultSet.getLong("number_of_students"));
		return clazz;
	}

}
