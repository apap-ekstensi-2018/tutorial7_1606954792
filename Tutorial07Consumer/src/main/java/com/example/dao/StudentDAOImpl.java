package com.example.dao;

import com.example.model.StudentModel;
import groovy.lang.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StudentDAOImpl implements StudentDAO
{
	@Autowired
	@Lazy
    //private List<StudentModel> std = new ArrayList<StudentModel>();
	private RestTemplate restTemplate;
	
	/*@Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }*/
	
	@Override
	public StudentModel selectStudent ( String npm)
	{
		StudentModel student = restTemplate.getForObject("http://localhost:8080/rest/student/view/"+npm,
                StudentModel.class);
        return student;
	}
	
	@Override
	public List <StudentModel> selectAllStudents ()
	{
		String url = "http://localhost:8080/rest/student/viewall";
        ResponseEntity<StudentModel[]> students = restTemplate.getForEntity(url, StudentModel[].class);
        return Arrays.asList(students.getBody());
	}
}