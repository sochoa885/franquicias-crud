package com.franchises.franchises_crud.repositories;

import com.franchises.franchises_crud.modules.BranchModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBranchRepository extends JpaRepository<BranchModel, Long> {
}
