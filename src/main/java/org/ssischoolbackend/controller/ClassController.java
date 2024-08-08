package org.ssischoolbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ssischoolbackend.dto.ClassDto;
import org.ssischoolbackend.service.ClassService;

@RestController
@RequestMapping("/api/classes")
public class ClassController {

	@Autowired
	private ClassService classService;

	@GetMapping("/all")
	public List<ClassDto> getAllClasses() {
		return classService.getAllClasses();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClassDto> getClassById(@PathVariable Long id) {
		ClassDto clazz = classService.getClassById(id);
		if (clazz != null) {
			return ResponseEntity.ok(clazz);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Long> createClass(@RequestBody ClassDto classDto) {
		long id = classService.createClass(classDto);
		return ResponseEntity.ok(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> updateClass(@PathVariable Long id, @RequestBody ClassDto classDto) {
		classDto.setId(id);
		int rowsAffected = classService.updateClass(classDto);
		if (rowsAffected > 0) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteClass(@PathVariable Long id) {
		int rowsAffected = classService.deleteClass(id);
		if (rowsAffected > 0) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
