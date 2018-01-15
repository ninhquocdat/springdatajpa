package vn.hcmute.core.data.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="role")
public class RoleEntity extends BaseEntity {

    @Column
    private String name;

    @Column
    private String code;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "permission_role",
        joinColumns = { @JoinColumn(name = "role_id", nullable = false) },
        inverseJoinColumns = { @JoinColumn(name = "permission_id", nullable = false) })
    private List<PermissionEntity> permissions;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
    private List<UserEntity> users;

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

    public List<PermissionEntity> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionEntity> permissions) {
        this.permissions = permissions;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
}
