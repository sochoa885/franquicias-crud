package com.franchises.franchises_crud.controllers;

import com.franchises.franchises_crud.modules.BranchModel;
import com.franchises.franchises_crud.services.BranchService;
import com.franchises.franchises_crud.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${apiPrefix}/branches")
public class BranchController {
    @Autowired
    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping("/create/{franchiseId}")
    public ResponseEntity<ApiResponse<BranchModel>> createBranch(@PathVariable Long franchiseId, @RequestBody BranchModel branch) {
        try {
            ApiResponse<BranchModel> response = new ApiResponse<>(true, "Branch created successfully", branchService.createBranch(franchiseId, branch).get());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException error) {
            ApiResponse<BranchModel> response = new ApiResponse<>(false, error.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("{branchId}/update-name")
    public ResponseEntity<ApiResponse<BranchModel>> updateBranchName(@PathVariable Long branchId, @RequestParam String name) {
        try {
            ApiResponse<BranchModel> response = new ApiResponse<>(true, "Branch updated successfully", branchService.updateBranchName(branchId, name).get());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException error) {
            ApiResponse<BranchModel> response = new ApiResponse<>(false, error.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
