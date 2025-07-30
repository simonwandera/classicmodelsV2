package com.systech.systech.Repository;

import com.systech.systech.Entity.AuditLog;
import com.systech.systech.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuditLogRepository extends JpaRepository<AuditLog,Long> {


    @Query("SELECT a FROM AuditLog a  order by a.id desc limit :count")
    List<AuditLog> getAuditLogs(Integer count);
}
