package com.api.hispterdemo.service;

import com.api.hispterdemo.domain.ToChuc;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ToChuc}.
 */
public interface ToChucService {

    /**
     * Save a toChuc.
     *
     * @param toChuc the entity to save.
     * @return the persisted entity.
     */
    ToChuc save(ToChuc toChuc);

    /**
     * Get all the toChucs.
     *
     * @return the list of entities.
     */
    List<ToChuc> findAll();


    /**
     * Get the "id" toChuc.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ToChuc> findOne(Long id);

    /**
     * Delete the "id" toChuc.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
