package dev.rbb.productservice.inheritanceexamples.singleclass;

import dev.rbb.productservice.inheritanceexamples.mappedsuperclass.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SCMentorRepository extends JpaRepository<dev.rbb.productservice.inheritanceexamples.mappedsuperclass.Mentor, Long> {
    dev.rbb.productservice.inheritanceexamples.mappedsuperclass.Mentor save(dev.rbb.productservice.inheritanceexamples.mappedsuperclass.Mentor mentor);

    Mentor findMentorById(Long id);
}
