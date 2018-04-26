package assignment2.sd.TUCN_app_2.persistence.respositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import assignment2.sd.TUCN_app_2.persistence.entities.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>{
	
	public List<Course> findAllByTeacherTeacherId(Integer teacherId);
	public List<Course> findAllByCourseName(String courseName);
	

}

