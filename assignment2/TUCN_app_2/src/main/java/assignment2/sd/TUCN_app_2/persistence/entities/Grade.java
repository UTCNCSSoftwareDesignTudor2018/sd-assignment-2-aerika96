package assignment2.sd.TUCN_app_2.persistence.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "grades")
public class Grade {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer gradeId;
	
	@Column
	@NotNull
	private Integer mark;
	
	@ManyToOne(cascade=CascadeType.ALL) 
	@JoinColumn(name = "course")
	@NotNull
	private Course course;
	
	@ManyToOne(cascade=CascadeType.ALL) 
	@JoinColumn(name = "student")
	@NotNull
	private Student student;
	
	public Grade() {
		
	}
	
	public Grade(Integer mark, Course course, Student student) {
		this.mark = mark;
		this.course = course;
		this.student = student;
	}

	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	

	public Integer getMark() {
		return mark;
	}

	public void setMark(Integer mark) {
		this.mark = mark;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	
	
}
