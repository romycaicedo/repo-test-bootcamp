package demoStorage.repositories;

import demoStorage.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentRepository extends JpaRepository<Student, String>{
}
