package com.thevarungupta.springboottestingdemo.repository;

import com.thevarungupta.springboottestingdemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // custom method using convention
    Optional<Employee> findByEmail(String email);

    // custom method using custom query with index params
    @Query("select e from Employee e where e.firstName = ?1 and e.lastName = ?2")
    Employee findByFirstNameAndLastName(String firstName, String lastName);

    // custom method using custom query with named params
    @Query("select e from Employee e where e.firstName = :firstName and e.lastName = :lastName")
    Employee findByFirstNameAndLastNameWithNamedParams(@Param("firstName") String firstName,
                                                       @Param("lastName") String lastName);

    // custom method using custom native query
    @Query(value = "select * from employee e where e.first_name = ?1 and e.last_name = ?2",
            nativeQuery = true)
    Employee findByFirstNameAndLastNameWithNativeQuery(String firstName, String lastName);

    // custom method using custom native query and named params
    @Query(value = "select * from employee e where e.first_name = :firstName and e.last_name = :lastName",
            nativeQuery = true)
    Employee findByFirstNameAndLastNameWithNativeQueryAndNamedParams(@Param("firstName") String firstName,
                                                                     @Param("lastName") String lastName);
}
