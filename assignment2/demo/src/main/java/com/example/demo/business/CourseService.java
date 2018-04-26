package assignment2.sd.TUCN_app_2.business;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import assignment2.sd.TUCN_app_2.persistence.entities.Course;
import assignment2.sd.TUCN_app_2.persistence.respositories.CourseRepository;

@Service()
public class CourseService {
	
	@Inject 
	CourseRepository courseRepository;
	
	public List<Course> getAllCourses(){
		return courseRepository.findAll();
	}
	
	public List<Course> getCoursesByTeacher(String teacherID){
		
		List<Course> coursesFilteredList = null;
		
		if(StringUtils.isEmpty(teacherID)) {
			
		}
		else {
			Integer teacherId = Integer.parseInt(teacherID);
			coursesFilteredList = courseRepository.findAllByTeacherId(teacherId);
		}
		
		return coursesFilteredList;
	}
	

}
