package com.kosign.demo_jpa.repository;

import com.kosign.demo_jpa.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color, Integer> {

}
