package amgoize.university.controllers;

import amgoize.university.DAO.GroupDAO;
import amgoize.university.model.Group;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/groups")
public class GroupsController {
    private final GroupDAO groupDAO;

    @Autowired
    public GroupsController(GroupDAO groupDAO) {
        this.groupDAO = groupDAO;
    }

    // Получение всех групп
    @GetMapping
    public String getAllGroups(Model model){
        List<Group> groups = groupDAO.getAllGroups();
        model.addAttribute("groups", groups);
        return "groups/listGroup"; // Предполагается, что у вас есть шаблон list.html в папке groups
    }

    // Получение группы по id
    @GetMapping("/{id}")
    public String getGroup(@PathVariable("id") int id, Model model) {
        Group group = groupDAO.getGroup(id);
        if (group != null) {
            model.addAttribute("group", group);
            return "groups/oneGroup"; // Предполагается, что у вас есть шаблон detail.html в папке groups
        } else {
            return "groups/notFound"; // Предполагается, что у вас есть шаблон notFound.html в папке groups
        }
    }

    // Переход к форме создания новой группы
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("group", new Group());
        return "groups/createGroup"; // Предполагается, что у вас есть шаблон create.html в папке groups
    }

    // Создание новой группы
    @PostMapping("/create")
    public String createGroup(@Valid @ModelAttribute("group") Group group, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "groups/createGroup"; // Возвращаем форму, если есть ошибки валидации
        }
        groupDAO.createGroup(group);
        return "redirect:/groups"; // Перенаправление на список групп после успешного создания
    }

    // Переход к форме обновления группы
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Group group = groupDAO.getGroup(id);
        if (group != null) {
            model.addAttribute("group", group);
            return "groups/updateGroup"; // Предполагается, что у вас есть шаблон update.html в папке groups
        } else {
            return "groups/notFound"; // Предполагается, что у вас есть шаблон notFound.html в папке groups
        }
    }

    // Обновление группы
    @PostMapping("/update/{id}")
    public String updateGroup(@PathVariable("id") int id, @Valid @ModelAttribute("group") Group group, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "groups/updateGroup"; // Возвращаем форму, если есть ошибки валидации
        }
        group.setId(id);
        groupDAO.updateGroup(group);
        return "redirect:/groups"; // Перенаправление на список групп после успешного обновления
    }

    // Удаление группы
    @GetMapping("/delete/{id}")
    public String deleteGroup(@PathVariable("id") int id) {
        groupDAO.deleteGroup(id);
        return "redirect:/groups"; // Перенаправление на список групп после успешного удаления
    }
}
