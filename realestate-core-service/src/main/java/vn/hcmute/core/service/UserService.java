package vn.hcmute.core.service;

import org.springframework.dao.DuplicateKeyException;
import vn.hcmute.core.dto.UserDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 17/6/2017.
 */
public interface UserService {
    UserDTO findByUserName(String username);
    Object[] searchByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, Integer firstItem, Integer maxPageItems);
    UserDTO findByUserId(Long userId);
    UserDTO saveUser(UserDTO userDTO) throws DuplicateKeyException;
    Integer deleteUserByUserIds(List<Long> userIds);
}
