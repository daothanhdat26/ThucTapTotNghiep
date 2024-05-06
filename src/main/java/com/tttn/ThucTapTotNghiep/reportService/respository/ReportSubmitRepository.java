package com.tttn.ThucTapTotNghiep.reportService.respository;

import com.tttn.ThucTapTotNghiep.reportService.model.ReportSubmit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportSubmitRepository extends JpaRepository<ReportSubmit,Integer> {
}
