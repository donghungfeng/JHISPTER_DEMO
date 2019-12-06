package com.api.hispterdemo.repository;
import com.api.hispterdemo.domain.ToChuc;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ToChuc entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ToChucRepository extends JpaRepository<ToChuc, Long> {

}
