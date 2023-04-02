package com.cuspay.repo;

import com.cuspay.model.Fines;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinesRepo extends JpaRepository<Fines, Long> {
}
