package com.sample;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SampleRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	private final static RowMapper<User> USER_ROW_MAPPER = (rs, rowNumber)->{
		
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setAge(rs.getInt("age"));
		
		return user;
	};
	
	
	public List<User> findAll(){
		
		String sql = "Select id, name, age From users Order By id;";
		
		List<User> userList = jdbcTemplate.query(sql, USER_ROW_MAPPER);
		
		return userList;
	}
}
