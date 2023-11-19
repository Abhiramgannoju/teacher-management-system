package teacherpresidio.teacher.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import teacherpresidio.teacher.model.Teacher;

public interface teacherrepo extends JpaRepository<Teacher,Integer> {

	List<Teacher> findByFullNameContainingIgnoreCase(String name);

}
