package com.api.hispterdemo.service;

import com.api.hispterdemo.domain.DanhGia;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link DanhGia}.
 */
public interface DanhGiaService {

    /**
     * Save a danhGia.
     *
     * @param danhGia the entity to save.
     * @return the persisted entity.
     */
    DanhGia save(DanhGia danhGia);

    /**
     * Get all the danhGias.
     *
     * @return the list of entities.
     */
    List<DanhGia> findAll();


    /**
     * Get the "id" danhGia.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DanhGia> findOne(Long id);

    /**
     * Delete the "id" danhGia.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
