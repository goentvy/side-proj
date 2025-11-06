package com.entvy.openbidhub.repository.spec;

import com.entvy.openbidhub.domain.OnbidItemEntity;
import com.entvy.openbidhub.dto.OnbidItemSearchCondition;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class OnbidItemSpecification {

    public static Specification<OnbidItemEntity> withCondition(OnbidItemSearchCondition cond) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (cond.getSido() != null)
                predicates.add(cb.equal(root.get("sido"), cond.getSido()));
            if (cond.getSgk() != null)
                predicates.add(cb.equal(root.get("sgk"), cond.getSgk()));
            if (cond.getEmd() != null)
                predicates.add(cb.equal(root.get("emd"), cond.getEmd()));
            if (cond.getCltrNm() != null)
                predicates.add(cb.like(root.get("cltrNm"), "%" + cond.getCltrNm() + "%"));
            if (cond.getBidStatus() != null)
                predicates.add(cb.equal(root.get("bidStatus"), cond.getBidStatus()));
            if (cond.getOpenPriceFrom() != null)
                predicates.add(cb.greaterThanOrEqualTo(root.get("openPrice"), cond.getOpenPriceFrom()));
            if (cond.getOpenPriceTo() != null)
                predicates.add(cb.lessThanOrEqualTo(root.get("openPrice"), cond.getOpenPriceTo()));
            if (cond.getPbctBegnDtFrom() != null)
                predicates.add(cb.greaterThanOrEqualTo(root.get("pbctBegnDt"), cond.getPbctBegnDtFrom()));
            if (cond.getPbctBegnDtTo() != null)
                predicates.add(cb.lessThanOrEqualTo(root.get("pbctBegnDt"), cond.getPbctBegnDtTo()));

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}