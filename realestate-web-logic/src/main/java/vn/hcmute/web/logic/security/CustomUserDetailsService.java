package vn.hcmute.web.logic.security;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.hcmute.core.dto.PermissionDTO;
import vn.hcmute.core.dto.RoleDTO;
import vn.hcmute.core.dto.UserDTO;
import vn.hcmute.core.service.UserService;
import vn.hcmute.web.logic.utils.MyUserDetail;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	private final Logger log = Logger.getLogger(CustomUserDetailsService.class);

	@Autowired
	private UserService userService;

	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) {
		UserDTO userDTO = userService.findByUserName(username);
		if(userDTO == null) {
			log.error("user not found");
			throw new UsernameNotFoundException("Username not found");
		}
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (RoleDTO role: userDTO.getRoles()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getCode()));
			for (PermissionDTO permission: role.getPermissions()) {
				authorities.add(new SimpleGrantedAuthority("PERMISSION_"+permission.getCode()));
			}
		}
		MyUserDetail myUserDetail = new MyUserDetail(username, userDTO.getPassword(), true, true, true, true, authorities);
		BeanUtils.copyProperties(userDTO, myUserDetail);
		return myUserDetail;
	}
}
