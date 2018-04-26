package assignment2.sd.TUCN_app_2.business;

import java.io.Serializable;

@SuppressWarnings("serial")
public class StudentDTO implements Serializable {
	
	private Integer groupId;
	private String studentIdentifier;
	
	public StudentDTO(Integer groupId, String studentIdentifier) {
		this.groupId = groupId;
		this.studentIdentifier = studentIdentifier;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getStudentIdentifier() {
		return studentIdentifier;
	}

	public void setStudentIdentifier(String studentIdentifier) {
		this.studentIdentifier = studentIdentifier;
	}
	
	

	
}
