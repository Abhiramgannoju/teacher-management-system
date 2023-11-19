package teacherpresidio.teacher.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import teacherpresidio.teacher.model.Teacher;
import teacherpresidio.teacher.repo.teacherrepo;

@Service
public class teacherservice {
	@Autowired
	teacherrepo repo;
	public void addteacher(Teacher teach)
	{
		repo.save(teach);
	}
	public List<Teacher> getAllteacher()
	{
		return repo.findAll();
	}
	public void deleteTeacherbyId(int id) {
		repo.deleteById(id);
		
	}
	public Optional<Teacher> getteacherById(int id) {
		return repo.findById(id);
	}
	public void updateTeacher(int id, Teacher updatedTeacher) {
		 Optional<Teacher> existingTeacherOptional = repo.findById(id);

	        if (existingTeacherOptional.isPresent()) {
	            Teacher existingTeacher = existingTeacherOptional.get();
	            
	            // Update the fields of the existing teacher with the updated values
	            existingTeacher.setFullName(updatedTeacher.getFullName());
	            existingTeacher.setAge(updatedTeacher.getAge());
	            existingTeacher.setDateOfBirth(updatedTeacher.getDateOfBirth());
	            existingTeacher.setNumberOfClasses(updatedTeacher.getNumberOfClasses());
	            existingTeacher.setGender(updatedTeacher.getGender());
	            existingTeacher.setSubject(updatedTeacher.getSubject());

	            // Save the updated teacher
	            repo.save(existingTeacher);
		
	}

}
	public List<Teacher> findTeachersByName(String name) {
		return repo.findByFullNameContainingIgnoreCase(name);
	}
	public ArrayList<Teacher> getAllteachers()
	{
		return (ArrayList<Teacher>) repo.findAll();
	}
}
