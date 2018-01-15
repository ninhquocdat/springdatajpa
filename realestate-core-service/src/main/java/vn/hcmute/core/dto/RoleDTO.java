package vn.hcmute.core.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Admin on 17/6/2017.
 */
public class RoleDTO extends AbstractDTO {
    private String name;
    private String code;
    private List<PermissionDTO> permissions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<PermissionDTO> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionDTO> permissions) {
        this.permissions = permissions;
    }
}
