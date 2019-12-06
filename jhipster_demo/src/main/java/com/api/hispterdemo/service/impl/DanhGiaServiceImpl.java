package com.api.hispterdemo.service.impl;

import com.api.hispterdemo.service.DanhGiaService;
import com.api.hispterdemo.domain.DanhGia;
import com.api.hispterdemo.repository.DanhGiaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link DanhGia}.
 */
@Service
@Transactional
public class DanhGiaServiceImpl implements DanhGiaService {

    private final Logger log = LoggerFactory.getLogger(DanhGiaServiceImpl.class);

    private final DanhGiaRepository danhGiaRepository;

    public DanhGiaServiceImpl(DanhGiaRepository danhGiaRepository) {
        this.danhGiaRepository = danhGiaRepository;
    }

    /**
     * Save a danhGia.
     *
     * @param danhGia the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DanhGia save(DanhGia danhGia) {
        log.debug("Request to save DanhGia : {}", danhGia);
        return danhGiaRepository.save(danhGia);
    }

    /**
     * Get all the danhGias.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<DanhGia> findAll() {
        log.debug("Request to get all DanhGias");
        return danhGiaRepository.findAll();
    }


    /**
     * Get one danhGia by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DanhGia> findOne(Long id) {
        log.debug("Request to get DanhGia : {}", id);
        return danhGiaRepository.findById(id);
    }

    /**
     * Delete the danhGia by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DanhGia : {}", id);
        danhGiaRepository.deleteById(id);
    }
}
