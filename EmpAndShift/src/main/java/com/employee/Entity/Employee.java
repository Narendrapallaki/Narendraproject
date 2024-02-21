package com.employee.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table
public class Employee {
	
	 
	 
	    @Id
	    private Long empId;
	    private String name;
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
		@JsonManagedReference
	    private List<ShiftTimes> shiftTimings;
		 
		public Employee() {
			super();
			// TODO Auto-generated constructor stub
		}
		//, List<ShiftTimes> shiftTimings

		/**
		 * @param id
		 * @param name
		 * @param email
		 * @param dateJoin
		 * @param mobile
		 * @param status
		 * @param isDeleted
		 * @param about
		 * @param shiftTimings
		 */
		public Employee(Long empId, String name, String email,
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
		 * @return the id
		 */
		public Long getId() {
			return empId;
		}

		/**
		 * @param id the id to set
		 */
		public void setId(Long empId) {
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

	
	    
	    

}
