package com.demos.ILService.repository;

import com.demos.ILService.model.Il;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IlRepository extends JpaRepository<Il, Integer> {
    List<Il> findByName(String name);
}
