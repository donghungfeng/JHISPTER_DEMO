package com.api.hispterdemo.service;

import com.api.hispterdemo.domain.DeTai;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link DeTai}.
 */
public interface DeTaiService {

    /**
     * Save a deTai.
     *
     * @param deTai the entity to save.
     * @return the persisted entity.
     */
    DeTai save(DeTai deTai);

    /**
     * Get all the deTais.
     *
     * @return the list of entities.
     */
    List<DeTai> findAll();


    /**
     * Get the "id" deTai.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DeTai> findOne(Long id);

    /**
     * Delete the "id" deTai.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
