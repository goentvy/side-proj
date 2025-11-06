package com.entvy.openbidhub.repository;

import com.entvy.openbidhub.domain.OnbidItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OnbidItemRepository extends JpaRepository<OnbidItemEntity, Long>, JpaSpecificationExecutor<OnbidItemEntity> {
    Page<OnbidItemEntity> findAll(Pageable pageable);
    boolean existsByCltrMnmtNo(String cltrMnmtNo);
}