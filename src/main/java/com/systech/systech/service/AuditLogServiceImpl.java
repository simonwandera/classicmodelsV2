package com.systech.systech.service;

import com.systech.systech.Entity.AuditLog;
import com.systech.systech.Entity.Customer;
import com.systech.systech.Repository.AuditLogRepository;
import com.systech.systech.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuditLogServiceImpl implements AuditLogService {

    private final AuditLogRepository auditLogRepository;


    @Override
    public AuditLog saveOrUpdate(String title, String description) {
        AuditLog auditLog = new AuditLog();

        auditLog.setTitle(title);
        auditLog.setDescription(description);

        return auditLogRepository.save(auditLog);
    }
}
