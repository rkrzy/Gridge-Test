package com.example.demo.src.report;

import com.example.demo.src.report.entity.CommentReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentReportRepository extends JpaRepository<CommentReport, Long> {
}
