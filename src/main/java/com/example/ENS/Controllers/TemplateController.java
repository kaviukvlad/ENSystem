package com.example.ENS.Controllers;

import com.example.ENS.Models.Template;
import com.example.ENS.Models.User;
import com.example.ENS.Services.TemplateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/templates")
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @GetMapping
    public String listTemplates(Model model) {
        model.addAttribute("templates", templateService.getAllTemplates());
        return "templates";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("template", new Template());
        return "template-form";
    }

    @PostMapping("/new")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String createTemplate(@Valid @ModelAttribute Template template) {
        templateService.createTemplate(template);
        return "redirect:/templates";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Template template = templateService.getTemplateById(id);
        if (template != null) {
            model.addAttribute("template", template);
            return "template-form";
        } else {
            return "redirect:/templates";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateTemplate(@PathVariable Long id, @Valid @ModelAttribute Template updatedTemplate) {
        templateService.updateTemplate(id, updatedTemplate);
        return "redirect:/templates";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteTemplate(@PathVariable Long id) {
        templateService.deleteTemplate(id);
        return "redirect:/templates";
    }

    @GetMapping("/search")
    public String searchTemplates(@RequestParam("query") String query, Model model) {
        model.addAttribute("templates", templateService.searchTemplates(query));
        return "templates";
    }

    @GetMapping("/sort")
    public String sortTemplates(Model model) {
        model.addAttribute("templates", templateService.getAllTemplatesSortedByUpdatedAt());
        return "templates";
    }

}
