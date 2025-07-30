package com.systech.systech.service;

import com.systech.systech.Entity.AuditLog;
import com.systech.systech.Entity.Office;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuditLogService {

    AuditLog saveOrUpdate(String title, String description);
}
