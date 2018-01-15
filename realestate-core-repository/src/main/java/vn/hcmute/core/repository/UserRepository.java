package vn.hcmute.core.repository;

import org.hibernate.ObjectNotFoundException;
import vn.hcmute.core.data.entity.UserEntity;

import java.util.List;

/**
 * Created by Admin on 17/6/2017.
 */
public interface UserRepository extends JpaRepository<Long, UserEntity> {
    UserEntity findByUserName(String username) throws ObjectNotFoundException;
    Integer deleteUserByUserIds(List<Long> userIds);
}
