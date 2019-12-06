package com.api.hispterdemo.service;

import com.api.hispterdemo.domain.DuToan;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link DuToan}.
 */
public interface DuToanService {

    /**
     * Save a duToan.
     *
     * @param duToan the entity to save.
     * @return the persisted entity.
     */
    DuToan save(DuToan duToan);

    /**
     * Get all the duToans.
     *
     * @return the list of entities.
     */
    List<DuToan> findAll();


    /**
     * Get the "id" duToan.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DuToan> findOne(Long id);

    /**
     * Delete the "id" duToan.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
