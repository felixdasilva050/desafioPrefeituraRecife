package impl;

import exception.BusinessException;
import exception.NoContentExcepetion;
import model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import repository.PersonRepository;
import service.PersonService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personDAO;

    @Override
    public List<Person> findAll() {
        return personDAO.findAll();
    }

    @Override
    public void create(Person person) {
        if(Objects.isNull(person.getId())){
            personDAO.save(person);
        }else{
            Person personDB = findById(person.getId());
            if (Objects.equals(person.getId(), personDB.getId())){
                throw new BusinessException("Há um Pessoa com o mesmo ID");
            }
        }
    }

    @Override
    public Person findById(Long id) {
        Optional<Person> person = personDAO.findById(id);
        return person.orElseThrow(() -> new NoContentExcepetion("Não foi possível localizar o ID informado"));
    }

    @Override
    public void updatePerson(Person person) {
        final Person PERSON_DB= findById(person.getId());
        if(PERSON_DB.getId().equals(person.getId())){
            personDAO.save(person);
        }else{
            throw new BusinessException("Os ID são diferentes");
        }
    }

    @Override
    public void delete(Person person) {
        if(personDAO.existsById(person.getId())){
            personDAO.delete(person);
        } else {
            throw new BusinessException("Não foi possível localizar a Pessoa");
        }
    }
}
