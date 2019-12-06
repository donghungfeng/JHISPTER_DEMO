package com.api.hispterdemo.service;

import com.api.hispterdemo.domain.TrangThai;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link TrangThai}.
 */
public interface TrangThaiService {

    /**
     * Save a trangThai.
     *
     * @param trangThai the entity to save.
     * @return the persisted entity.
     */
    TrangThai save(TrangThai trangThai);

    /**
     * Get all the trangThais.
     *
     * @return the list of entities.
     */
    List<TrangThai> findAll();


    /**
     * Get the "id" trangThai.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TrangThai> findOne(Long id);

    /**
     * Delete the "id" trangThai.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
