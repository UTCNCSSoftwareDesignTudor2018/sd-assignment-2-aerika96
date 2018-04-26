package assignment2.sd.TUCN_app_2.persistence.respositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import assignment2.sd.TUCN_app_2.persistence.entities.Student;
import assignment2.sd.TUCN_app_2.persistence.entities.User;

public interface StudentRepository extends JpaRepository<Student,Integer> {
	
	public List<Student> findAllByStudentId(String studentId);
	
	public Student findByUser(User user);
	
	
}
