package com.example.ENS.Repositories;

import com.example.ENS.Models.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TemplateRepository extends JpaRepository<Template, Long> {
    List<Template> findByNameContaining(String name);

    @Query("SELECT t FROM Template t ORDER BY t.updatedAt DESC")
    List<Template> findAllOrderByUpdatedAtDesc();
}
