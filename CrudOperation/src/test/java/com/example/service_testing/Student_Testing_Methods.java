package com.example.service_testing;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.Entity.Student;
import com.example.Exception.NoDatafound;
import com.example.Repositery.Repo;
import com.example.Service.ServiceInterface;

import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;

@SpringBootTest
public class Student_Testing_Methods {

	@Autowired
	private ServiceInterface serviceInterface;
	@MockBean
	private Repo repo;

	@Test
	public void saveTest() {

		Student s1 = new Student();
		s1.setId(5);
		s1.setName("sitha");
		s1.setMobile("83838838");
		when(repo.save(any(Student.class))).thenReturn(s1);
		Student sSave = serviceInterface.sSave(s1);
		Assertions.assertNotNull(sSave);
		verify(repo, times(1)).save(any(Student.class));
	}
	
	@Test
	public void deleteTest() {
		int id = 6;
		String na = "data Deleted";
		String byIdMe = serviceInterface.deleteByIdMe(id);
		Assertions.assertEquals(na, byIdMe);
		verify(repo).deleteById(id);
	}

	@Test
	public void fetchByIdTest() {
		Student s1 = Student.builder().id(6).name("nari").mobile("7385").build();
		Student s2 = serviceInterface.fetchById(6);
		Assertions.assertEquals(s1, s2);
	}

	@BeforeEach
	public void setUp() {
		Optional<Student> student = Optional.of(new Student(6, "nari", "7385"));

		Mockito.when(repo.findById(6)).thenReturn(student);
	}

	@Test
	public void updateTest() throws NoDatafound {
		int id = 6;
		String result = "user data saved";
		Student stu = Student.builder().id(6).name("ravi").mobile("9090").build();

		String upsave = serviceInterface.upsave(stu, id);
		
		Assertions.assertEquals(result, upsave);

	}
  
	
	@BeforeEach
	public void setUp1() {

		List<Student> list = Arrays.asList(new Student(2, "raju", "12345"), new Student(3, "ramu", "78945"));

		when(repo.findAll()).thenReturn(list);

	}

	@Test
	public void getAllTest() {

		List<Student> expected = Arrays.asList(new Student(2, "raju", "12345"), new Student(3, "ramu", "78945"));

		List<Student> result = serviceInterface.getAllDeta();
		Assertions.assertEquals(expected, result);

	}
}
