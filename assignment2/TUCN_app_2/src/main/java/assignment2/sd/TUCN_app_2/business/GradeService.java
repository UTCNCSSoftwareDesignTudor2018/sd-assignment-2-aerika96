package assignment2.sd.TUCN_app_2.business;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import assignment2.sd.TUCN_app_2.persistence.entities.Course;
import assignment2.sd.TUCN_app_2.persistence.entities.Grade;
import assignment2.sd.TUCN_app_2.persistence.entities.Student;
import assignment2.sd.TUCN_app_2.persistence.respositories.GradeRepository;

@Service()
public class GradeService {

	@Inject
	GradeRepository gradeRepository;
	
	public Grade gradeStudent(Student student, Course course, Integer mark ) {
		
		Grade grade = new Grade(mark,course,student);
		
		gradeRepository.save(grade);
		return grade;
		
		
	}
}
