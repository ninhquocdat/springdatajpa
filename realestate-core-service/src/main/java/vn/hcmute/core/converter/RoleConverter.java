package vn.hcmute.core.converter;

import org.modelmapper.ModelMapper;
import vn.hcmute.core.data.entity.RoleEntity;
import vn.hcmute.core.dto.RoleDTO;

public class RoleConverter {
    private ModelMapper modelMapper = new ModelMapper();

    public RoleDTO convertToDto(RoleEntity entity) {
        RoleDTO result = modelMapper.map(entity, RoleDTO.class);
        return result;
    }

    public RoleEntity convertToEntity(RoleDTO dto) {
        RoleEntity result = modelMapper.map(dto, RoleEntity.class);
        return result;
    }
}
