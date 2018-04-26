package assignment2.sd.TUCN_app_2.business;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import assignment2.sd.TUCN_app_2.persistence.ReportFactory;
import assignment2.sd.TUCN_app_2.persistence.entities.Course;
import assignment2.sd.TUCN_app_2.persistence.entities.Grade;
import assignment2.sd.TUCN_app_2.persistence.entities.Student;
import assignment2.sd.TUCN_app_2.persistence.entities.Teacher;
import assignment2.sd.TUCN_app_2.persistence.entities.User;
import assignment2.sd.TUCN_app_2.persistence.mongo.Report;
import assignment2.sd.TUCN_app_2.persistence.mongo.ReportDAO;
import assignment2.sd.TUCN_app_2.persistence.mongo.StudentsFailedExams;
import assignment2.sd.TUCN_app_2.persistence.mongo.StudentsTakingSameCourseReportDAO;
import assignment2.sd.TUCN_app_2.persistence.respositories.CourseRepository;
import assignment2.sd.TUCN_app_2.persistence.respositories.GradeRepository;
import assignment2.sd.TUCN_app_2.persistence.respositories.StudentRepository;
import assignment2.sd.TUCN_app_2.persistence.respositories.UserRepository;

@Service()
public class StudentService {
	
	@Inject 
	CourseRepository courseRepository;
	
	@Inject
	GradeRepository gradeRepository;
	
	@Inject 
	UserRepository userRepository;
	

	private StudentRepository studentRepository;
	
	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository =  studentRepository;
	}
	
	public List<Student> getAllStudents()
	{
		return studentRepository.findAll();
	}
	
	public Student getStudentById(Integer studentId) {
		
		Student student =  studentRepository.findById(studentId).get();
		
		return student;
	}
	
	public Student getStudentByUser(Integer userId) {
		
		Student student = studentRepository.findByUserUserId(userId);
		
		return student;
		
	}
	
	public List<Course> getCoursesForStudent(Integer studentId){
		List<Course> coursesFiltered = null;
		
		if(studentRepository.findById(studentId).isPresent()) {
		
			coursesFiltered = studentRepository.findById(studentId).get().getCourses();
		}
		return coursesFiltered;
	}
	
	public List<Course> getNotEnrolled(Integer studentId){
		
		List<Course> coursesFiltered = new ArrayList<Course>();
		List<Course> studentCourses = null;
		List<Course> allCourses = null;
		
		allCourses =courseRepository.findAll();
		if(studentRepository.findById(studentId).isPresent()) {
			studentCourses = studentRepository.findById(studentId).get().getCourses();
		}
		
		for(Course course:allCourses) {
			if(!studentCourses.contains(course)) {
				coursesFiltered.add(course);
			}
		}
		
		return coursesFiltered;
		 
	}
	
	public List<Grade> getStudentGrades(Integer studentId){
		List<Grade> gradesFiltered = gradeRepository.findAllByStudentStudentId(studentId);
		
		return gradesFiltered;
	}
	
	public List<Student> getNotGraded(Integer courseId){
		List<Student> allTaking = courseRepository.findById(courseId).get().getStudents();
		List<Grade> allGrades  = courseRepository.findById(courseId).get().getGrades();
		
		for(Grade g: allGrades) {
			Student stud = g.getStudent();
			allTaking.remove(stud);
		}
		
		return allTaking;
	}
	
	public Student updateStudent(Integer studentId, StudentDTO student) {
		
		Student studentToBeUpdated =  studentRepository.findById(studentId).get();
		
		if(student.getGroupId()!=null)
			studentToBeUpdated.setGroupId(student.getGroupId());
		if(!student.getStudentIdentifier().isEmpty())
			studentToBeUpdated.setStudentIdentifier(student.getStudentIdentifier());
		
		studentRepository.save(studentToBeUpdated);
		
		return studentToBeUpdated;
		
		
	}
	
	public Student addNewStudent(Student newStudent, User newUser) {
		
		Student stud= new Student();
		stud.setStudentIdentifier(newStudent.getStudentIdentifier());
		stud.setGroupId(newStudent.getGroupId());
		stud.setUser(newUser);
		return studentRepository.save(stud);

	}
	
	public Student deleteStudent(String studentIdentifier) {
		Student stud  = studentRepository.findByStudentIdentifier(studentIdentifier);
		studentRepository.delete(stud);
		return stud;
	}
	public Student enrollMe(Integer studentId, Integer courseId) {
		Student studentToBeUpdated  =  studentRepository.findById(studentId).get();
		Course newCourse = courseRepository.findById(courseId).get();
		List<Course> studentCourses = studentToBeUpdated.getCourses();
		List<Student> courseStudents = newCourse.getStudents();
		
		studentCourses.add(newCourse);
		studentToBeUpdated.setCourses(studentCourses);
		studentRepository.save(studentToBeUpdated);
		
		courseStudents.add(studentToBeUpdated);
		newCourse.setStudents(courseStudents);
		courseRepository.save(newCourse);
		
		return studentToBeUpdated;
		
	}
	
	@SuppressWarnings("unchecked")
	public void createStudentsFailingReport(Teacher teacher) throws UnknownHostException {
		ReportFactory repoFactory = new ReportFactory();
		
		@SuppressWarnings("static-access")
		Report failingStudents = repoFactory.getReport(repoFactory.STUDENT_FAILED);
		(failingStudents).storeReport(teacher, studentRepository.findAll(), "Students failed courses");
	
	}
	
}
