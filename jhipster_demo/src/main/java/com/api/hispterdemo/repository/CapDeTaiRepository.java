package com.api.hispterdemo.repository;
import com.api.hispterdemo.domain.CapDeTai;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CapDeTai entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CapDeTaiRepository extends JpaRepository<CapDeTai, Long> {

}
