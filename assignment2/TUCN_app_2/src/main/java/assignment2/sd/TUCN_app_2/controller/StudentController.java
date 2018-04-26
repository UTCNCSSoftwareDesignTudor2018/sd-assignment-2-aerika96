package assignment2.sd.TUCN_app_2.controller;

import java.net.UnknownHostException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import assignment2.sd.TUCN_app_2.business.CourseService;
import assignment2.sd.TUCN_app_2.business.StudentDTO;
import assignment2.sd.TUCN_app_2.business.StudentService;
import assignment2.sd.TUCN_app_2.business.UserDTO;
import assignment2.sd.TUCN_app_2.business.UserService;

@Controller
//@RequestMapping ("/student")
public class StudentController {
	
	@Inject
	StudentService studentService;
	
	@Inject 
	UserService userService;
	
	@Inject
	CourseService courseService;
	
	@RequestMapping(value = "/user_data", method = RequestMethod.GET)
	public ModelAndView displayUserData( ) {
    	
    	ModelAndView mav = new ModelAndView("user_view");
    	try {
    	mav.addObject("userById", userService.getUserById(1));
    	}catch(NullPointerException e) {
    		
    	}
    	return mav;
		
    	
	}
	
	@RequestMapping( value = "/user_data",  method = RequestMethod.POST)
	public ModelAndView changeUserData(@ModelAttribute(value = "changeUser")  UserDTO user) {
		userService.updateUser(1, user);
		return new ModelAndView( "redirect:user_data");
	}
	
	@RequestMapping(value = "/student_data", method = RequestMethod.GET)
	public ModelAndView displayStudentData() {
		ModelAndView mav = new ModelAndView("student_view");
		try {
		mav.addObject("studentById",studentService.getStudentByUser(1));
		}catch(NullPointerException e) {
			
		}
		return mav;
		
	}
	@RequestMapping(value = "/student_data",  method = RequestMethod.POST)
	public ModelAndView changeStudentData(@ModelAttribute(value = "changeStudent") StudentDTO student) {
		
		studentService.updateStudent(studentService.getStudentByUser(1).getStudentId(), student);

		return new ModelAndView( "redirect:student_data");
		
	}
	
	@RequestMapping(value = "/enrollment", method = RequestMethod.GET)
	public ModelAndView displayEnrolledCourses(ModelMap model) {
		ModelAndView mav= new ModelAndView("enrollment_view");
		model.addAttribute("enrollmentOptions", studentService.getNotEnrolled(studentService.getStudentByUser(1).getStudentId()));
		mav.addObject("enrolledCourses",studentService.getCoursesForStudent(studentService.getStudentByUser(1).getStudentId()));
		return mav;
	}

	
	
	@RequestMapping(value="/enrollment/enrollmentOptions", method=RequestMethod.POST)
	public String enrollToCourse(@ModelAttribute ("enrollmentOptions") @RequestParam Integer courseId) throws UnknownHostException {
	
			studentService.enrollMe(studentService.getStudentByUser(1).getStudentId(), courseId);
			courseService.createCoursesWithStudentsReport(userService.getUserById(5).getTeacher());
		
			
		return "redirect:/enrollment";
	}
	
	
	@RequestMapping(value = "/grading", method=RequestMethod.GET)
	public ModelAndView showMyGrades( ) throws UnknownHostException {
		
		ModelAndView mav =  new ModelAndView("grade_view");
		mav.addObject("gradesShown",studentService.getStudentGrades(studentService.getStudentByUser(1).getStudentId()));

		studentService.createStudentsFailingReport(userService.getUserById(5).getTeacher());
		return mav;
	}
	
}
