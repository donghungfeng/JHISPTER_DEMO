package com.api.hispterdemo.service;

import com.api.hispterdemo.domain.CapDeTai;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link CapDeTai}.
 */
public interface CapDeTaiService {

    /**
     * Save a capDeTai.
     *
     * @param capDeTai the entity to save.
     * @return the persisted entity.
     */
    CapDeTai save(CapDeTai capDeTai);

    /**
     * Get all the capDeTais.
     *
     * @return the list of entities.
     */
    List<CapDeTai> findAll();


    /**
     * Get the "id" capDeTai.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CapDeTai> findOne(Long id);

    /**
     * Delete the "id" capDeTai.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
