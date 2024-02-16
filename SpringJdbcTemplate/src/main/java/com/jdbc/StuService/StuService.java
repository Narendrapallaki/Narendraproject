package com.jdbc.StuService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.jdbc.CustomExceptions.RecordNotFoundException;
import com.jdbc.Entity.Student;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class StuService implements StuServiceInter {
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	String save = "INSERT INTO student (id, name, marks) VALUES (?, ?, ?)";

	String delete = "DELETE FROM student WHERE id = ?";
	String getAll = "SELECT * FROM student";
	String findById = "SELECT * FROM student WHERE id = ?";

	@Override
	public Student save(Student student) {
		System.out.println("Service space--------" + student);

		int update2 = jdbcTemplate.update(save, student.getId(), student.getName(), student.getMarks());
		if (update2 > 0) {
			System.out.println("Data saved");
			log.info("Data saved!");
		} else {
			System.out.println("Data not inserted");
			log.error("Data not inserted into Db");
		}
		return student;
	}

	@Override
	public void deleteById(int id) throws RecordNotFoundException { 
		try {

			Student queryForObject = jdbcTemplate.queryForObject(findById,
					(rs, rowNum) -> new Student(rs.getInt("id"), rs.getString("name"), rs.getInt("marks")), id);

			jdbcTemplate.update(delete, id);

		} catch (Exception e) {
			
			throw new RecordNotFoundException("RecordNotFound " + id + " In data base ");
		}

	}

	@Override
	public List<Student> getAll() { 

		List<Student> query = jdbcTemplate.query(getAll,
				(rs, rowNum) -> new Student(rs.getInt("id"), rs.getString("name"), rs.getInt("marks")));

		return query;
	}

	@Override
	public Student findById(int id) throws RecordNotFoundException {
		

		try {
			Student queryForObject = jdbcTemplate.queryForObject(findById,
					(rs, rowNum) -> new Student(rs.getInt("id"), rs.getString("name"), rs.getInt("marks")), id);
			return queryForObject;

		} catch (EmptyResultDataAccessException e) {
			
			throw new RecordNotFoundException("RecordNotFound " + id + " In data base ");
		}

	}

	@Override
	public Student update(Student student, int id) throws RecordNotFoundException {

		String update = "UPDATE student SET name = ?, marks = ? WHERE id = ?";
		int update2 = jdbcTemplate.update(update, student.getName(), student.getMarks(), id);

		if (update2 > 0) {

			System.out.println("Data updated.........");

		} else {
			System.out.println("Data not updated........");
		}

		return student;
	}

}
