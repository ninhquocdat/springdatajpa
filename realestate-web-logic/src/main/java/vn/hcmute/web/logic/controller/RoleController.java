package vn.hcmute.web.logic.controller;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import vn.hcmute.core.data.common.Constant;
import vn.hcmute.core.dto.RoleDTO;
import vn.hcmute.core.service.RoleService;
import vn.hcmute.web.logic.command.RoleCommand;
import vn.hcmute.web.logic.utils.RequestUtil;
import vn.hcmute.web.logic.utils.WebCommonUtil;
import vn.hcmute.web.logic.validator.RoleValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 20/6/2017.
 */
@Controller
public class RoleController extends ApplicationObjectSupport {
    private final transient Logger log = Logger.getLogger(RoleController.class);
    @Autowired
    RoleService roleService;

    @Autowired
    RoleValidator roleValidator;

    @RequestMapping(value = {"/admin/role/list.html"})
    public ModelAndView listRole(@ModelAttribute(value = Constant.LIST_MODEL_KEY) RoleCommand command,
                                 HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/admin/role/list");
        String crudaction = command.getCrudaction();
        Map<String, String> messageMap = new HashMap<String, String>();
        try {
            if (StringUtils.isNotBlank(crudaction) && Constant.ACTION_DELETE.equals(crudaction)) {
                String[] checkList = command.getCheckList();
                if (checkList != null && checkList.length > 0) {
                    List<Long> roleIds = new ArrayList<Long>();
                    for (String id : checkList) {
                        Long roleId = Long.parseLong(id);
                        roleIds.add(roleId);
                    }
                    if (roleIds.size() > 0) {
                        Integer checkDelete = roleService.deleteRole(roleIds);
                        if (checkDelete != null && checkDelete.equals(roleIds.size())) {
                            messageMap.put(Constant.ACTION_DELETE, this.getMessageSourceAccessor().getMessage("label.delete.success"));
                        } else {
                            messageMap.put(Constant.REDIRECT_ERROR, this.getMessageSourceAccessor().getMessage("database.exception"));
                        }
                    }
                }
            }
            messageMap.put(Constant.REDIRECT_UPDATE, this.getMessageSourceAccessor().getMessage("label.update.success"));
            messageMap.put(Constant.REDIRECT_INSERT, this.getMessageSourceAccessor().getMessage("label.save.success"));
            messageMap.put(Constant.REDIRECT_ERROR, this.getMessageSourceAccessor().getMessage("database.exception"));
            WebCommonUtil.addRedirectMsg(mav, command.getCrudaction(), messageMap);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            mav.addObject(Constant.ALTER, Constant.TYPE_DANGER);
            mav.addObject(Constant.MESSAGE_RESPONSE, this.getMessageSourceAccessor().getMessage("database.exception"));
        }
        executeSearchRole(request, command);
        mav.addObject(Constant.LIST_MODEL_KEY, command);
        return mav;
    }

    private void executeSearchRole(HttpServletRequest request, RoleCommand command) {
        RequestUtil.initSearchBean(request, command);
        Map<String, Object> properties = buildMapProperties4Search(command);
        Object[] results = roleService.searchByProperties(properties, command.getSortExpression(), command.getSortDirection(), command.getFirstItem(), command.getMaxPageItems());
        command.setListResult((List<RoleDTO>) results[1]);
        command.setTotalItems(Integer.valueOf(results[0].toString()));
    }

    private Map<String,Object> buildMapProperties4Search(RoleCommand command) {
        Map<String, Object> properties = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(command.getPojo().getCode())) {
            properties.put("code", command.getPojo().getCode().trim());
        }
        if (StringUtils.isNotBlank(command.getPojo().getName())) {
            properties.put("name", command.getPojo().getName().trim());
        }
        return properties;
    }

    @RequestMapping(value = {"/ajax/admin/role/edit.html"})
    public ModelAndView addOrEditRole(@ModelAttribute(value = Constant.FORM_MODEL_KEY) RoleCommand command,
                                      BindingResult bindingResult) {
        ModelAndView mav = new ModelAndView("/admin/role/edit");
        String crudaction = command.getCrudaction();
        RoleDTO pojo = command.getPojo();
        try {
            if (StringUtils.isNotBlank(crudaction) && Constant.INSERT_OR_UPDATE.equals(crudaction)) {
                roleValidator.validate(command, bindingResult);
                if (!bindingResult.hasErrors()) {
                    if (pojo != null && pojo.getId() == null) {
                        roleService.saveRole(pojo);
                        mav.addObject(Constant.MESSAGE_RESPONSE, Constant.REDIRECT_INSERT);
                    }else {
                        roleService.updateRole(pojo);
                        mav.addObject(Constant.MESSAGE_RESPONSE, Constant.REDIRECT_UPDATE);
                    }
                }
            }
            if (pojo != null && pojo.getId() != null) {
                command.setPojo(roleService.findByRoleId(pojo.getId()));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            mav.addObject(Constant.MESSAGE_RESPONSE, Constant.REDIRECT_ERROR);
        }
        mav.addObject(Constant.FORM_MODEL_KEY, command);
        return mav;
    }
}
