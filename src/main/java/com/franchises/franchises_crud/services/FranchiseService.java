package com.franchises.franchises_crud.services;

import com.franchises.franchises_crud.modules.BranchModel;
import com.franchises.franchises_crud.modules.FranchiseModel;
import com.franchises.franchises_crud.repositories.IBranchRepository;
import com.franchises.franchises_crud.repositories.IFranchiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class FranchiseService {
    @Autowired
    private final IFranchiseRepository franchiseRepository;

    public FranchiseService(IFranchiseRepository franchiseRepository) {
        this.franchiseRepository = franchiseRepository;
    }

    public Supplier<FranchiseModel> createFranchise(FranchiseModel franchise) {
        return () -> franchiseRepository.save(franchise);
    }

    public Supplier<FranchiseModel> updateFranchiseName(Long franchiseId, String name) {
        FranchiseModel franchise = franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> new RuntimeException("Franchise not found"));
        franchise.setName(name);
        return () -> franchiseRepository.save(franchise);
    }
}
