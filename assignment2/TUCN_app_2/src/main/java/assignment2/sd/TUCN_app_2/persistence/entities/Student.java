package assignment2.sd.TUCN_app_2.persistence.entities;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity 
@Table (name = "students")
public class Student {
	
	private Integer studentId;
	
	
	private Integer groupId;
	
	private String studentIdentifier;
	
	
	private User user;
	
	private List<Course> courses;

	
	private List<Grade> grades;
	
	public Student() {
		
	}

	public Student(Integer groupId, String studentIdentifier ) {
		this.groupId = groupId;
		this.studentIdentifier = studentIdentifier;
	}

	@OneToMany (mappedBy = "student", fetch = FetchType.EAGER)
	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

	@Column
	@NotNull
	public Integer getGroupId() {
		
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}


	@Column
	@NotNull
	public String getStudentIdentifier() {
		return studentIdentifier;
	}

	public void setStudentIdentifier(String studentIdentifier) {
		this.studentIdentifier = studentIdentifier;
	}


	@OneToOne
	@JoinColumn (name = "user")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}



	@ManyToMany (mappedBy = "students", fetch = FetchType.LAZY)
	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	
	
	
	
	
	
	

}
