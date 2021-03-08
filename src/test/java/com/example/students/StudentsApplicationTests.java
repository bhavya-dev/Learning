package com.example.students;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.dao.StudentDao;
import com.dao.StudentRepository;
import com.model.Student;

@SpringBootTest
class StudentsApplicationTests {
	
	@Autowired
	StudentDao studentDao;
	
	@MockBean
	StudentRepository studentRepository;
	
	@Test
	void testRegister() {
		Student student=new Student(1,"kunam","Ramanamma","mupalla","paid");
		when(studentRepository.save(student)).thenReturn(student);
		assertEquals(student,studentDao.register(student));
	}


	
}
