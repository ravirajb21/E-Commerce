package dev.rbb.productservice.inheritanceexamples.tableperclass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TPCMentorRepository extends JpaRepository<dev.rbb.productservice.inheritanceexamples.mappedsuperclass.Mentor, Long> {
    dev.rbb.productservice.inheritanceexamples.mappedsuperclass.Mentor save(dev.rbb.productservice.inheritanceexamples.mappedsuperclass.Mentor mentor);

    Mentor findMentorById(Long id);
}