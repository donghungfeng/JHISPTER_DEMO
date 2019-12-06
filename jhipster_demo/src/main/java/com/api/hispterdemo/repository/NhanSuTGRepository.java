package com.api.hispterdemo.repository;
import com.api.hispterdemo.domain.NhanSuTG;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the NhanSuTG entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NhanSuTGRepository extends JpaRepository<NhanSuTG, Long> {

}
