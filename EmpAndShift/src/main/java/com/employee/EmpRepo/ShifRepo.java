package com.employee.EmpRepo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.employee.Entity.ShiftTimes;

@Repository
public interface ShifRepo extends CrudRepository<ShiftTimes, Long>
{
   //   List<ShiftTimes>getByShiftIdShiftTable(Long shiftTimeId);
	
	List<ShiftTimes>findByShiftTimeId(Long shiftTimeId);
}
