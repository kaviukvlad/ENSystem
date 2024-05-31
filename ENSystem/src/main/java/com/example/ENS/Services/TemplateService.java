package com.example.ENS.Services;

import com.example.ENS.Models.Template;
import com.example.ENS.Repositories.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateService {

    @Autowired
    private TemplateRepository templateRepository;

    public List<Template> getAllTemplates() {
        return templateRepository.findAll();
    }

    public Template getTemplateById(Long id) {
        return templateRepository.findById(id).orElse(null);
    }

    public Template createTemplate(Template template) {
        return templateRepository.save(template);
    }

    public Template updateTemplate(Long id, Template updateTemplate) {
        Template template = templateRepository.findById(id).orElse(null);
        if (template != null) {
            template.setName(updateTemplate.getName());
            template.setContent(updateTemplate.getContent());
            return templateRepository.save(template);
        }
        return null;
    }

    public void deleteTemplate(Long id) {
        templateRepository.deleteById(id);
    }

    public List<Template> searchTemplates(String name) {
        return templateRepository.findByNameContaining(name);
    }

    public List<Template> getAllTemplatesSortedByUpdatedAt() {
        return templateRepository.findAllOrderByUpdatedAtDesc();
    }

}
