package dev.rbb.productservice;

import dev.rbb.productservice.inheritanceexamples.joinedtable.Mentor;
import dev.rbb.productservice.inheritanceexamples.joinedtable.User;
import dev.rbb.productservice.inheritanceexamples.joinedtable.JTMentorRepository;
import dev.rbb.productservice.inheritanceexamples.joinedtable.JTUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServiceApplicationTests {

    @Test
    void contextLoads() {
    }
    @Autowired
    private JTMentorRepository jtMentorRepository;
    @Autowired
    private  JTUserRepository jtUserRepository;

//    @Test
//    void testDifferentInheritance() {
//        User user = new User();
//
//        user.setEmail("raviraj@gmail.com");
//        user.setPassword("password");
//        jtUserRepository.save(user);
//
//        Mentor mentor = new Mentor();
//
//        mentor.setEmail("rbb@gmail.com");
//        mentor.setPassword("psswrd");
//        mentor.setGetNumberOfMentees(4);
//        mentor.setNumberOfSessions(40);
//
//        jtMentorRepository.save(mentor);
//    }

}
