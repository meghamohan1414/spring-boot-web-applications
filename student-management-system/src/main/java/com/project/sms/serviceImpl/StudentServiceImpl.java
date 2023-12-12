package com.project.sms.serviceImpl;

import com.project.sms.entity.Student;
import com.project.sms.repository.StudentRepository;
import com.project.sms.service.StudentService;
import com.project.sms.utilities.UtilityFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepo;

    public StudentServiceImpl(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        Student studentDetails = new Student();
        studentRepo.findById(id).ifPresent(existingStudentDetails -> {
            studentDetails.setId(existingStudentDetails.getId());
            studentDetails.setFirstName(existingStudentDetails.getFirstName());
            studentDetails.setLastName(existingStudentDetails.getLastName());
            studentDetails.setDob(existingStudentDetails.getDob());
            studentDetails.setGender(existingStudentDetails.getGender());
            studentDetails.setAge(existingStudentDetails.getAge());
            studentDetails.setEmail(existingStudentDetails.getEmail());
        });
        return studentDetails;
    }

    @Override
    public Student updateStudent(Student student) {
        if (student != null) {
            student.setAge(UtilityFunctions.calculateAge(student.getDob()));
            return studentRepo.save(student);
        }
        return null;
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepo.deleteById(id);
    }

    @Override
    public Student saveStudent(Student student) {
        if (student != null && student.getDob() != null)
            student.setAge(UtilityFunctions.calculateAge(student.getDob()));
        return studentRepo.save(student);
    }
}
