package vn.hcmute.core.service.impl;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.hcmute.core.converter.RoleConverter;
import vn.hcmute.core.repository.RoleRepository;
import vn.hcmute.core.data.entity.RoleEntity;
import vn.hcmute.core.dto.RoleDTO;
import vn.hcmute.core.service.RoleService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    public Object[] searchByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, Integer firstItem, Integer maxPageItems) {
        Object[] results = roleRepository.searchByProperties(properties, sortExpression, sortDirection, firstItem, maxPageItems);
        List<RoleDTO> roleDTOS = new ArrayList<RoleDTO>();
        for (RoleEntity entity : (List<RoleEntity>)results[1]){
            RoleConverter converter = new RoleConverter();
            RoleDTO dto = converter.convertToDto(entity);
            roleDTOS.add(dto);
        }
        results[1] = roleDTOS;
        return results;
    }

    @Transactional
    public void updateRole(RoleDTO dto) throws DuplicateKeyException {
        RoleConverter converter = new RoleConverter();
        RoleEntity entity = converter.convertToEntity(dto);
        roleRepository.update(entity);
    }

    @Transactional
    public void saveRole(RoleDTO dto) throws DuplicateKeyException {
        RoleConverter converter = new RoleConverter();
        RoleEntity entity = converter.convertToEntity(dto);
        roleRepository.save(entity);
    }

    public RoleDTO findByRoleId(Long roleId) throws ObjectNotFoundException {
        RoleEntity entity = roleRepository.findById(roleId);
        RoleDTO dto = new RoleDTO();
        if (entity != null) {
            RoleConverter converter = new RoleConverter();
            dto = converter.convertToDto(entity);
        }
        return dto;
    }

    @Transactional
    public Integer deleteRole(List<Long> roleIds) {
        return roleRepository.delete(roleIds);
    }

    public List<RoleDTO> findAllRole() {
        List<RoleEntity> entities = roleRepository.findAll();
        List<RoleDTO> roleDTOS = new ArrayList<RoleDTO>();
        for (RoleEntity item: entities) {
            RoleConverter converter = new RoleConverter();
            RoleDTO dto = converter.convertToDto(item);
            roleDTOS.add(dto);
        }
        return roleDTOS;
    }
}
