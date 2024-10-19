package com.franchises.franchises_crud.repositories;

import com.franchises.franchises_crud.modules.FranchiseModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFranchiseRepository extends JpaRepository<FranchiseModel, Long> {
}
