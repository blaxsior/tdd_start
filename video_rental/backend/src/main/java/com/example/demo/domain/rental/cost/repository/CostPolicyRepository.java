package com.example.demo.domain.rental.cost.repository;

import com.example.demo.domain.rental.cost.entity.CostPolicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CostPolicyRepository extends JpaRepository<CostPolicy, Long> {

}
