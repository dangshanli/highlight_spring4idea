package com.springdata.myjpa.repository;

import com.springdata.myjpa.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author luzj
 * @description:
 * @date 2018/7/10
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByAddress(String address);

    Person findByNameAndAddress(String name, String address);

    @Query("select p from Person p where p.name = :name and p.address  = :address")
    Person withNameAndAddressQuery(@Param("name") String name, @Param("address") String address);

    Person withNameAndAddress(String name,String address);

}
