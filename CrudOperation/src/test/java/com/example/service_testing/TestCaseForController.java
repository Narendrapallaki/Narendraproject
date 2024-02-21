package com.example.service_testing;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.RETURNS_DEFAULTS;
import static org.mockito.Mockito.mockitoSession;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.Controllerr.Contro;
import com.example.Entity.Student;
import com.example.Service.ServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.minidev.json.JSONUtil;
@WebMvcTest(Contro.class)
@AutoConfigureMockMvc
public class TestCaseForController {

   @MockBean
   private ServiceInterface serInter;

   @Autowired
   private MockMvc mockMvc;

   @Autowired
   private Contro contro;
	
	   @Test
	   public void getAllTest()
	   {
		   // mocking the data 
		         List<Student> stu=Arrays.asList(
		        		 new Student(1, "nari", "9676842785"),
		        		 new Student(5, "raju", "8527847696"));
		         
		         Mockito.when(serInter.getAllDeta()).thenReturn(stu);
		         
		         //testing the controller 
		         ResponseEntity<List<Student>> response = contro.getAll();
		         
		         assertEquals(HttpStatus.OK, response.getStatusCode());
		         
		         Assertions.assertEquals(stu, response.getBody());
	   }
	    
	  @Test
	  public void saveDataTest()
	  {
		     //actual
		       Student stu=Student.builder().id(1).name("rahul").mobile("939393").build();
		       
		       //expected
		       Student s1=Student.builder().id(1).name("rahul").mobile("939393").build();
		       
		       Mockito.when(serInter.sSave(stu)).thenReturn(s1);
		       
		       ResponseEntity<Object> savaMethod = contro.savaMethod(stu);
		       
		       assertEquals(HttpStatus.CREATED, savaMethod.getStatusCode());
		       Assertions.assertEquals(savaMethod.getBody(), stu);
	  }
	  
	/*  @Test
	  public void saveTest()
	  {
		  // actual
		  Student stu=Student.builder().id(1).name("rahul").mobile("939393").build();
	       
	       //expected
	       Student s1=Student.builder().id(1).name("rahul").mobile("939393").build();
	       Mockito.when(serInter.sSave(stu)).thenReturn(s1);
	       
	          ResponseEntity<Object> saveData = contro.savaMethod(stu);
	          
	          mockMvc.perform(post("/postData")
	        		  .contentType(MediaType.APPLICATION_JSON)
	        	     .content(JSONUtil.convertToStrict(saveData, getClass())
	        	          );
	        	     
	          
	       
	  }*/
	  
	  @Test
	    public void saveIntegrationTest() throws Exception {
	    
	        Student stu = Student.builder().id(1).name("rahul").mobile("939393").build();
	        Student s1 = Student.builder().id(1).name("rahul").mobile("939393").build();
	        Mockito.when(serInter.sSave(stu)).thenReturn(s1);

	       
	        mockMvc.perform(MockMvcRequestBuilders.post("/postData")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(asJsonString(stu)))
	                .andExpect(MockMvcResultMatchers.status().isCreated())
	                .andExpect(MockMvcResultMatchers.content().json(asJsonString(s1)));

	     
	    }
	  private static String asJsonString(Object object) throws Exception {
	        ObjectMapper objectMapper = new ObjectMapper();
	        return objectMapper.writeValueAsString(object);
	    }
        
}
