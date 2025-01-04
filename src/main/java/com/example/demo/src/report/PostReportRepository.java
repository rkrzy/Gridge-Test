package com.example.demo.src.report;

import com.example.demo.src.report.entity.PostReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostReportRepository extends JpaRepository<PostReport, Long> {
}
