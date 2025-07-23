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
@CrossOrigin(origins = "*")
public class OfficeController {

    private final OfficeServiceI officeService;

    /*
     Create a new office
     POST /api/offices
     */
    @PostMapping
    public ResponseEntity<Office> createOffice(@RequestBody Office office) {
        try {
            Office createdOffice = officeService.createOffice(office);
            return new ResponseEntity<>(createdOffice, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     Get all offices
     GET /api/offices
     */
    @GetMapping
    public ResponseEntity<List<Office>> getAllOffices() {
        try {
            List<Office> offices = officeService.getAllOffices();
            if (offices.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(offices, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     Get office by ID
     GET /api/offices/{id}
     */
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

    /*
     Update office by ID
     PUT /api/offices/{id}
     */
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

    /*
     Delete office by ID
     DELETE /api/offices/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteOffice(@PathVariable Long id) {
        try {
            officeService.deleteOffice(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     Health check endpoint
     GET /api/offices/health
     */
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return new ResponseEntity<>("Office service is running", HttpStatus.OK);
    }
}
