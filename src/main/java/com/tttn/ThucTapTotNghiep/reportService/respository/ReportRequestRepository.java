package com.tttn.ThucTapTotNghiep.reportService.respository;

import com.tttn.ThucTapTotNghiep.reportService.model.ReportRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRequestRepository extends JpaRepository<ReportRequest,Integer> {
}
