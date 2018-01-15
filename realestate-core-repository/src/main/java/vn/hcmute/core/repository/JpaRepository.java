package vn.hcmute.core.repository;

import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.DuplicateKeyException;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface JpaRepository<PK extends Serializable,T> {
    List<T> findAll();
    T update(T var1) throws DuplicateKeyException;
    T save(T var1) throws DuplicateKeyException;
    T findById(PK var1) throws ObjectNotFoundException;
    Integer delete(List<PK> var1);
    Object[] searchByProperties(Map<String, Object> properties,
                                String sortExpression, String sortDirection,
                                Integer offset, Integer limit);
}
