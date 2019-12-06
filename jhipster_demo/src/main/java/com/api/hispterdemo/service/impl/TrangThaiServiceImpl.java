package com.api.hispterdemo.service.impl;

import com.api.hispterdemo.service.TrangThaiService;
import com.api.hispterdemo.domain.TrangThai;
import com.api.hispterdemo.repository.TrangThaiRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link TrangThai}.
 */
@Service
@Transactional
public class TrangThaiServiceImpl implements TrangThaiService {

    private final Logger log = LoggerFactory.getLogger(TrangThaiServiceImpl.class);

    private final TrangThaiRepository trangThaiRepository;

    public TrangThaiServiceImpl(TrangThaiRepository trangThaiRepository) {
        this.trangThaiRepository = trangThaiRepository;
    }

    /**
     * Save a trangThai.
     *
     * @param trangThai the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TrangThai save(TrangThai trangThai) {
        log.debug("Request to save TrangThai : {}", trangThai);
        return trangThaiRepository.save(trangThai);
    }

    /**
     * Get all the trangThais.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<TrangThai> findAll() {
        log.debug("Request to get all TrangThais");
        return trangThaiRepository.findAll();
    }


    /**
     * Get one trangThai by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TrangThai> findOne(Long id) {
        log.debug("Request to get TrangThai : {}", id);
        return trangThaiRepository.findById(id);
    }

    /**
     * Delete the trangThai by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TrangThai : {}", id);
        trangThaiRepository.deleteById(id);
    }
}
