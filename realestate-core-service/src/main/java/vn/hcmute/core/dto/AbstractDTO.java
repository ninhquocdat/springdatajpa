package vn.hcmute.core.dto;

import java.io.Serializable;

public class AbstractDTO implements Serializable {
  private Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
