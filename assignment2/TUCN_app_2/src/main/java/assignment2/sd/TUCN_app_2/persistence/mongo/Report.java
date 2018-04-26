package assignment2.sd.TUCN_app_2.persistence.mongo;

import java.net.UnknownHostException;
import java.util.List;

import assignment2.sd.TUCN_app_2.persistence.entities.Teacher;

public interface Report <T> {

	 public void storeReport(Teacher teacher, List<T> l, String reportName) throws UnknownHostException;
	 
}
