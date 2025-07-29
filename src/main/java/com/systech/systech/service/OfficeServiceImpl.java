package com.systech.systech.service;

import com.systech.systech.Entity.Office;
import com.systech.systech.Repository.OfficeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OfficeServiceImpl implements OfficeService {

    private final OfficeRepository officeRepository;

    @Override
    public Office createOffice(Office office) {
        return officeRepository.save(office);
    }

    @Override
    public Office updateOffice(Long id, Office office) {
        Optional<Office> existing = officeRepository.findById(id);
        if (existing.isPresent()) {
            office.setId(id);
            return officeRepository.save(office);
        } else {
            throw new RuntimeException("Office not found with id: " + id);
        }
    }

    @Override
    public void deleteOffice(Long id) {
        officeRepository.deleteById(id);
    }

    @Override
    public List<Office> getAllOffices() {
        List<Office> offices =  officeRepository.findAll();
        if (offices.isEmpty()) {
            return Collections.emptyList();
        }
        return offices;
    }

    @Override
    public Office getOfficeById(Long id) {
        return officeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Office not found with id: " + id));
    }
}
