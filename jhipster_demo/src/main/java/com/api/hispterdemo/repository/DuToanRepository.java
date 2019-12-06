package com.api.hispterdemo.repository;
import com.api.hispterdemo.domain.DuToan;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DuToan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DuToanRepository extends JpaRepository<DuToan, Long> {

}
