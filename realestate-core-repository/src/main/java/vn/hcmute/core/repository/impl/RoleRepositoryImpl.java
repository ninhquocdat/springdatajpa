package vn.hcmute.core.repository.impl;

import org.springframework.stereotype.Repository;
import vn.hcmute.core.repository.RoleRepository;
import vn.hcmute.core.data.entity.RoleEntity;

@Repository
public class RoleRepositoryImpl extends SimpleJpaRepository<Long, RoleEntity> implements RoleRepository {
}
