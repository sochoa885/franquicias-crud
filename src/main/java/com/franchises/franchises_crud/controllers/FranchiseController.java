package com.franchises.franchises_crud.controllers;

import com.franchises.franchises_crud.modules.BranchModel;
import com.franchises.franchises_crud.modules.FranchiseModel;
import com.franchises.franchises_crud.services.FranchiseService;
import com.franchises.franchises_crud.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${apiPrefix}/franchises")
public class FranchiseController {
    @Autowired
    private final FranchiseService franchiseService;

    public FranchiseController(FranchiseService franchiseService) {
        this.franchiseService = franchiseService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<FranchiseModel>> createFranchise(@RequestBody FranchiseModel franchise) {
        try {
            ApiResponse<FranchiseModel> response = new ApiResponse<>(true, "Product created successfully", franchiseService.createFranchise(franchise).get());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException error) {
            ApiResponse<FranchiseModel> response = new ApiResponse<>(false, error.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("{franchiseId}/update-name")
    public ResponseEntity<ApiResponse<FranchiseModel>> updateFranchiseName(@PathVariable Long franchiseId, @RequestParam String name) {
        try {
            ApiResponse<FranchiseModel> response = new ApiResponse<>(true, "Product updated successfully", franchiseService.updateFranchiseName(franchiseId, name).get());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException error) {
            ApiResponse<FranchiseModel> response = new ApiResponse<>(false, error.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
