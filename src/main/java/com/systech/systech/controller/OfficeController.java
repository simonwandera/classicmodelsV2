package com.systech.systech.controller;

import com.systech.systech.Entity.Office;
import com.systech.systech.service.OfficeServiceI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offices")
@RequiredArgsConstructor
public class OfficeController {

    private final OfficeServiceI officeService;

    @PostMapping
    public ResponseEntity<Office> createOffice(@RequestBody Office office) {
        try {
            Office createdOffice = officeService.createOffice(office);
            return new ResponseEntity<>(createdOffice, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Office>> getAllOffices() {
        try {
            List<Office> offices = officeService.getAllOffices();
            return new ResponseEntity<>(offices, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Office> getOfficeById(@PathVariable Long id) {
        try {
            Office office = officeService.getOfficeById(id);
            return new ResponseEntity<>(office, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Office> updateOffice(@PathVariable Long id, @RequestBody Office office) {
        try {
            Office updatedOffice = officeService.updateOffice(id, office);
            return new ResponseEntity<>(updatedOffice, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteOffice(@PathVariable Long id) {
        try {
            officeService.deleteOffice(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return new ResponseEntity<>("Office service is running", HttpStatus.OK);
    }
}
