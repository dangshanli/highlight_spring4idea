package com.springdata.myjpa.controller;

import com.springdata.myjpa.domain.Person;
import com.springdata.myjpa.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author luzj
 * @description:
 * @date 2018/7/10
 */
@RestController
public class DataController {
    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/save")
    public Person save(String name, Integer age, String address) {
        Person p = personRepository.save(new Person(null, name, age, address));
        return p;
    }

    @RequestMapping("/q1")
    public List<Person> q1(String address) {
        List<Person> people = personRepository.findByAddress(address);
        return people;
    }

    @RequestMapping("/q2")
    public Person q2(String name, String address) {
        Person person = personRepository.findByNameAndAddress(name, address);
        return person;
    }

    @RequestMapping("/q3")
    public Person q3(String name,String address){
        Person p = personRepository.withNameAndAddressQuery(name,address);
        return p;
    }

    @RequestMapping("/q4")
    public Person q4(String name,String address){
        Person p = personRepository.withNameAndAddress(name,address);
        return p;
    }

    @RequestMapping("/sort")
    public List<Person> sort(){
        List<Person> people = personRepository.findAll(new Sort(Sort.Direction.ASC,"age"));
        return people;
    }

    @RequestMapping("/page")
    public Page<Person> page(){
        Page<Person> personPage = personRepository.findAll(new PageRequest(1,2));
        return personPage;
    }
}
