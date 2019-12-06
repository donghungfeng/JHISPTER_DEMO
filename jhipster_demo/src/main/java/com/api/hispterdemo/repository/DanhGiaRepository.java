package com.api.hispterdemo.repository;
import com.api.hispterdemo.domain.DanhGia;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DanhGia entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DanhGiaRepository extends JpaRepository<DanhGia, Long> {

}
