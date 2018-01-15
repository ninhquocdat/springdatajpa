package vn.hcmute.core.repository.impl;

import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Repository;
import vn.hcmute.core.repository.UserRepository;
import vn.hcmute.core.data.entity.UserEntity;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserRepositoryImpl extends SimpleJpaRepository<Long, UserEntity> implements UserRepository {

    public UserEntity findByUserName(String username) throws ObjectNotFoundException {
        try {
            StringBuilder sql = new StringBuilder("from UserEntity ue where ue.userName= :userName");
            Query query = entityManager.createQuery(sql.toString());
            query.setParameter("userName", username);
            return (UserEntity) query.getSingleResult();
        } catch (NoResultException e) {
            throw new ObjectNotFoundException("NoResultException", username);
        }
    }

    public Integer deleteUserByUserIds(List<Long> userIds) {
        StringBuilder sql = new StringBuilder("UPDATE UserEntity ue SET ue.status = 0 WHERE ue.id in (:listUserId)");
        Query query = entityManager.createQuery(sql.toString());
        query.setParameter("listUserId", userIds);
        query.executeUpdate();
        return userIds.size();
    }
}
