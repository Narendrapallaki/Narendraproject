package com.employee.UserValidation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.employee.Entity.ShiftTimes;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class EmployeUser {
	
	  
	    @jakarta.persistence.Id
	    private Long empId;
	    private String name;
	    @Email(regexp = "^(.+)@(.+)$",message = "must be email formate")
	    private String email;
	    @NotNull(message = "Date of Joining cannot be null")
	    private LocalDate dateJoin;
	    @Pattern(regexp = "[6789][0-9]{9}",message = "10 digits required")//"^\\d{10}$"
	    private String mobile;

	    private String status; // active or inactive
	    private boolean isDeleted;
	    
	    private String about;
	    @OneToMany(fetch = FetchType.EAGER, targetEntity = ShiftTimes.class,cascade = CascadeType.ALL)
		@JoinColumn(name="empId",referencedColumnName = "empId")
	
	    private List<ShiftTimes> shiftTimings;

		//@JsonManagedReference
	   
		public EmployeUser() {
			super();
			// TODO Auto-generated constructor stub
		}



		/**
		 * @param empId
		 * @param name
		 * @param email
		 * @param dateJoin
		 * @param mobile
		 * @param status
		 * @param isDeleted
		 * @param about
		 * @param shiftTimings
		 */
		public EmployeUser(Long empId, String name,
				@Email(regexp = "^(.+)@(.+)$", message = "must be email formate") String email,
				@NotNull(message = "Date of Joining cannot be null") LocalDate dateJoin,
				@Pattern(regexp = "[6789][0-9]{9}", message = "10 digits required") String mobile, String status,
				boolean isDeleted, String about, List<ShiftTimes> shiftTimings) {
			super();
			this.empId = empId;
			this.name = name;
			this.email = email;
			this.dateJoin = dateJoin;
			this.mobile = mobile;
			this.status = status;
			this.isDeleted = isDeleted;
			this.about = about;
			this.shiftTimings = shiftTimings;
		}



		/**
		 * @return the empId
		 */
		public Long getEmpId() {
			return empId;
		}



		/**
		 * @param empId the empId to set
		 */
		public void setEmpId(Long empId) {
			this.empId = empId;
		}



		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}



		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}



		/**
		 * @return the email
		 */
		public String getEmail() {
			return email;
		}



		/**
		 * @param email the email to set
		 */
		public void setEmail(String email) {
			this.email = email;
		}



		/**
		 * @return the dateJoin
		 */
		public LocalDate getDateJoin() {
			return dateJoin;
		}



		/**
		 * @param dateJoin the dateJoin to set
		 */
		public void setDateJoin(LocalDate dateJoin) {
			this.dateJoin = dateJoin;
		}



		/**
		 * @return the mobile
		 */
		public String getMobile() {
			return mobile;
		}



		/**
		 * @param mobile the mobile to set
		 */
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}



		/**
		 * @return the status
		 */
		public String getStatus() {
			return status;
		}



		/**
		 * @param status the status to set
		 */
		public void setStatus(String status) {
			this.status = status;
		}



		/**
		 * @return the isDeleted
		 */
		public boolean isDeleted() {
			return isDeleted;
		}



		/**
		 * @param isDeleted the isDeleted to set
		 */
		public void setDeleted(boolean isDeleted) {
			this.isDeleted = isDeleted;
		}



		/**
		 * @return the about
		 */
		public String getAbout() {
			return about;
		}



		/**
		 * @param about the about to set
		 */
		public void setAbout(String about) {
			this.about = about;
		}



		/**
		 * @return the shiftTimings
		 */
		public List<ShiftTimes> getShiftTimings() {
			return shiftTimings;
		}



		/**
		 * @param shiftTimings the shiftTimings to set
		 */
		public void setShiftTimings(List<ShiftTimes> shiftTimings) {
			this.shiftTimings = shiftTimings;
		}



		@Override
		public String toString() {
			return "EmployeUser [empId=" + empId + ", name=" + name + ", email=" + email + ", dateJoin=" + dateJoin
					+ ", mobile=" + mobile + ", status=" + status + ", isDeleted=" + isDeleted + ", about=" + about
					+ ", shiftTimings=" + shiftTimings + "]";
		}

		
	    

}
