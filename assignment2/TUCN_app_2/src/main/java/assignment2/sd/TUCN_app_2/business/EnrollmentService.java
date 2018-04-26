package assignment2.sd.TUCN_app_2.business;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import assignment2.sd.TUCN_app_2.persistence.respositories.CourseRepository;

@Service()
public class EnrollmentService {

	@Inject 
	CourseRepository courseRepository;
}
