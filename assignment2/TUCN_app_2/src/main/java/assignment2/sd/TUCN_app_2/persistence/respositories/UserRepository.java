package assignment2.sd.TUCN_app_2.persistence.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import assignment2.sd.TUCN_app_2.persistence.entities.User;

public interface UserRepository  extends JpaRepository<User,Integer>{
	
	public User findByUserId(Integer userId);

}
