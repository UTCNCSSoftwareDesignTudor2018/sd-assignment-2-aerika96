package assignment2.sd.TUCN_app_2.persistence;


import assignment2.sd.TUCN_app_2.persistence.mongo.Report;

import assignment2.sd.TUCN_app_2.persistence.mongo.StudentsFailedExams;
import assignment2.sd.TUCN_app_2.persistence.mongo.StudentsTakingSameCourseReportDAO;
import assignment2.sd.TUCN_app_2.persistence.mongo.TeachersTaughtCoursesDAO;

public class ReportFactory {

	
	//use getReport method to get object of type shape
    public static final String STUDENT_COURSES = "StudentsTakingCourses";
    public static final String STUDENT_FAILED = "StudentsFailingCourses";
    public static final String TEACHER_COURSE = "TeacherCourses";
    
	public Report getReport(String reportType){
        if(reportType == null){
            return null;
        }
        if(reportType.equalsIgnoreCase(STUDENT_COURSES)){
            return new StudentsTakingSameCourseReportDAO();
        }
        if(reportType.equalsIgnoreCase(STUDENT_FAILED)) {
        	return new  StudentsFailedExams();
        }
        if(reportType.equalsIgnoreCase(TEACHER_COURSE)) {
        	return new  TeachersTaughtCoursesDAO();
        }

        return null;
}
	
}
