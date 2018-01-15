package vn.hcmute.core.data.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "permission")
public class PermissionEntity extends BaseEntity {

  @Column
  private String name;

  @Column(length = 24)
  private String code;

  @ManyToMany(fetch = FetchType.EAGER, mappedBy = "permissions")
  private List<RoleEntity> roles;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<RoleEntity> getRoles() {
    return roles;
  }

  public void setRoles(List<RoleEntity> roles) {
    this.roles = roles;
  }
}
