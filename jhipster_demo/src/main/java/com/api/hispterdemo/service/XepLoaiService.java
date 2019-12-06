package com.api.hispterdemo.service;

import com.api.hispterdemo.domain.XepLoai;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link XepLoai}.
 */
public interface XepLoaiService {

    /**
     * Save a xepLoai.
     *
     * @param xepLoai the entity to save.
     * @return the persisted entity.
     */
    XepLoai save(XepLoai xepLoai);

    /**
     * Get all the xepLoais.
     *
     * @return the list of entities.
     */
    List<XepLoai> findAll();


    /**
     * Get the "id" xepLoai.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<XepLoai> findOne(Long id);

    /**
     * Delete the "id" xepLoai.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
