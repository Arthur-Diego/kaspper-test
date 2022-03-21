package com.kaspper.teste.specification;

import com.kaspper.teste.dto.request.CandidateRequestDTO;
import com.kaspper.teste.dto.request.JobRequestDTO;
import com.kaspper.teste.model.Candidate;
import com.kaspper.teste.model.Job;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static com.kaspper.teste.utils.ReflectionSpecificationUtils.*;
import static com.kaspper.teste.utils.ReflectionSpecificationUtils.getFieldValue;

public class JobSpecification {

    public static Specification<Job> candidateSpecification(JobRequestDTO dto) {

        return (root, query, cb) -> {

            List<Predicate> predicates = new LinkedList<Predicate>();
            addPredicatesFields(dto, root, cb, predicates);
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    private static <Y> void addPredicatesFields(Object dto, Root<Job> root,
                                                javax.persistence.criteria.CriteriaBuilder cb,
                                                List<Predicate> predicates) {

        Arrays.stream(dto.getClass().getDeclaredFields())
                .filter(f -> verifyIfFieldIsNull(dto, f))
                .forEach(f -> {
                    if (verifyIsNotString(f)) {
                        predicates.add(cb.equal(root.get(f.getName()), getFieldValue(dto, f)));
                    } else {
                        predicates.add(cb.like(root.get(f.getName()), likeAll(getFieldValue(dto, f).toString())));
                    }
                });
    }
}
