package com.api.hispterdemo.service;

import com.api.hispterdemo.domain.TienDo;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link TienDo}.
 */
public interface TienDoService {

    /**
     * Save a tienDo.
     *
     * @param tienDo the entity to save.
     * @return the persisted entity.
     */
    TienDo save(TienDo tienDo);

    /**
     * Get all the tienDos.
     *
     * @return the list of entities.
     */
    List<TienDo> findAll();


    /**
     * Get the "id" tienDo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TienDo> findOne(Long id);

    /**
     * Delete the "id" tienDo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
