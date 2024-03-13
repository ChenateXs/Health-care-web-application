package it.engineering.nc.app.specification;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import it.engineering.nc.app.entity.PractitionerEntity;

public class GenericFilterSpecification<T> implements Specification<T> {
	
	private List<SearchCriteria> list = new ArrayList<>();
	
	public void add(SearchCriteria criteria) {
        list.add(criteria);
    }
	
	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		query.distinct(true);
		List<Predicate> andPredicates = new ArrayList<Predicate>();
		List<Predicate> orPredicates  = new ArrayList<Predicate>();
		Predicate predicate = null;
		String key = "";
		Collections.sort(list);
        for (SearchCriteria criteria : list) {
        	switch (criteria.getOperation()) {
                case GREATER_THAN:
                	predicate = criteriaBuilder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString());
                    break;
                    
                case LESS_THAN:
                	predicate = criteriaBuilder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString());
                    break;
                    
                case GREATER_THAN_EQUAL:
                	predicate = criteriaBuilder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
                    break;
                    
                case LESS_THAN_EQUAL:
                	predicate = criteriaBuilder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
                    break;
                    
                case NOT_EQUAL:
                	predicate = criteriaBuilder.notEqual(root.get(criteria.getKey()), criteria.getValue());
                    break;
                    
                case EQUAL:
                	predicate = criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
                    break;
                    
                case LIKE:
                	predicate = criteriaBuilder.like(criteriaBuilder.lower(root.get(criteria.getKey())), "%" + criteria.getValue().toString().toLowerCase() + "%");
                    break;
                    
                case LIKE_END:
                	predicate = criteriaBuilder.like(criteriaBuilder.lower(root.get(criteria.getKey())), criteria.getValue().toString().toLowerCase() + "%");
                    break;
                    
                case LIKE_START:
                	predicate = criteriaBuilder.like(criteriaBuilder.lower(root.get(criteria.getKey())), "%" + criteria.getValue().toString().toLowerCase());
                    break;
                    
                case IN:
                	predicate = criteriaBuilder.in(root.get(criteria.getKey())).value(criteria.getValue());
                    break;
                    
                case NOT_IN:
                	predicate = criteriaBuilder.not(root.get(criteria.getKey())).in(criteria.getValue());
                    break;
                    
                case LESS_THAN_DATE:
                	try {
                		Date date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                						.parse((String)criteria.getValue());
                		predicate = criteriaBuilder.lessThan(root.get(criteria.getKey()), date);
                	} catch (ParseException e) {
                		e.printStackTrace();
                	}
                    break;
                    
                case GREATER_THAN_DATE:
                	try {
                		Date date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                						.parse((String)criteria.getValue());
                		predicate = criteriaBuilder.greaterThan(root.get(criteria.getKey()), date);
                	} catch (ParseException e) {
                		e.printStackTrace();}
                    break;
                    
                case IN_COLLECTION:
                	predicate = criteriaBuilder.in(root.join(criteria.getKey(),JoinType.LEFT).get("id"+ criteria.getKey().substring(0, 1).toUpperCase() + criteria.getKey().substring(1,criteria.getKey().length()-1))).value(criteria.getValue());
                    break;
                    
                case NOT_IN_COLLECTION:
                	predicate = criteriaBuilder.not(root.join(criteria.getKey(),JoinType.LEFT).get("id"+ criteria.getKey().substring(0, 1).toUpperCase() + criteria.getKey().substring(1,criteria.getKey().length()-1))).in(criteria.getValue());
                	break;
                	
                case IS_NULL:
                	predicate = criteriaBuilder.isNull(root.get(criteria.getKey()));
                    break;
                    
                case IS_NOT_NULL:
                	predicate = criteriaBuilder.isNotNull(root.get(criteria.getKey()));
                	break;
            }
        
        	if(!orPredicates.isEmpty() && !criteria.getKey().equals(key)) {
        		andPredicates.add(criteriaBuilder.or(orPredicates.toArray(new Predicate[0])));
        		orPredicates.clear();
        	}
    		orPredicates.add(predicate);
        	key = criteria.getKey();
        }

		if(!list.isEmpty())
			andPredicates.add(criteriaBuilder.or(orPredicates.toArray(new Predicate[0])));

		return criteriaBuilder.and(andPredicates.toArray(new Predicate[0]));

	}
	
	
}
