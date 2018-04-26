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
import assignment2.sd.TUCN_app_2.persistence.entities.Teacher;

public class TeachersTaughtCoursesDAO extends ReportDAO implements Report<Teacher> {
	
	private static final String COLLECTION_NAME  = "teachers_taught_courses";
	
	public void storeReport (Teacher teacher, List<Teacher> teachers, String reportName) throws UnknownHostException {
		MongoClient mongoClient = new MongoClient(new MongoClientURI(getURI()));
		DB mongoDatabase  =  mongoClient.getDB(getDBName());
		DBCollection databaseCollection = mongoDatabase.getCollection(COLLECTION_NAME);
		DBObject dbObject =  new BasicDBObject("reportName",reportName)
					.append("teacherId", teacher.getTeacherId());
		
		List<DBObject> dbTeacherObjects = new ArrayList<DBObject>();
		
		for(Teacher t : teachers) {
			DBObject dbTeacherObject = new BasicDBObject("teacherName", t.getUser().getFirstName()+" "+t.getUser().getLastName());
			List<Course> courses = t.getCourses();
			List<DBObject> dbCourseObjects =  new ArrayList<DBObject>();
			
			for(Course c: courses) {
				DBObject dbCourseObject = new BasicDBObject("courseName", c.getCourseName());
				dbCourseObjects.add(dbCourseObject);
			}
			
			((BasicDBObject) dbTeacherObject).append("courses",dbCourseObjects);
			dbTeacherObjects.add(dbTeacherObject);
			
		}
		
		((BasicDBObject) dbObject).append("courses",dbTeacherObjects)
			.append("date", new Date(Calendar.getInstance().getTime().getTime()));
		
		databaseCollection.insert(dbObject);
		mongoClient.close();
		
	}

}
