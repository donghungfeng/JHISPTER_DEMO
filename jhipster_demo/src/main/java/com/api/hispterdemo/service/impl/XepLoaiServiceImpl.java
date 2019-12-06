package com.api.hispterdemo.service.impl;

import com.api.hispterdemo.service.XepLoaiService;
import com.api.hispterdemo.domain.XepLoai;
import com.api.hispterdemo.repository.XepLoaiRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link XepLoai}.
 */
@Service
@Transactional
public class XepLoaiServiceImpl implements XepLoaiService {

    private final Logger log = LoggerFactory.getLogger(XepLoaiServiceImpl.class);

    private final XepLoaiRepository xepLoaiRepository;

    public XepLoaiServiceImpl(XepLoaiRepository xepLoaiRepository) {
        this.xepLoaiRepository = xepLoaiRepository;
    }

    /**
     * Save a xepLoai.
     *
     * @param xepLoai the entity to save.
     * @return the persisted entity.
     */
    @Override
    public XepLoai save(XepLoai xepLoai) {
        log.debug("Request to save XepLoai : {}", xepLoai);
        return xepLoaiRepository.save(xepLoai);
    }

    /**
     * Get all the xepLoais.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<XepLoai> findAll() {
        log.debug("Request to get all XepLoais");
        return xepLoaiRepository.findAll();
    }


    /**
     * Get one xepLoai by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<XepLoai> findOne(Long id) {
        log.debug("Request to get XepLoai : {}", id);
        return xepLoaiRepository.findById(id);
    }

    /**
     * Delete the xepLoai by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete XepLoai : {}", id);
        xepLoaiRepository.deleteById(id);
    }
}
