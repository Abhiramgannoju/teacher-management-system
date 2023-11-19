package teacherpresidio.teacher.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.qos.logback.core.model.Model;
import teacherpresidio.teacher.model.Teacher;
import teacherpresidio.teacher.service.teacherservice;

@Controller
public class HomeController {
    private final teacherservice service;

    @Autowired
    public HomeController(teacherservice service) {
        this.service = service;
    }

    @GetMapping("/addteachers")
    public String getaddTeachers(org.springframework.ui.Model model) {
        model.addAttribute("teacher", new Teacher()); // Add an instance of Teacher to the model
        return "addteachers"; // Assuming "addteachers" is the associated Thymeleaf/HTML template
    }
    @PostMapping("/addteachers")
    public String postaddTeachers(@ModelAttribute("teacher") Teacher teacher) {
    	service.addteacher(teacher);
        return "redirect:/allteachers"; // Assuming "addteachers" is the associated Thymeleaf/HTML template
    }


    @GetMapping("/")
    public String home()
    {
    	return "index";
    }
	@GetMapping("/allteachers")
	public String allteachers(org.springframework.ui.Model model)
	{
		
        model.addAttribute("teachers", service.getAllteacher());
		return "allteachers";
	}
	@GetMapping("/delete")
	public String allteachersdelete(org.springframework.ui.Model model)
	{
		
        model.addAttribute("teachers", service.getAllteacher());
		return "delete";
	}
	@PostMapping("/delete/{id}")
	public String deleteTeacher(@PathVariable("id") int id) {
	    service.deleteTeacherbyId(id);
	    return "redirect:/delete";
	}
	@GetMapping("/update")
	public String allteachersupdate(org.springframework.ui.Model model)
	{
		
        model.addAttribute("teachers", service.getAllteacher());
		return "update";
	}
	@GetMapping("/update/{id}")
	public String getUpdateTeacher(@PathVariable("id") int id, org.springframework.ui.Model model) {
	    Optional<Teacher> optionalTeacher = service.getteacherById(id);
	    
	    if (optionalTeacher.isPresent()) {
	        Teacher existingTeacher = optionalTeacher.get();
	        model.addAttribute("teacher", existingTeacher);
	        return "updation"; // Assuming "addteachers" is your update form
	    } else {
	        // Handle the case where teacher with given ID is not found
	        return "error";
	    }
	}
	@PostMapping("/update/{id}")
	public String postUpdateTeacher(@PathVariable("id") int id, @ModelAttribute("teacher") Teacher updatedTeacher) {
	    // Your code to update the teacher using the service
	    service.updateTeacher(id, updatedTeacher);
	    return "redirect:/allteachers"; // Redirect to the page showing all teachers
	}
	@GetMapping("/search")
	public String allteacherssearch(org.springframework.ui.Model model)
	{
		
        model.addAttribute("teachers", service.getAllteacher());
		return "search";
	}
    
	@PostMapping("/aftersearch")
    public String searchTeachersByName(@RequestParam("search") String name, org.springframework.ui.Model model) {
        

        model.addAttribute("foundTeachers", service.findTeachersByName(name));

        return "aftersearch"; 
    }
	@GetMapping("/filter")
	public String allteachersfilter(org.springframework.ui.Model model)
	{
		
		return "filter";
	}
	@GetMapping("/filteredteachers")
	public String filterTeachers(@RequestParam("filterBy") String filterBy, org.springframework.ui.Model model) {
	    ArrayList<Teacher> teachers = service.getAllteachers(); // Retrieve all teachers

	    // Perform filtering based on the selected option
	    switch (filterBy) {
	        case "ageAsc":
	            teachers = (ArrayList<Teacher>) teachers.stream()
	                    .sorted(Comparator.comparingInt(Teacher::getAge))
	                    .collect(Collectors.toList());
	            break;
	        case "ageDesc":
	            teachers = (ArrayList<Teacher>) teachers.stream()
	                    .sorted(Comparator.comparingInt(Teacher::getAge).reversed())
	                    .collect(Collectors.toList());
	            break;
	        case "classesAsc":
	            teachers = (ArrayList<Teacher>) teachers.stream()
	                    .sorted(Comparator.comparingInt(Teacher::getNumberOfClasses))
	                    .collect(Collectors.toList());
	            break;
	        case "classesDesc":
	            teachers = (ArrayList<Teacher>) teachers.stream()
	                    .sorted(Comparator.comparingInt(Teacher::getNumberOfClasses).reversed())
	                    .collect(Collectors.toList());
	            break;
	        default:
	            // Handle default case or return an error page
	            break;
	    }

	    model.addAttribute("teachers", teachers); // Add filtered teachers to the model
	    return "filteredteachers"; // Assuming "filteredTeachers" is the Thymeleaf template to display filtered teachers
	}

	

}



