package com.project.sms.controller;

import com.project.sms.entity.Student;
import com.project.sms.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {

    Logger log = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //List All Students
    @GetMapping("/students")
    public String listStudents(Model model){
        log.info("List Students");
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }

    //Add Student
    @PostMapping("/students")
    public String addStudent(@ModelAttribute("student") Student student) {
        log.info("Save Student Details");
        log.info("Student Name: "+student.getFirstName());
        log.info("Student DOB: "+student.getDob());
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/students/new")
    public String createStudentForm(Model model) {

        // create student object to hold student form data
        Student student = new Student();
        model.addAttribute("student", student);
        return "create_student";

    }

    @GetMapping("/student/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "edit_student";
    }

    @PostMapping("/student/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute("student") Student student, Model model){
        studentService.updateStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/student/{id}")
    public String deleteStudent(@PathVariable Long id){
        studentService.deleteStudentById(id);
        return "redirect:/students";
    }
}
