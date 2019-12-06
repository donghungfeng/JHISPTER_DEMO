package com.api.hispterdemo.repository;
import com.api.hispterdemo.domain.TienDo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TienDo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TienDoRepository extends JpaRepository<TienDo, Long> {

}
