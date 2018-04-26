package assignment2.sd.TUCN_app_2.controller;

import java.net.UnknownHostException;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import assignment2.sd.TUCN_app_2.business.CourseService;
import assignment2.sd.TUCN_app_2.business.GradeService;
import assignment2.sd.TUCN_app_2.business.StudentDTO;
import assignment2.sd.TUCN_app_2.business.StudentService;
import assignment2.sd.TUCN_app_2.business.TeacherService;
import assignment2.sd.TUCN_app_2.business.UserDTO;
import assignment2.sd.TUCN_app_2.business.UserService;
import assignment2.sd.TUCN_app_2.persistence.entities.Course;
import assignment2.sd.TUCN_app_2.persistence.entities.Student;
import assignment2.sd.TUCN_app_2.persistence.entities.User;
import assignment2.sd.TUCN_app_2.persistence.respositories.CourseRepository;


@Controller
//@RequestMapping("teacher")
public class TeacherController {
	
	@Inject 
	UserService userService;

	@Inject 
	TeacherService teacherService;
	
	@Inject
	CourseService courseService;
	
	@Inject
	GradeService gradeService;
	
	@Inject 
	StudentService studentService;
	
	
	private Integer courseID;
	

	@RequestMapping(value = "/teacher_data", method = RequestMethod.GET)
	public ModelAndView displayUserData( ) {
    	
    	ModelAndView mav = new ModelAndView("teacher_view");
    	try {
    	mav.addObject("teacherById", userService.getUserById(5));
    	}catch(NullPointerException e) {
    		
    	}
    	return mav;
		
    	
	}
	
	@RequestMapping( value = "/teacher_data",  method = RequestMethod.POST)
	public ModelAndView changeUserData(@ModelAttribute(value = "changeTeacher")  UserDTO user) {
		userService.updateUser(5, user);
		return new ModelAndView( "redirect:teacher_data");
	}
	
	
	@RequestMapping(value = "/gen_report/courses", method = RequestMethod.GET)
	public ModelAndView showMyCourses(ModelMap model) throws UnknownHostException {
		
		ModelAndView mav = new ModelAndView("course_view");
		try {
		model.addAttribute("myCourses",courseService.getCoursesByTeacherId(userService.getUserById(5).getTeacher().getTeacherId()));
		teacherService.createTeacherCoursesReport(userService.getUserById(5).getTeacher());
		}catch(NullPointerException e) {
			
		}
		return mav;
	}
	
	@RequestMapping(value = "/gen_report/specific_course", method =  RequestMethod.GET)
	public ModelAndView showStudentsAtACourse(@RequestParam Integer courseId) {
		ModelAndView mav= new ModelAndView("report_view");
		mav.addObject("notGraded",studentService.getNotGraded(courseId));
		courseID = courseId;
		return mav;
	}
	
	@RequestMapping(value = "/grade_student/{id}", method = RequestMethod.POST)
	public ModelAndView giveAGrade(@PathVariable ("id") Integer studentId, @RequestParam Integer mark) {
		gradeService.gradeStudent(studentService.getStudentById(studentId), courseService.getCourseById(courseID),mark);
		
		return new ModelAndView("redirect:/gen_report/specific_course?courseId="+courseID);
	}
	
	@RequestMapping(value = "/gen_report/courses", method = RequestMethod.POST)
	public String addNewCourse(@ModelAttribute ("addNewCourse") Course course) {
		courseService.addCourse(course);
		return "redirect:/gen_report/courses";
	}
	
	@RequestMapping(value = "/list_students", method = RequestMethod.GET)
	public ModelAndView showAllStudents() {
		ModelAndView mav= new ModelAndView("all_students_view");
		mav.addObject("allStudents",studentService.getAllStudents());
		return mav;
	}
	
	@RequestMapping(value = "/list_students", method = RequestMethod.POST)
	public ModelAndView addStudent(@ModelAttribute(value = "addStudent") Student student) {
		User user =  new User();
		userService.addNewUser(user);
		studentService.addNewStudent(student,user);
		
		return new ModelAndView ("redirect:/list_students");
	}
	
	@RequestMapping (value = "/list_student",method = RequestMethod.DELETE)
	public ModelAndView deleteStudent(@ModelAttribute(value = "deleteStudent") String studentIdentifier) {
		Student stud = studentService.deleteStudent(studentIdentifier);
		userService.deleteUser(userService.getUserById(stud.getUser().getUserId()));
		return  new ModelAndView("redirect:/list_student");
	}
	
}
