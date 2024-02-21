 package com.jdbc.StuController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jdbc.CustomExceptions.RecordNotFoundException;
import com.jdbc.Entity.Student;
import com.jdbc.StuService.StuServiceInter;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller//@RestController
public class StuController {

	@Autowired
	private StuServiceInter stuServiceInter;

	@PostMapping(value = "/save")
	public ResponseEntity<Map<String, Object>> save(@RequestBody Student student) {

	//	System.out.println("Controller space ----------" + student);
		Map<String, Object> response = new HashMap<>();

		Student save = stuServiceInter.save(student);
		response.put("Result :", save);
    
       log.info(save.toString());
		return new ResponseEntity<>(response, HttpStatus.CREATED);

	}

	@GetMapping(value = "/getAll")
	public ResponseEntity<Map<String, Object>> getAll() {

		Map<String, Object> response = new HashMap<>();

		List<Student> all = stuServiceInter.getAll();

		System.out.println("Controller space ----------" + all);

		response.put("Result :", all);

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Map<String, Object>> deleteById(@PathVariable int id) throws RecordNotFoundException {

		Map<String, Object> response = new HashMap<>();

		stuServiceInter.deleteById(id);

		response.put("Result :", "Data deleted in DB");
            log.warn(response.toString());
		return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);

	}

	@GetMapping(value = "/getById/{id}")
	public ResponseEntity<Map<String, Object>> getById(@PathVariable int id) throws RecordNotFoundException {

		Map<String, Object> response = new HashMap<>();

		Student byId = stuServiceInter.findById(id);

		System.out.println("Controller space ----------" + byId);

		response.put("Result :", byId);
        response.put("Status", HttpStatus.OK);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
    
	
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<Map<String, Object>> update(@RequestBody Student student,@PathVariable int id) throws RecordNotFoundException {

		Map<String, Object> response = new HashMap<>();

		Student update = stuServiceInter.update(student,id);

		System.out.println("Controller space ----------" + update);

		response.put("Result :", update);

		return new ResponseEntity<>(response, HttpStatus.OK);

	}
 
	@GetMapping("/")
     public String home()
     {
    	 return "home";
     }

	
}
