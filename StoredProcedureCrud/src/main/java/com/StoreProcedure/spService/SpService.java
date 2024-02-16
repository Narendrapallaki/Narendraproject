package com.StoreProcedure.spService;

import java.sql.Types;
import java.util.HashMap;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import com.StoreProcedure.entity.Apple;

@Service
public class SpService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	

	public Map<String, Object> saveData(Apple apple) {

		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate);
		jdbcCall.withProcedureName("sp_create_apple");

		Map<String, Object> response = new HashMap<>();
		response.put("s_type", apple.getType());
		response.put("s_quality", apple.getQuality());
		Map<String, Object> execute = jdbcCall.execute(response);
		System.out.println("Service space :---------------------" + execute);
		return execute;

	}

	public Map<String, Object> getById(int id) {

		System.out.println("Service space-------" + id);
		
	SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate);
		jdbcCall.withProcedureName("sp_get_apple");
		jdbcCall.declareParameters(

				new SqlParameter("sp_id", Types.INTEGER), new SqlOutParameter("sp_id_out", Types.INTEGER),
				new SqlOutParameter("sp_type", Types.VARCHAR), new SqlOutParameter("sp_quality", Types.INTEGER)

		);
		Map<String, Object> response = new HashMap<>();

		response.put("sp_id", id);
		Map<String, Object> getResult = jdbcCall.execute(response);
		getResult.remove("#update-count-1", 1);
		System.out.println("service space result --------" + getResult);
		return getResult;

	}

	public Map<String, Object> getAll() {

		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate);
		System.out.println("Service space------- get all");
		jdbcCall.withProcedureName("sp_getAll");

		Map<String, Object> response = new HashMap<>();

		Map<String, Object> getResult = jdbcCall.execute(response);

//		  List<Map<String, Object>> resultSet = (List<Map<String, Object>>) getResult.get("#result-set-1");
//
//		    // Remove the "#result-set-1" key from the result map
//		    getResult.remove("#result-set-1");
//
//		    // Use the extracted result set as the actual result
//		    getResult.put("Result", resultSet);

		System.out.println("service space result --------" + getResult);
		return getResult;

	}

	
	
	
	public Map<String, Object>updateData(Apple apple,int id)
	{
		System.out.println("service method ----update"+apple+" and "+id);
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate);
		   jdbcCall.withProcedureName("sp_update");
		   jdbcCall.declareParameters(
		
				   new SqlParameter("sp_id",Types.INTEGER),
				   new SqlParameter("sp_type", Types.VARCHAR),
				   new SqlParameter("sp_quality", Types.INTEGER)
				   );
			Map<String, Object> response = new HashMap<>();

		response.put("sp_id", id);
		   response.put("sp_type", apple.getType());
		   response.put("sp_quality", apple.getQuality());
		
		Map<String,Object> execute = jdbcCall.execute(response);
		System.out.println("In side updatData "+execute);
		return execute;
		
	}
	
	
	public Map<String, Object> deleteByid(int id) {

		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate);

		jdbcCall.withProcedureName("sp_deleteById");

		jdbcCall.declareParameters(new SqlParameter("sp_id", Types.INTEGER));
		Map<String, Object> response = new HashMap<>();
		response.put("sp_id", id);
		Map<String, Object> getResult = jdbcCall.execute(response);
		getResult.remove("#update-count-1", 1);
		getResult.put("result ", "data deleted");
		return getResult;
	}

}
