package org.ssischoolbackend.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.ssischoolbackend.model.Subject;

import java.util.List;

@Repository
public class SubjectDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public List<Subject> getAllSubjects() {
		return jdbcTemplate.query("SELECT * FROM subject", Subject::baseMapper);
	}

	public Subject getSubjectById(Long id) {
		return jdbcTemplate.queryForObject("SELECT * FROM subject WHERE id = :id", new MapSqlParameterSource("id", id),
				Subject::baseMapper);
	}

	public long createSubject(Subject subject) {
		MapSqlParameterSource params = new MapSqlParameterSource().addValue("name", subject.getName())
				.addValue("coefficient", subject.getCoefficient()).addValue("teacher_id", subject.getTeacherId());

		return jdbcTemplate.update(
				"INSERT INTO subject (name, coefficient, teacher_id) VALUES (:name, :coefficient, :teacher_id)",
				params);
	}

	public int updateSubject(Subject subject) {
		MapSqlParameterSource params = new MapSqlParameterSource().addValue("id", subject.getId())
				.addValue("name", subject.getName()).addValue("coefficient", subject.getCoefficient())
				.addValue("teacher_id", subject.getTeacherId());

		return jdbcTemplate.update(
				"UPDATE subject SET name = :name, coefficient = :coefficient, teacher_id = :teacher_id WHERE id = :id",
				params);
	}

	public int deleteSubject(Long id) {
		return jdbcTemplate.update("DELETE FROM subject WHERE id = :id", new MapSqlParameterSource("id", id));
	}

	public List<Subject> getSubjectsByClassId(Long classId) {
		return jdbcTemplate.query(
				"SELECT s.* FROM subject s " + "JOIN class_subject cs ON s.id = cs.subject_id "
						+ "WHERE cs.class_id = :class_id",
				new MapSqlParameterSource("class_id", classId), Subject::baseMapper);
	}

}
