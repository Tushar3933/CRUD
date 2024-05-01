package com.crud.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.api.entity.Employee;

public interface EmpRepository extends JpaRepository<Employee, Integer> {

}