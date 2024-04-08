package com.thevarungupta.springboottestingdemo.repository;

import com.thevarungupta.springboottestingdemo.entity.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

//    private Employee employee;
//
//    @BeforeEach
//    public void setup() {
//        employee = Employee.builder()
//                .firstName("John")
//                .lastName("Watson")
//                .email("j@gmail.com")
//                .build();
//    }

//    @Test
//    public void given_when_then(){
    // given - precondition or setup

    // when - action or the behaviour that we are going test

    // then - verify the output
//    }

    // Junit test for save employee operation
    @DisplayName("Junit test for save employee operation")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {
        // given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("John")
                .lastName("Watson")
                .email("j@gmail.com")
                .build();

        // when - action or the behaviour that we are going test
        Employee savedEmployee = employeeRepository.save(employee);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

    @Test
    public void givenEmployeeList_whenFindAll_thenEmployeeList() {
        // given
        Employee employee1 = Employee.builder()
                .firstName("John")
                .lastName("Watson")
                .email("j@gmail.com")
                .build();
        Employee employee2 = Employee.builder()
                .firstName("Mark")
                .lastName("Smith")
                .email("m@gmail.com")
                .build();
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);

        // when
        List<Employee> employeeList = employeeRepository.findAll();

        // then
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }

    @Test
    public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject() {
        // given
        Employee employee = Employee.builder()
                .firstName("John")
                .lastName("Watson")
                .email("j@gmail.com")
                .build();
        employeeRepository.save(employee);

        // when
        Employee employeeDb = employeeRepository.findById(employee.getId()).get();

        // verify
        assertThat(employeeDb).isNotNull();
    }

    @Test
    public void givenEmployeeObject_whenFindByEmail_thenReturnEmployeeObject() {
        // given
        Employee employee = Employee.builder()
                .firstName("John")
                .lastName("Watson")
                .email("j@gmail.com")
                .build();
        employeeRepository.save(employee);

        // when
        Employee employeeDb = employeeRepository.findByEmail(employee.getEmail()).get();

        // verify
        assertThat(employeeDb).isNotNull();
    }

    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnEmployeeObject() {
        // given
        Employee employee = Employee.builder()
                .firstName("John")
                .lastName("Watson")
                .email("j@gmail.com")
                .build();
        employeeRepository.save(employee);

        // when
        Employee savedEmployee = employeeRepository.findByEmail(employee.getEmail()).get();
        savedEmployee.setFirstName("John2");
        savedEmployee.setLastName("Watson2");
        savedEmployee.setEmail("j2@gmail.com");
        Employee updatedEmployee = employeeRepository.save(savedEmployee);

        // verify
        assertThat(updatedEmployee.getFirstName()).isEqualTo("John2");
        assertThat(updatedEmployee.getLastName()).isEqualTo("Watson2");
        assertThat(updatedEmployee.getEmail()).isEqualTo("j2@gmail.com");
    }

    @Test
    public void givenEmployeeObject_whenDeleteEmployee_thenRemoveEmployeeObject() {
        // given
        Employee employee = Employee.builder()
                .firstName("John")
                .lastName("Watson")
                .email("j@gmail.com")
                .build();
        employeeRepository.save(employee);

        // when
        employeeRepository.delete(employee);
        Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());

        // verify
        assertThat(employeeOptional).isEmpty();
    }

    @Test
    public void givenFirstNameAndLastName_whenFindByJPQL_thenReturnEmployeeObject() {
        // given
        Employee employee = Employee.builder()
                .firstName("John")
                .lastName("Watson")
                .email("j@gmail.com")
                .build();
        employeeRepository.save(employee);
        String firstName = "John";
        String lastName = "Watson";

        // when
        Employee savedEmployee = employeeRepository.findByFirstNameAndLastName(firstName, lastName);

        // verify
        assertThat(savedEmployee).isNotNull();
    }
}