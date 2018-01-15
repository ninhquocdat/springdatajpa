package vn.hcmute.web.logic.command;

import vn.hcmute.core.dto.RoleDTO;

public class RoleCommand extends AbstractCommand<RoleDTO> {
    public RoleCommand() {
        this.pojo = new RoleDTO();
    }
}
