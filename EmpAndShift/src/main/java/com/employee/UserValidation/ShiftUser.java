package com.employee.UserValidation;

import java.time.LocalDate;
import java.time.LocalTime;

import com.employee.Entity.Employee;

import com.employee.localDateExce.LocalDateSolve;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ShiftUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shiftTimeId;
   //  private Long EmpId;
    @NotNull(message = "Date of Joining cannot be null")
    private LocalDate startDate;
    @NotNull(message = "Date of Joining cannot be null")
    private LocalDate endDate;
    private LocalTime shiftStartTime;
    private LocalTime shiftEndTime;
    private Long modifiedBy;
    private String weekOff;
    
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,targetEntity = Employee.class)
    @JoinColumn(name="empId")
    @JsonBackReference
    private Employee employee; 
   //    @JsonBackReference//    @JoinColumn(name="employee_id")
    
	public ShiftUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param shiftTimeId
	 * @param empId
	 * @param startDate
	 * @param endDate
	 * @param shiftStartTime
	 * @param shiftEndTime
	 * @param modifiedBy
	 * @param weekOff
	 * @param employee
	 */
	

	/**
	 * @return the shiftTimeId
	 */
	

	
	/**
	 * @param shiftTimeId
	 * @param startDate
	 * @param endDate
	 * @param shiftStartTime
	 * @param shiftEndTime
	 * @param modifiedBy
	 * @param weekOff
	 * @param employee
	 */
	public ShiftUser(Long shiftTimeId, @NotNull(message = "Date of Joining cannot be null") LocalDate startDate,
			@NotNull(message = "Date of Joining cannot be null") LocalDate endDate, LocalTime shiftStartTime,
			LocalTime shiftEndTime, Long modifiedBy, String weekOff, Employee employee) {
		super();
		this.shiftTimeId = shiftTimeId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.shiftStartTime = shiftStartTime;
		this.shiftEndTime = shiftEndTime;
		this.modifiedBy = modifiedBy;
		this.weekOff = weekOff;
		this.employee = employee;
	}

	/**
	 * @return the shiftTimeId
	 */
	public Long getShiftTimeId() {
		return shiftTimeId;
	}

	/**
	 * @param shiftTimeId the shiftTimeId to set
	 */
	public void setShiftTimeId(Long shiftTimeId) {
		this.shiftTimeId = shiftTimeId;
	}

	/**
	 * @return the startDate
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public LocalDate getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the shiftStartTime
	 */
	public LocalTime getShiftStartTime() {
		return shiftStartTime;
	}

	/**
	 * @param shiftStartTime the shiftStartTime to set
	 */
	public void setShiftStartTime(LocalTime shiftStartTime) {
		this.shiftStartTime = shiftStartTime;
	}

	/**
	 * @return the shiftEndTime
	 */
	public LocalTime getShiftEndTime() {
		return shiftEndTime;
	}

	/**
	 * @param shiftEndTime the shiftEndTime to set
	 */
	public void setShiftEndTime(LocalTime shiftEndTime) {
		this.shiftEndTime = shiftEndTime;
	}

	/**
	 * @return the modifiedBy
	 */
	public Long getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * @return the weekOff
	 */
	public String getWeekOff() {
		return weekOff;
	}

	/**
	 * @param weekOff the weekOff to set
	 */
	public void setWeekOff(String weekOff) {
		this.weekOff = weekOff;
	}

	/**
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "ShiftUser [shiftTimeId=" + shiftTimeId + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", shiftStartTime=" + shiftStartTime + ", shiftEndTime=" + shiftEndTime + ", modifiedBy=" + modifiedBy
				+ ", weekOff=" + weekOff + ", employee=" + employee + "]";
	}
	
	

	
  
}
