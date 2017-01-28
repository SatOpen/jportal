package io.github.jhipster.sample.repository;

import io.github.jhipster.sample.domain.PackageTv;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the PackageTv entity.
 */
@SuppressWarnings("unused")
public interface PackageTvRepository extends JpaRepository<PackageTv,Long> {

}
