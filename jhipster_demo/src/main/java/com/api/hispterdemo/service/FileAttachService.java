package com.api.hispterdemo.service;

import com.api.hispterdemo.domain.FileAttach;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link FileAttach}.
 */
public interface FileAttachService {

    /**
     * Save a fileAttach.
     *
     * @param fileAttach the entity to save.
     * @return the persisted entity.
     */
    FileAttach save(FileAttach fileAttach);

    /**
     * Get all the fileAttaches.
     *
     * @return the list of entities.
     */
    List<FileAttach> findAll();


    /**
     * Get the "id" fileAttach.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FileAttach> findOne(Long id);

    /**
     * Delete the "id" fileAttach.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
