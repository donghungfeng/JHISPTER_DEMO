package com.api.hispterdemo.repository;
import com.api.hispterdemo.domain.XepLoai;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the XepLoai entity.
 */
@SuppressWarnings("unused")
@Repository
public interface XepLoaiRepository extends JpaRepository<XepLoai, Long> {

}
