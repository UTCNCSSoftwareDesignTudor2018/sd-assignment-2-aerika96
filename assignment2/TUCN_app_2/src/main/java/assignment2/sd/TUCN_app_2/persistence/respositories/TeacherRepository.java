package assignment2.sd.TUCN_app_2.persistence.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import assignment2.sd.TUCN_app_2.persistence.entities.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer>{

	public Teacher findByUserUserId(Integer userId);
	
}
