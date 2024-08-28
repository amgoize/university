package amgoize.university.controllers;

import amgoize.university.DAO.TeachersDAO;
import amgoize.university.model.Teacher;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teachers")
public class TeachersController {
    private final TeachersDAO teachersDAO;

    @Autowired
    public TeachersController(TeachersDAO teachersDAO) {
        this.teachersDAO = teachersDAO;
    }

    // Получить список всех учителей
    @GetMapping
    public String getAllTeachers(Model model) {
        List<Teacher> teachers = teachersDAO.showTeachers();
        model.addAttribute("teachers", teachers);
        return "teachers/listTeacher"; // Имя Thymeleaf шаблона
    }

    // Получить учителя по id
    @GetMapping("/{id}")
    public String getTeacherById(@PathVariable("id") int id, Model model) {
        Teacher teacher = teachersDAO.showTeacher(id);
        if (teacher != null) {
            model.addAttribute("teacher", teacher);
            return "teachers/oneTeacher"; // Имя Thymeleaf шаблона для деталей учителя
        } else {
            return "teachers/notFound"; // Имя Thymeleaf шаблона, если учитель не найден
        }
    }

    // Показать форму для создания учителя
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teachers/createTeacher"; // Имя Thymeleaf шаблона для создания учителя
    }

    // Создание учителя
    @PostMapping("/create")
    public String createTeacher(@Valid @ModelAttribute("teacher") Teacher teacher, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "teachers/createTeacher"; // Возврат к форме создания при наличии ошибок
        }
        teachersDAO.saveTeacher(teacher);
        return "redirect:/teachers"; // Перенаправление на список учителей
    }

    // Показать форму для обновления учителя
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Teacher teacher = teachersDAO.showTeacher(id);
        if (teacher != null) {
            model.addAttribute("teacher", teacher);
            return "teachers/updateTeacher"; // Имя Thymeleaf шаблона для обновления учителя
        } else {
            return "teachers/notFound"; // Имя Thymeleaf шаблона, если учитель не найден
        }
    }

    // Обновление учителя
    @PostMapping("/update/{id}")
    public String updateTeacher(@PathVariable("id") int id, @Valid @ModelAttribute("teacher") Teacher teacher, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "teachers/updateTeacher"; // Возврат к форме обновления при наличии ошибок
        }
        teacher.setId(id);
        teachersDAO.updateTeacher(teacher);
        return "redirect:/teachers"; // Перенаправление на список учителей
    }

    // Удалить учителя
    @GetMapping("/delete/{id}")
    public String deleteTeacher(@PathVariable("id") int id) {
        Teacher teacher = teachersDAO.showTeacher(id);
        if (teacher != null) {
            teachersDAO.deleteTeacher(id);
        }
        return "redirect:/teachers"; // Перенаправление на список учителей после удаления
    }
}
