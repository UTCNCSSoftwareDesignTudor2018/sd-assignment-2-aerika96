package assignment2.sd.TUCN_app_2.business;

import java.net.UnknownHostException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import assignment2.sd.TUCN_app_2.persistence.ReportFactory;
import assignment2.sd.TUCN_app_2.persistence.entities.Course;
import assignment2.sd.TUCN_app_2.persistence.entities.Teacher;
import assignment2.sd.TUCN_app_2.persistence.mongo.Report;
import assignment2.sd.TUCN_app_2.persistence.mongo.ReportDAO;
import assignment2.sd.TUCN_app_2.persistence.mongo.StudentsTakingSameCourseReportDAO;
import assignment2.sd.TUCN_app_2.persistence.respositories.CourseRepository;

@Service()
public class CourseService {
	
	private CourseRepository courseRepository;
	
	@Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
	public List<Course> getAllCourses(){
		return courseRepository.findAll();
	}
	
	public List<Course> getCoursesByTeacherId(Integer teacherId){
		
		return courseRepository.findAllByTeacherTeacherId(teacherId);
	}
	
	public Course getCourseById(Integer courseId) {
		return courseRepository.findById(courseId).get();
	}
	
	public Course addCourse(Course course) {
		courseRepository.save(course);
		
		return course;
		
	}
	
	@SuppressWarnings("unchecked")
	public void createCoursesWithStudentsReport(Teacher teacher) throws UnknownHostException {
		ReportFactory repoFactory = new ReportFactory();
		
		@SuppressWarnings("static-access")
		Report coursesWithStudents = repoFactory.getReport(repoFactory.STUDENT_COURSES);
		( coursesWithStudents).storeReport(teacher, courseRepository.findAll(), "Students of separate courses");
	}

}
