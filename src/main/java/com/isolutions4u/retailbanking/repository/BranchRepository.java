package com.isolutions4u.retailbanking.repository;

import com.isolutions4u.retailbanking.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, String> {

    Branch findByName(String name);

}
