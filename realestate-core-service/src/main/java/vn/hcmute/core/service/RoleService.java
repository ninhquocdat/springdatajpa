package vn.hcmute.core.service;

import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.DuplicateKeyException;
import vn.hcmute.core.dto.RoleDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 20/6/2017.
 */
public interface RoleService {
    Object[] searchByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, Integer firstItem, Integer maxPageItems);
    void updateRole(RoleDTO dto) throws DuplicateKeyException;
    void saveRole(RoleDTO dto);
    RoleDTO findByRoleId(Long roleId) throws ObjectNotFoundException;
    Integer deleteRole(List<Long> roleIds);
    List<RoleDTO> findAllRole();
}
