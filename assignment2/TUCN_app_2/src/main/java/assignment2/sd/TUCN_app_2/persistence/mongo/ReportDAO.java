package assignment2.sd.TUCN_app_2.persistence.mongo;

public class ReportDAO {

	private static final String DRIVER = "mongodb";
	private static final String HOST = "localhost";
	private static final int PORT = 27017;
	private static final String DB_NAME = "tucn_spring";
	public String getURI() {
		return DRIVER + "://" + HOST + ":" + PORT;
	}
	public String getDBName() {
		return DB_NAME;
	}
}
