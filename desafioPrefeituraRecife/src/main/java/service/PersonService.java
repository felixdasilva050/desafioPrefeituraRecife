package service;

import model.Person;

import java.util.List;

public interface PersonService {

    List<Person> findAll();
    void create(Person person);
    Person findById(Long id);
    void updatePerson(Person person);
    void delete(Person person);
}
