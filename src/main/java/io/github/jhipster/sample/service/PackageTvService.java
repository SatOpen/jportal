package io.github.jhipster.sample.service;

import io.github.jhipster.sample.domain.PackageTv;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing PackageTv.
 */
public interface PackageTvService {

    /**
     * Save a packageTv.
     *
     * @param packageTv the entity to save
     * @return the persisted entity
     */
    PackageTv save(PackageTv packageTv);

    /**
     *  Get all the packageTvs.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<PackageTv> findAll(Pageable pageable);

    /**
     *  Get the "id" packageTv.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    PackageTv findOne(Long id);

    /**
     *  Delete the "id" packageTv.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
