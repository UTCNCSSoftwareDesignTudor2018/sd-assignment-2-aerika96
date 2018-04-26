package assignment2.sd.TUCN_app_2.persistence.respositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import assignment2.sd.TUCN_app_2.persistence.entities.Grade;

public interface GradeRepository extends JpaRepository<Grade,Integer> {

	public List<Grade> findAllByStudentStudentId(Integer studentId);
	public List<Grade> findAllByCourseCourseId(Integer courseId);
}
