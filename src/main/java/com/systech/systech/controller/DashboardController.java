package com.systech.systech.controller;

import com.systech.systech.Entity.AuditLog;
import com.systech.systech.Entity.Product;
import com.systech.systech.service.DashboardService;
import com.systech.systech.service.ProductService;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/kpis")
    public ResponseEntity<Map<String, String>> getKPIs() {

        try{
            Map<String, String> kpi = dashboardService.getKPI();
            return ResponseEntity.ok(kpi);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error fetching KPIs: {}", e.getMessage());
            return ResponseEntity
                    .internalServerError()
                    .body(Map.of("error", "Failed to fetch KPIs"));
        }
    }

    @GetMapping("/trends")
    public ResponseEntity<Product> salesTrends() {

        return null;
    }

    @GetMapping("/recentActivities")
    public ResponseEntity<List<AuditLog>> getRecentActivities() {
        try {
            List<AuditLog> auditLogList = dashboardService.getAuditLogs(3);
            return ResponseEntity.ok(auditLogList);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
