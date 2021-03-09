package com.example.students;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.controller.StudentController;
import com.dao.StudentDao;
import com.dao.StudentRepository;
import com.model.Student;

@ExtendWith(MockitoExtension.class)
class StudentsApplicationTests {

	@InjectMocks
	StudentDao studentDao;

	@Mock
	StudentRepository studentRepository;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testRegister() {
		Student student = new Student(1, "kunam", "Ramanamma", "mupalla", "paid");
		Mockito.when(studentRepository.save(student)).thenReturn(student);
		Assertions.assertEquals(student, studentDao.register(student));
	}

	@Test
	void getAllStudentsTest() {
		Mockito.when(studentRepository.findAll())
				.thenReturn(Stream
						.of(new Student(1, "kunam", "Ramanamma", "mupalla", "paid"),
								new Student(2, "Alla", "Ramakrishna", "kandukur", "Notpaid"))
						.collect(Collectors.toList()));
		Assertions.assertEquals(2, studentDao.getAllStudents().size());
	}

	@Test
	void getStudentByFirstNameAndIdTest() {
		Integer id = 1;
		String firstName = "Ramanamma";
		Student student = new Student(1, "kunam", "Ramanamma", "mupalla", "paid");
		Mockito.when(studentRepository.findByIdAndFirstName(id, firstName)).thenReturn(student);
		Assertions.assertEquals(student, studentDao.getStudentByFirstNameAndId(id, firstName));
	}

	@Test
	void getStudentByFirstNameORIdTest() {
		Integer id = 1;
		String firstName = "Ramanamma";
		Student student = new Student(1, "k", "venkata", "mupalla", "paid");
		Mockito.when(studentRepository.findByIdOrFirstName(id, firstName)).thenReturn(student);
		Assertions.assertEquals(student, studentDao.getStudentByFirstNameORId(id, firstName));
	}

	@Test
	void deleteByIdTest() {
		Integer id = 5;
		Student student = new Student(1, "kunam", "Ramanamma", "mupalla", "paid");

		studentDao.deleteById(id);
		Mockito.verify(studentRepository, Mockito.times(1)).deleteById(id);
	}

	@Test
	void getAllStudentsBySortingTest() {

		Sort sort = Sort.by(Sort.Direction.ASC, "lastName");
		Mockito.when(studentRepository.findAll(sort))
				.thenReturn(Stream.of(new Student(2, "kunam", "Ramanamma", "mupalla", "paid"),
						new Student(1, "Alla", "krishna", "kandukur", "Notpaid")).collect(Collectors.toList()));
		Assertions.assertEquals(2, studentDao.getAllStudentsBySorting().size());
	}
	
	
	
}
