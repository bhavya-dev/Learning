package com.dao;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.model.Student;

@Service
public class StudentDao {
	
	@Autowired
	StudentRepository studentRepository;

	public Student register(Student student) {
		Student s=studentRepository.save(student);
		return s;
	}

	public List<Student> getAllStudents() {
		List<Student> students= studentRepository.findAll();

		System.out.println("getting data"+students);
		return students;
	
	}

	public Student updateById(Student student) {
		Student student1=studentRepository.findById(student.getId()).get();
		
		if(student.getFirstName()!=null && !student.getFirstName().isEmpty()) {
			student1.setFirstName(student.getFirstName());
		}
		
		student1=studentRepository.save(student1);
	return student1;
	}

	public void deleteById(Integer id) {
		
		studentRepository.deleteById(id);
		System.out.println("delete by id");
		
	}

	public Student getStudentByFirstNameAndId(Integer id, String firstName) {
      Student student = studentRepository.findByIdAndFirstName(id,firstName);
      System.out.println(student);
		return student;
	}

	public Student getStudentByFirstNameORId(Integer id, String firstName) {
	    
		Student student = studentRepository.findByIdOrFirstName(id,firstName);
	      System.out.println(student);

        return student;
	}

	public List<Student> getAllStudentsByPaging(int page,int size) {
		
		Pageable pageable = PageRequest.of(page-1, size);
		return (List<Student>) studentRepository.findAll(pageable).getContent();
	}

	public List<Student> getAllStudentsBySorting() {
		
		Sort sort=Sort.by(Sort.Direction.ASC,"lastName");
		List<Student> student= studentRepository.findAll(sort);
		System.out.println("sorted"+student);
		return student;
	}
	
	
	
	
	

}
