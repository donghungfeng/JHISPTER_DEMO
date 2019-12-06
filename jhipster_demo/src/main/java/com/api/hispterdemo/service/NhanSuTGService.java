package com.api.hispterdemo.service;

import com.api.hispterdemo.domain.NhanSuTG;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link NhanSuTG}.
 */
public interface NhanSuTGService {

    /**
     * Save a nhanSuTG.
     *
     * @param nhanSuTG the entity to save.
     * @return the persisted entity.
     */
    NhanSuTG save(NhanSuTG nhanSuTG);

    /**
     * Get all the nhanSuTGS.
     *
     * @return the list of entities.
     */
    List<NhanSuTG> findAll();


    /**
     * Get the "id" nhanSuTG.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NhanSuTG> findOne(Long id);

    /**
     * Delete the "id" nhanSuTG.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
