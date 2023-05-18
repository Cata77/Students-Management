package com.gestiune.app.controller;

import com.gestiune.app.model.Student;
import com.gestiune.app.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Controller
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }
    @GetMapping("/students")
    public String showStudents(Model model) {
        List<Student> students = service.getStudentList();
        model.addAttribute("students", students);
        return "students.html";
    }

    @GetMapping("/addStudent")
    public String addStudent(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "addStudentForm.html";
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute Student student) {
        service.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/update")
    public String showUpdateForm(@RequestParam Long studentId, Model model) {
        Student student = service.findStudentById(studentId).get();
        model.addAttribute("student", student);
        return "addStudentForm.html";
    }

    @GetMapping("/delete")
    public String showUpdateForm(@RequestParam Long studentId) {
        service.deleteStudent(studentId);
        return "redirect:/students";
    }

    @GetMapping("/calculate_average")
    public String calculateAverageGrade(Model model) {
        BigDecimal averageGrade = service.calculateAverageGrade().setScale(2, RoundingMode.HALF_UP);
        model.addAttribute("averageGrade",averageGrade);
        return "averageGrade.html";
    }

    @GetMapping("/most_popular_profile")
    public String findMostPopularSubject(Model model) {
        String mostPopularProfile = service.getMostPopularProfile();
        String[] parts = mostPopularProfile.split(",");
        String profile = parts[0].trim();
        String nrOfStudents = parts[1].trim();
        model.addAttribute("mostPopularSubject",profile);
        model.addAttribute("nrOfStudents",nrOfStudents);
        return "popularProfile.html";
    }
}
