package assignment2.sd.TUCN_app_2.persistence.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;



@Entity
@Table(name = "courses")
public class Course {
	
	private Integer courseId;
	
	
	private String courseName;
	
	
	private Teacher teacher;
	
	
	private List<Student> students;
	
	public List<Grade> grades;
	

	@OneToMany (mappedBy = "course", fetch = FetchType.EAGER)
	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}


	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "courses_students", 
            joinColumns = { @JoinColumn(name = "courses_course_id") }, 
            inverseJoinColumns = { @JoinColumn(name = "student_student_id") }
        )
	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@Column
	@NotNull
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	
	@ManyToOne(cascade=CascadeType.ALL)  
	@NotNull
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	

}
