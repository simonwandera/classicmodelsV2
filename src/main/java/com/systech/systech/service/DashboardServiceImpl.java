package com.systech.systech.service;

import com.systech.systech.Entity.AuditLog;
import com.systech.systech.Repository.AuditLogRepository;
import com.systech.systech.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
@Slf4j
public class DashboardServiceImpl implements DashboardService {

    private final AuditLogRepository auditLogRepository;
    private final PaymentService paymentService;
    private final CustomerService customerService;
    private final OrderService orderService;
    private final ProductService productService;

    @Override
    public Map<String, String> getKPI() {

        BigDecimal totalSales = paymentService.getTotalSalesThisYear();

        BigDecimal customerCount = customerService.getCustomerCount();

        BigDecimal orderVolume = orderService.getOrderVolumeThisYear();

        String mostSoldProduct = productService.getMostSoldProduct();

        Map<String, String> getKPI = new HashMap<>();
        getKPI.put("totalSales", StringUtil.formatBigDecimalToAmount(totalSales, "Ksh", null));
        getKPI.put("customerCount", customerCount.toString());
        getKPI.put("orderVolume", orderVolume.toString());
        getKPI.put("mostSoldProduct", mostSoldProduct);

        return  getKPI;

    }

    @Override
    public List<AuditLog> getAuditLogs(Integer count) {
        return auditLogRepository.getAuditLogs(count);
    }
}