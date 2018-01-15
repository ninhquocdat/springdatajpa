package vn.hcmute.core.converter;

import org.modelmapper.ModelMapper;
import vn.hcmute.core.data.entity.UserEntity;
import vn.hcmute.core.dto.UserDTO;

public class UserConverter {

    private ModelMapper modelMapper = new ModelMapper();

    public UserDTO convertToDto(UserEntity entity) {
        UserDTO result = modelMapper.map(entity, UserDTO.class);
        return result;
    }

    public UserEntity convertToEntity(UserDTO dto) {
        UserEntity result = modelMapper.map(dto, UserEntity.class);
        return result;
    }
}
