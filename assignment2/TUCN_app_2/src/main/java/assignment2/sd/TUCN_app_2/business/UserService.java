package assignment2.sd.TUCN_app_2.business;

import java.util.Optional;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import assignment2.sd.TUCN_app_2.persistence.entities.Student;
import assignment2.sd.TUCN_app_2.persistence.entities.User;
import assignment2.sd.TUCN_app_2.persistence.respositories.UserRepository;

@Service()
public class UserService {
 
	private UserRepository userRepository;
	
	@Autowired 
	public UserService(UserRepository userRepository) {
		this.userRepository =userRepository;
	}
	
	public User getUserById(Integer userId) {
		 User user =  userRepository.findByUserId(userId);
		 return user;
	}
	public User addNewUser(User user) {
		userRepository.save(user);
		return user;
	}
	
	public User deleteUser(User user) {
		userRepository.delete(user);
		return user;
	}
	public User updateUser(Integer userId, UserDTO user) {
		User userToBeUpdated =  userRepository.findById(userId).get();
		
		if(!user.getIdNumber().isEmpty())
			userToBeUpdated.setIdNumber(user.getIdNumber());
		if(!user.getCnp().isEmpty())
			userToBeUpdated.setCnp(user.getCnp());
		if(!user.getAddress().isEmpty())
			userToBeUpdated.setAddress(user.getAddress());
		if(!user.getFirstName().isEmpty())
			userToBeUpdated.setFirstName(user.getFirstName());
		if(!user.getLastName().isEmpty())
			userToBeUpdated.setLastName(user.getLastName());
		
		userRepository.save(userToBeUpdated);
		
		return userToBeUpdated;
		
	}
	
	
}
