package assignment2.sd.TUCN_app_2.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import assignment2.sd.TUCN_app_2.business.StudentService;
import assignment2.sd.TUCN_app_2.business.UserDTO;
import assignment2.sd.TUCN_app_2.business.UserService;

@Controller
@RequestMapping ("/student")
public class StudentController {
	
	@Inject
	StudentService studentService;
	
	@Inject 
	UserService userService;
	
	@RequestMapping(value= "/show_user_data/{id}", method = RequestMethod.GET)
	public String displayUserData(@RequestParam Integer userId, Model model) {
		
		model.addAttribute("show_user_data",userService.getUserById(userId));
		
		return "show_user_data";
		
    	
	}
	
	@RequestMapping(value = "/{id}/change_username", method = RequestMethod.POST)
	public String changeUserName(@RequestParam Integer id, @RequestBody UserDTO user) {
		userService.updateUser(id, user);
		return "redirect: /show_user_data";
	}
	
	

}
