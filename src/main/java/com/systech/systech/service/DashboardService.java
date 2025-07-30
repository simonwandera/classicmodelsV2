package com.systech.systech.service;

import com.systech.systech.Entity.AuditLog;
import com.systech.systech.Entity.Employee;

import java.util.List;
import java.util.Map;


public interface DashboardService {

    Map<String, String> getKPI();
    List<AuditLog> getAuditLogs(Integer count);

    }