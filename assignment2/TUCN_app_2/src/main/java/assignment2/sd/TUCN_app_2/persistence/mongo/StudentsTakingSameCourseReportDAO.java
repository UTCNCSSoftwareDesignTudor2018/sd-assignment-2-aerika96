package assignment2.sd.TUCN_app_2.persistence.mongo;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import assignment2.sd.TUCN_app_2.persistence.entities.Course;
import assignment2.sd.TUCN_app_2.persistence.entities.Student;
import assignment2.sd.TUCN_app_2.persistence.entities.Teacher;

public class StudentsTakingSameCourseReportDAO extends ReportDAO implements Report<Course> {

	private static final String COLLECTION_NAME  = "sudents_taking_same_course";
	
	public void storeReport (Teacher teacher, List<Course> l, String reportName) throws UnknownHostException {
		MongoClient mongoClient = new MongoClient(new MongoClientURI(getURI()));
		DB mongoDatabase  =  mongoClient.getDB(getDBName());
		DBCollection databaseCollection = mongoDatabase.getCollection(COLLECTION_NAME);
		DBObject dbObject =  new BasicDBObject("reportName",reportName)
					.append("teacherId", teacher.getTeacherId());
		
		List<DBObject> dbCourseObjects = new ArrayList<DBObject>();
		
		for(Course c: l) {
			DBObject dbCourseObject = new BasicDBObject("courseName", c.getCourseName())
					.append("teacherName", c.getTeacher().getUser().getLastName());
			List<Student> students = c.getStudents();
			List<DBObject> dbStudentObjects =  new ArrayList<DBObject>();
			
			for(Student s: students) {
				DBObject dbStudentObject = new BasicDBObject("studentName", s.getUser().getFirstName()+ " " + s.getUser().getLastName())
					.append("group", s.getGroupId())
					.append("studentIdentifier", s.getStudentIdentifier());
				dbStudentObjects.add(dbStudentObject);
			}
			
			((BasicDBObject) dbCourseObject).append("students",dbStudentObjects);
			dbCourseObjects.add(dbCourseObject);
			
		}
		
		((BasicDBObject) dbObject).append("courses",dbCourseObjects)
			.append("date", new Date(Calendar.getInstance().getTime().getTime()));
		
		databaseCollection.insert(dbObject);
		mongoClient.close();
		
	}
	
	
}
