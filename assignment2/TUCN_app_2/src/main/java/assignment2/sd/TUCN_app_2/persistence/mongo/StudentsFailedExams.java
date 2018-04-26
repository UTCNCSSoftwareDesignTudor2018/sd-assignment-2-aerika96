package assignment2.sd.TUCN_app_2.persistence.mongo;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import assignment2.sd.TUCN_app_2.persistence.entities.Course;
import assignment2.sd.TUCN_app_2.persistence.entities.Grade;
import assignment2.sd.TUCN_app_2.persistence.entities.Student;
import assignment2.sd.TUCN_app_2.persistence.entities.Teacher;

public class StudentsFailedExams extends ReportDAO implements Report<Student> {

	private static final String COLLECTION_NAME  = "sudents_failed_courses";

	public void storeReport(Teacher teacher, List<Student> l, String reportName) throws UnknownHostException {
			MongoClient mongoClient = new MongoClient(new MongoClientURI(getURI()));
			DB mongoDatabase  =  mongoClient.getDB(getDBName());
			DBCollection databaseCollection = mongoDatabase.getCollection(COLLECTION_NAME);
			DBObject dbObject =  new BasicDBObject("reportName",reportName)
						.append("teacherId", teacher.getTeacherId());
			
			List<DBObject> dbStudentObjects = new ArrayList<DBObject>();
			
			for(Student stud: l) {
				DBObject dbStudentObject = new BasicDBObject("studentName", stud.getUser().getFirstName()+ " " +stud.getUser().getLastName())
						.append("teacherName", stud.getGroupId());
				List<Grade> grades =  stud.getGrades();
				List<Course> courses = new ArrayList<Course>();
				for (Grade grade: grades) {
					if(grade.getMark()<=4) {
						courses.add(grade.getCourse());
					}
				}
				List<DBObject> dbCourseObjects =  new ArrayList<DBObject>();
				
				for(Course course: courses) {
					DBObject dbCourseObject = new BasicDBObject("courseName", course.getCourseName());
					dbStudentObjects.add(dbStudentObject);
				}
				((BasicDBObject) dbStudentObject).append("courses",dbCourseObjects);
				dbStudentObjects.add(dbStudentObject);
				
				
			}
			
			((BasicDBObject) dbObject).append("student",dbStudentObjects)
				.append("date", new Date(Calendar.getInstance().getTime().getTime()));
			
			databaseCollection.insert(dbObject);
			mongoClient.close();
			
		
		
		
	}
	
	
}
