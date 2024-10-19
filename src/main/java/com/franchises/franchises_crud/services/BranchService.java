package com.franchises.franchises_crud.services;

import com.franchises.franchises_crud.modules.BranchModel;
import com.franchises.franchises_crud.modules.FranchiseModel;
import com.franchises.franchises_crud.modules.ProductModel;
import com.franchises.franchises_crud.repositories.IBranchRepository;
import com.franchises.franchises_crud.repositories.IFranchiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class BranchService {
    @Autowired
    private final IBranchRepository branchRepository;

    @Autowired
    private final IFranchiseRepository franchiseRepository;

    public BranchService(IBranchRepository branchRepository, IFranchiseRepository franchiseRepository) {
        this.branchRepository = branchRepository;
        this.franchiseRepository = franchiseRepository;
    }

    public Supplier<BranchModel> createBranch(Long franchiseId, BranchModel branch) {
        FranchiseModel franchise = franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> new RuntimeException("Franchise not found"));
        branch.setFranchise(franchise);
        return () -> branchRepository.save(branch);
    }

    public Supplier<BranchModel> updateBranchName(Long branchId, String name) {
        BranchModel branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new RuntimeException("Branch not found"));
        branch.setName(name);
        return () -> branchRepository.save(branch);
    }
}
