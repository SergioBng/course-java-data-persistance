package com.rakovets.course.datapersistence.example;

import com.rakovets.course.datapersistence.example.dal.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class EmployeeTest {
    private static SessionFactory SESSION_FACTORY;

    @BeforeAll
    static void init() {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
    }

    @Test
    void testSaveEmployee() {
        Session session = SESSION_FACTORY.openSession();

        Employee employee = new Employee();
        employee.setName("Dmitry Rakovets");
        Long id = (Long) session.save(employee);

        Employee savedEmployee = session.find(Employee.class, id);
        Assertions.assertEquals(savedEmployee.getName(), "Dmitry Rakovets");

        session.close();
    }

    @AfterAll
    static void finish() {
        SESSION_FACTORY.close();
    }
}
