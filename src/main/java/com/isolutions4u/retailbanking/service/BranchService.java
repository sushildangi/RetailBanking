package com.isolutions4u.retailbanking.service;

import com.isolutions4u.retailbanking.model.Branch;

import java.util.List;

public interface BranchService {
    Branch findBranchByName(String name);

    Branch findBranchByBranchNo(String branchNo);
    void saveBranch(Branch branch);

    List<Branch> findAllBranches();
}
