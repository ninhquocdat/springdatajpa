package vn.hcmute.core.repository.impl;

import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.DuplicateKeyException;
import vn.hcmute.core.data.common.Constant;
import vn.hcmute.core.data.utils.HibernateUtil;
import vn.hcmute.core.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


public class SimpleJpaRepository<PK extends Serializable,T> implements JpaRepository<PK,T> {
    private transient static Logger log = Logger.getLogger("SessionBean");
    private final Class<T> persistentClass;

    @PersistenceContext(unitName = "persistence-data")
    protected EntityManager entityManager;

    public SimpleJpaRepository() {
        this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    public String getPersistentClassName() {
        return this.persistentClass.getSimpleName();
    }

    public List<T> findAll() {
        try {
            StringBuilder stringBuilder = new StringBuilder("from ");
            stringBuilder.append(this.getPersistentClassName());
            Query query = entityManager.createQuery(stringBuilder.toString());
            return query.getResultList();
        } catch (RuntimeException var4) {
            throw var4;
        }
    }

    public T update(T entity) throws DuplicateKeyException {
        try {
            T result = entityManager.merge(entity);
            return result;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public T save(T entity) throws DuplicateKeyException {
        try {
            entityManager.persist(entity);
            return entity;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public T findById(PK id) throws ObjectNotFoundException {
        try {
            T instance = entityManager.find(persistentClass, id);
            if (instance == null) {
                throw new ObjectNotFoundException("Not found T " + id, null);
            }
            return instance;
        } catch (RuntimeException var3) {
            throw new ObjectNotFoundException("Not found T " + id, null);
        }
    }

    public Integer delete(List<PK> ids) {
        Integer res = 0;
        for (PK id : ids) {
            try {
                T entity = entityManager.getReference(this.persistentClass, id);
                entityManager.remove(entity);
                res++;
            } catch (RuntimeException re) {
                throw re;
            }
        }
        return res;
    }

    public Object[] searchByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        try {
            final Object[] nameQuery = HibernateUtil.buildNameQuery(properties, null, sortExpression, sortDirection);
            StringBuilder sql1 = new StringBuilder("FROM ");
            sql1.append(getPersistentClassName()).append(Constant.alias).append(" WHERE 1=1 ").append(nameQuery[0]);
            Query query1 = entityManager.createQuery(sql1.toString());
            setParameterToQuery(nameQuery, query1);
            if (offset != null && offset >= 0) {
                query1.setFirstResult(offset);
            }
            if (limit != null && limit > 0) {
                query1.setMaxResults(limit);
            }
            List<T> res = query1.getResultList();

            StringBuilder sql2 = new StringBuilder("SELECT count(*) FROM ");
            sql2.append(getPersistentClassName()).append(Constant.alias).append(" WHERE 1=1 ").append(nameQuery[0]);
            Query query2 = entityManager.createQuery(sql2.toString());
            setParameterToQuery(nameQuery, query2);
            Object totalItem = query2.getSingleResult();
            return new Object[]{totalItem, res};
        } catch (RuntimeException re) {
        }
        return new Object[]{0, new ArrayList<T>()};
    }

    private void setParameterToQuery(Object[] nameQuery, Query query) {
        if (nameQuery.length == 3) {
            String[] params = (String[]) nameQuery[1];
            Object[] values = (Object[]) nameQuery[2];
            for (int i2 = 0; i2 < params.length ; i2++) {
                query.setParameter(params[i2], values[i2]);
            }
        }
    }
}
