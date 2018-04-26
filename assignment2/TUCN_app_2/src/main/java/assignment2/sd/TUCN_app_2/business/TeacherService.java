package assignment2.sd.TUCN_app_2.business;

import java.net.UnknownHostException;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import assignment2.sd.TUCN_app_2.persistence.ReportFactory;
import assignment2.sd.TUCN_app_2.persistence.entities.Teacher;
import assignment2.sd.TUCN_app_2.persistence.mongo.Report;
import assignment2.sd.TUCN_app_2.persistence.mongo.ReportDAO;
import assignment2.sd.TUCN_app_2.persistence.mongo.StudentsFailedExams;
import assignment2.sd.TUCN_app_2.persistence.mongo.TeachersTaughtCoursesDAO;
import assignment2.sd.TUCN_app_2.persistence.respositories.TeacherRepository;

@Service()
public class TeacherService {

	@Inject
	TeacherRepository teacherRepository;
	
	public Teacher getTeacherByUser(Integer userId) {
		
		Teacher teacher = teacherRepository.findByUserUserId(userId);
		
		return teacher;
		
	}

	@SuppressWarnings("unchecked")
	public void createTeacherCoursesReport(Teacher teacher) throws UnknownHostException {
		ReportFactory repoFactory = new ReportFactory();
		
		@SuppressWarnings("static-access")
		Report teachersTeaching = repoFactory.getReport(repoFactory.TEACHER_COURSE);
		(teachersTeaching).storeReport(teacher, teacherRepository.findAll(), "Teachers taught courses ");
	
	}
	
}
