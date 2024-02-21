package com.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.EmpRepo.ShifRepo;
import com.employee.Entity.ShiftTimes;

@Service
public class ShiftService {

	
	@Autowired
	public ShifRepo shifRepo;
	
	public List<ShiftTimes> getByShiftId(Long shiftTimeId)
	{
		return shifRepo.findByShiftTimeId(shiftTimeId);
		
	}
}
