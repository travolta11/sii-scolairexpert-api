package org.ssischoolbackend.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.ssischoolbackend.model.Class;

@Repository
public class ClassDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public List<Class> getAllClasses() {
		return jdbcTemplate.query("SELECT * FROM class", Class::baseMapper);
	}

	public Class getClassById(Long id) {
		return jdbcTemplate.queryForObject("SELECT * FROM class WHERE id = :id", new MapSqlParameterSource("id", id),
				Class::baseMapper);
	}

	public long createClass(Class clazz) {
		MapSqlParameterSource params = new MapSqlParameterSource().addValue("class_name", clazz.getName())
				.addValue("number_of_students", clazz.getNbOfStudents());

		return jdbcTemplate.update(
				"INSERT INTO class (class_name, number_of_students) VALUES (:class_name, :number_of_students)", params);
	}

	public int updateClass(Class clazz) {
		MapSqlParameterSource params = new MapSqlParameterSource().addValue("id", clazz.getId())
				.addValue("class_name", clazz.getName()).addValue("number_of_students", clazz.getNbOfStudents());

		return jdbcTemplate.update(
				"UPDATE class SET class_name = :class_name, number_of_students = :number_of_students WHERE id = :id",
				params);
	}

	public int deleteClass(Long id) {
		return jdbcTemplate.update("DELETE FROM class WHERE id = :id", new MapSqlParameterSource("id", id));
	}
}
