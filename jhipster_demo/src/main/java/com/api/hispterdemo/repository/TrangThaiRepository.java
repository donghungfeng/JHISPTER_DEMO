package com.api.hispterdemo.repository;
import com.api.hispterdemo.domain.TrangThai;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TrangThai entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TrangThaiRepository extends JpaRepository<TrangThai, Long> {

}
