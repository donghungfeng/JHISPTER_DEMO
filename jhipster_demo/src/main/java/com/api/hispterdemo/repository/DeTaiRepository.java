package com.api.hispterdemo.repository;
import com.api.hispterdemo.domain.DeTai;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DeTai entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeTaiRepository extends JpaRepository<DeTai, Long> {

}
