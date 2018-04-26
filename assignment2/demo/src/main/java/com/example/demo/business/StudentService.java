package assignment2.sd.TUCN_app_2.business;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.util.StringUtils;

import assignment2.sd.TUCN_app_2.persistence.entities.Student;
import assignment2.sd.TUCN_app_2.persistence.respositories.StudentRepository;

public class StudentService {

	@Inject 
	StudentRepository studentRepository;
	
	public List<Student> getAllStudents()
	{
		return studentRepository.findAll();
	}
	
	public Student getStudentById(Integer id) {
		
		Optional<Student> student =  studentRepository.findById(id);
		
		if(student.isPresent()) {
			return student.get();
		}
		return null;
	}
	
	
	public Student updateStudent(Integer studentId, StudentDTO student) {
		
		Student studentToBeUpdated =  studentRepository.findById(studentId).get();
		
		studentToBeUpdated.setGroupId(student.getGroupId());
		studentToBeUpdated.setStudentIdentifier(student.getStudentIdentifier());
		
		studentRepository.save(studentToBeUpdated);
		
		return studentToBeUpdated;
		
		
	}
}
