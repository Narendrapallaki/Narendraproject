package com.example.Service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Student;
import com.example.Exception.NoDatafound;
import com.example.Repositery.Repo;



@Service
public class ServiceInter implements ServiceInterface {

	@Autowired
	public Repo repo;

	@Override
	public Student sSave(Student student) {
		
		// TODO Auto-generated method stub
		Student save = repo.save(student);
		return save;
	}

	@Override
	public List<Student> getAllDeta() {
		
		return (List<Student>) repo.findAll();
	}

	@Override
	public String deleteByIdMe(Integer integer) {
		// TODO Auto-generated method stub
		
		repo.deleteById(integer);
		
		
		return "data Deleted";
		
	}

//	@Override
//	public Student updateById(Integer id) {
//		// TODO Auto-generated method stub
//		Student student = repo.findById(id).get();
//		return student;
//	}

	@Override
	public String upsave(Student us,int id) throws NoDatafound {
		// TODO Auto-generated method stub
		Student student = repo.findById(id).orElseThrow(  ()-> new NoDatafound("Id not found")
				);
		//student.setId(us.getId());
	
		student.setMobile(us.getMobile());
		student.setName(us.getName());
	System.out.println("service method updated details ----------------"+student);
		repo.save(student);
		return "user data saved";
	}

@Override
public Student fetchById(int id) {
	// TODO Auto-generated method stub
	return repo.findById(id).get();
}

    
	
	
     
	
	

	

	

	

	
	


}
