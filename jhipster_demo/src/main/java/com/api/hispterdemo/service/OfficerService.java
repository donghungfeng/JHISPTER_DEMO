package com.api.hispterdemo.service;

import com.api.hispterdemo.domain.Officer;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Officer}.
 */
public interface OfficerService {

    /**
     * Save a officer.
     *
     * @param officer the entity to save.
     * @return the persisted entity.
     */
    Officer save(Officer officer);

    /**
     * Get all the officers.
     *
     * @return the list of entities.
     */
    List<Officer> findAll();


    /**
     * Get the "id" officer.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Officer> findOne(Long id);

    /**
     * Delete the "id" officer.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
