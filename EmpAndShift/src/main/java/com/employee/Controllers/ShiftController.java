package com.employee.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.Entity.ShiftTimes;
import com.employee.service.ShiftService;

@RestController
public class ShiftController {

	
	@Autowired
	public ShiftService shiftService;
	
	@GetMapping("/getShift")
	public ResponseEntity<List<ShiftTimes>> getShiftId(@RequestParam("shiftTimeId") Long shiftTimeId)
	{
		return new ResponseEntity<List<ShiftTimes>>(shiftService.getByShiftId(shiftTimeId), HttpStatus.OK);
		
	}
}
