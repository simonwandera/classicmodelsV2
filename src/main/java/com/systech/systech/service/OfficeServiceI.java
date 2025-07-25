package com.systech.systech.service;

import com.systech.systech.Entity.Office;

import java.util.List;

public interface OfficeServiceI {
    Office createOffice(Office office);
    Office updateOffice(Long id, Office office);
    void deleteOffice(Long id);
    List<Office> getAllOffices();
    Office getOfficeById(Long id);
}
