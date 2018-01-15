package vn.hcmute.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.hcmute.core.converter.UserConverter;
import vn.hcmute.core.data.entity.RoleEntity;
import vn.hcmute.core.repository.UserRepository;
import vn.hcmute.core.data.common.Constant;
import vn.hcmute.core.data.entity.UserEntity;
import vn.hcmute.core.dto.UserDTO;
import vn.hcmute.core.service.UserService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTO findByUserName(String username) {
        UserConverter converter = new UserConverter();
        return converter.convertToDto(userDao.findByUserName(username));
    }

    public Object[] searchByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, Integer firstItem, Integer maxPageItems) {
        Object[] results = userDao.searchByProperties(properties, sortExpression, sortDirection, firstItem, maxPageItems);
        List<UserDTO> userDTOS = new ArrayList<UserDTO>();
        UserConverter converter = new UserConverter();
        for (UserEntity entity : (List<UserEntity>)results[1]){
            UserDTO dto = converter.convertToDto(entity);
            userDTOS.add(dto);
        }
        results[1] = userDTOS;
        return results;
    }

    public UserDTO findByUserId(Long userId) {
        UserEntity entity = userDao.findById(userId);
        UserConverter converter = new UserConverter();
        UserDTO dto = converter.convertToDto(entity);
        return dto;
    }

    @Transactional
    public UserDTO saveUser(UserDTO userDTO) throws DuplicateKeyException {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        userDTO.setCreatedDate(currentTime);
        userDTO.setStatus(Constant.ACTIVE_USER);
        UserConverter converter = new UserConverter();
        UserEntity entity = converter.convertToEntity(userDTO);
        return converter.convertToDto(userDao.save(entity));
    }

    @Transactional
    public Integer deleteUserByUserIds(List<Long> userIds) {
        Integer result = userDao.deleteUserByUserIds(userIds);
        return result;
    }
}
