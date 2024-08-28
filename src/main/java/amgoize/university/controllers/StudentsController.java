package amgoize.university.controllers;

import amgoize.university.DAO.StudentDAO;
import amgoize.university.model.Student;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentsController {
    private final StudentDAO studentDAO;

    @Autowired
    public StudentsController(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @GetMapping
    public String getAllStudents(Model model) {
        List<Student> students = studentDAO.showStudents();
        model.addAttribute("students", students);
        return "students/listStudent"; // Имя шаблона Thymeleaf для отображения списка студентов
    }

    @GetMapping("/{id}")
    public String getStudent(@PathVariable("id") int id, Model model) {
        Student student = studentDAO.showStudent(id);
        if (student != null) {
            model.addAttribute("student", student);
            return "students/oneStudent"; // Имя шаблона Thymeleaf для отображения деталей студента
        } else {
            return "students/notFound"; // Имя шаблона Thymeleaf для отображения страницы не найдено
        }
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("student", new Student());
        return "students/createStudent"; // Имя шаблона Thymeleaf для формы создания студента
    }

    @PostMapping("/create")
    public String createStudent(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "students/createStudent"; // Возвращаем форму, если есть ошибки валидации
        }
        studentDAO.saveStudent(student);
        return "redirect:/students"; // Перенаправление на список студентов после успешного создания
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Student student = studentDAO.showStudent(id);
        if (student != null) {
            model.addAttribute("student", student);
            return "students/updateStudent"; // Имя шаблона Thymeleaf для формы обновления студента
        } else {
            return "students/notFound"; // Имя шаблона Thymeleaf для отображения страницы не найдено
        }
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable("id") int id, @Valid @ModelAttribute("student") Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "students/updateStudent"; // Возвращаем форму, если есть ошибки валидации
        }
        student.setId(id);
        studentDAO.updateStudent(student);
        return "redirect:/students"; // Перенаправление на список студентов после успешного обновления
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") int id) {
        Student student = studentDAO.showStudent(id);
        if (student != null) {
            studentDAO.deleteStudent(id);
            return "redirect:/students"; // Перенаправление на список студентов после успешного удаления
        } else {
            return "students/notFound"; // Имя шаблона Thymeleaf для отображения страницы не найдено
        }
    }
}
