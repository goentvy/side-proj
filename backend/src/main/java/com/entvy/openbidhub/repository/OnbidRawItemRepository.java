package com.entvy.openbidhub.repository;

import com.entvy.openbidhub.domain.OnbidRawItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OnbidRawItemRepository extends JpaRepository<OnbidRawItem, Long> {
    Optional<OnbidRawItem> findByCltrMnmtNo(String cltrMnmtNo);
}
