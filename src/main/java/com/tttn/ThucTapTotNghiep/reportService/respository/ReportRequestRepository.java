package com.tttn.ThucTapTotNghiep.reportService.respository;

import com.tttn.ThucTapTotNghiep.reportService.model.ReportRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRequestRepository extends JpaRepository<ReportRequest,Integer> {
    List<ReportRequest> findBySubjectClass(Integer classId);

}
