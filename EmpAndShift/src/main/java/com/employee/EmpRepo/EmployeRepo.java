package com.employee.EmpRepo;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.employee.Entity.Employee;
import com.employee.Entity.ShiftTimes;


@Repository
public interface EmployeRepo extends CrudRepository<Employee, Long>
{

	//void saveAll(Iterable<S> userreq);
	//Optional<Employee>getActiveEmploye(String sts,Long id);
	
	List<Employee> findByStatus(String status);
	
	 //List<ShiftTimes>getByShiftId(Long shiftTimeId);
}
