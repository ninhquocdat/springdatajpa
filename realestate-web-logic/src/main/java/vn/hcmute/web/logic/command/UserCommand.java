package vn.hcmute.web.logic.command;

import vn.hcmute.core.dto.RoleDTO;
import vn.hcmute.core.dto.UserDTO;

import java.util.List;

public class UserCommand extends AbstractCommand<UserDTO> {
    public UserCommand(){
        this.pojo = new UserDTO();
    }
    private String passwordRepeat;
    private List<RoleDTO> listRole;

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

    public List<RoleDTO> getListRole() {
        return listRole;
    }

    public void setListRole(List<RoleDTO> listRole) {
        this.listRole = listRole;
    }
}
