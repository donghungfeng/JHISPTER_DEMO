package com.api.hispterdemo.repository;
import com.api.hispterdemo.domain.FileAttach;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FileAttach entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FileAttachRepository extends JpaRepository<FileAttach, Long> {

}
