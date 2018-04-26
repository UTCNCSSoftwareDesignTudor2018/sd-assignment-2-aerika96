package assignment2.sd.TUCN_app_2.business;

import java.util.Optional;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import assignment2.sd.TUCN_app_2.persistence.entities.User;
import assignment2.sd.TUCN_app_2.persistence.respositories.UserRepository;

@Service()
public class UserService {

	@Inject 
	UserRepository userRepository;
	
	public User getUserById(Integer userId) {
		 Optional<User> user =  userRepository.findById(userId);
		 if(user.isPresent()) {
			 user.get();
		 }
		 return null;
	}
	
	public User updateUser(Integer userId, UserDTO user) {
		User userToBeUpdated =  userRepository.findById(userId).get();
		
		userToBeUpdated.setUserName(user.getUserName());
		userToBeUpdated.setIdNumber(user.getIdNumber());
		userToBeUpdated.setCnp(user.getCnp());
		userToBeUpdated.setAddress(user.getAddress());
		userToBeUpdated.setFirstName(user.getFirstName());
		userToBeUpdated.setLastName(user.getLastName());
		
		userRepository.save(userToBeUpdated);
		
		return userToBeUpdated;
		
	}
	
	
}
