package vn.hcmute.web.logic.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 17/6/2017.
 */
public class SecurityUtils {

    public static MyUserDetail getPrincipal() {
        return (MyUserDetail) (SecurityContextHolder
            .getContext()).getAuthentication().getPrincipal();
    }

    public static Long getLoginUserId() {
        return getPrincipal().getUserId();
    }

    public static boolean userHasAuthority(String roleCode) {
        List<GrantedAuthority> list = (List<GrantedAuthority>)(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        List<GrantedAuthority> authories = list;
        for (GrantedAuthority authority : authories) {
            if (authority.getAuthority().equals(roleCode)) {
                return true;
            }
        }
        return false;
    }
    public static List<String> getAuthorities() {
        List<String> results = new ArrayList<String>();
        List<GrantedAuthority> authorities = (List<GrantedAuthority>)(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        for (GrantedAuthority authority : authorities) {
            results.add(authority.getAuthority());
        }
        return results;
    }
    public static String getPrinciple() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}
