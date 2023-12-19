package com.example.moneyapi.dto.LaunchRepositorys;

import com.example.moneyapi.dto.LaunchRepositorys.LaunchRepositoryQuery;
import com.example.moneyapi.filters.LaunchFilter;
import com.example.moneyapi.model.LaunchModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class LaunchRepositoryImpl implements LaunchRepositoryQuery {
    @PersistenceContext
    private EntityManager manager;
    @Override
    public List<LaunchModel> filtersLaunch(LaunchFilter launchFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<LaunchModel> criteria = builder.createQuery(LaunchModel.class);

        Root<LaunchModel> root = criteria.from(LaunchModel.class);

        Predicate[] predicates = createRestrictions(launchFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<LaunchModel> query = manager.createQuery(criteria);
        return query.getResultList();
    }

    private Predicate[] createRestrictions(LaunchFilter launchFilter, CriteriaBuilder builder, Root<LaunchModel> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(launchFilter.getDescription())) {
            predicates.add(builder.like(
                    builder.lower(root.get("description")), "%" + launchFilter.getDescription().toLowerCase() + "%" )
            );
        }
        if(launchFilter.getDueDateOf() != null){
            predicates.add(builder.like(
                    builder.lower(root.get("DueDateOf")), "%" + launchFilter.getDueDateOf() + "%"
            ));
        }
        if (launchFilter.getDueDateBy() != null){
            predicates.add(builder.like(
                    builder.lower(root.get("DueDateBy")), "%" + launchFilter.getDueDateBy() + "%"
            ));
        }
        return predicates.toArray(new Predicate[predicates.size()]);
    }
}
