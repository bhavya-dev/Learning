package com.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dao.StudentDao;
import com.model.Student;


@RestController
public class StudentController {

	Logger logger= LoggerFactory.getLogger(StudentController.class);


	@Autowired
	StudentDao studentDao;

	@PostMapping("/register")
	public void register(@RequestBody Student student) {

		Student s1=studentDao.register(student);
	}

	@GetMapping("/getAllStudents")
	public List<Student> getAllStudents(){
		List<Student> students=studentDao.getAllStudents();

		return students;
	}

	@PutMapping("/update")
	public Student update(@RequestBody Student student ) {
		//logger.info("student"+student);
		
		Student student1=studentDao.updateById(student);

		logger.info("student"+student1);

		return student1;
	}

	@DeleteMapping("/deleteById/{id}")
	public void deleteById(@PathVariable Integer id) {
		studentDao.deleteById(id);
	}

	@GetMapping("/getStudentByFirstNameAndId/{id}/{firstName}")
	public Student getStudentByFirstNameAndId(@PathVariable Integer id,@PathVariable String firstName) {

		Student student=studentDao.getStudentByFirstNameAndId(id,firstName);
		return student;
	}

	@GetMapping("/getStudentByFirstNameOrId/{id}/{firstName}")
	public Student getStudentByFirstNameOrId(@PathVariable Integer id,@PathVariable String firstName) {
		Student student=studentDao.getStudentByFirstNameORId(id,firstName);
		return student;
	}

	@GetMapping("/getAllStudentsByPaging/{pageNo}/{pageSize}")
	public List<Student> getAllStudentsByPaging(@PathVariable Integer pageNo,@PathVariable Integer pageSize){
		List<Student> students=studentDao.getAllStudentsByPaging(pageNo,pageSize);

		return students;
	}

	@GetMapping("/getAllStudentsBySorting")
	public List<Student> getAllStudentsBySorting(){
		List<Student> students=studentDao.getAllStudentsBySorting();

		return students;
	}


}
