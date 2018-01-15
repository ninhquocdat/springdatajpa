package vn.hcmute.web.logic.controller;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.hcmute.core.data.common.Constant;
import vn.hcmute.core.dto.Mail;
import vn.hcmute.core.dto.RoleDTO;
import vn.hcmute.core.dto.UserDTO;
import vn.hcmute.core.service.MailService;
import vn.hcmute.core.service.RoleService;
import vn.hcmute.core.service.UserService;
import vn.hcmute.web.logic.command.UserCommand;
import vn.hcmute.web.logic.utils.RequestUtil;
import vn.hcmute.web.logic.utils.WebCommonUtil;
import vn.hcmute.web.logic.validator.UserValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class UserController extends ApplicationObjectSupport {
    private final transient Logger log = Logger.getLogger(UserController.class);
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserValidator userValidator;

    @Autowired
    private MailService mailService;

    @RequestMapping(value = {"/admin/user/list.html"})
    public ModelAndView showListUser(@ModelAttribute(Constant.LIST_MODEL_KEY) UserCommand command, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/admin/user/list");
        String crudaction = command.getCrudaction();
        Map<String, String> messageMap = new HashMap<String, String>();
        UserDTO dto = command.getPojo();
        try {
            if (StringUtils.isNotBlank(crudaction) && Constant.ACTION_DELETE.equals(crudaction)) {
                String[] checkList = command.getCheckList();
                if (checkList != null && checkList.length > 0) {
                    List<Long> userIds = new ArrayList<Long>();
                    for (String id : checkList) {
                        Long userId = Long.parseLong(id);
                        userIds.add(userId);
                    }
                    if (userIds.size() > 0) {
                        Integer checkDelete = userService.deleteUserByUserIds(userIds);
                        if (checkDelete != null && checkDelete.equals(userIds.size())) {
                            messageMap.put(Constant.ACTION_DELETE, this.getMessageSourceAccessor().getMessage("label.delete.success"));
                        } else {
                            messageMap.put(Constant.REDIRECT_ERROR, this.getMessageSourceAccessor().getMessage("database.exception"));
                        }
                    }
                }
            }
            messageMap.put(Constant.REDIRECT_ERROR, this.getMessageSourceAccessor().getMessage("database.exception"));
            WebCommonUtil.addRedirectMsg(mav, command.getCrudaction(), messageMap);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            mav.addObject(Constant.ALTER, Constant.TYPE_DANGER);
            mav.addObject(Constant.MESSAGE_RESPONSE, this.getMessageSourceAccessor().getMessage("database.exception"));
        }
        executeSearchUser(command, request);
        command.setListRole(roleService.findAllRole());
        mav.addObject(Constant.LIST_MODEL_KEY, command);
        return mav;
    }

    private void executeSearchUser(UserCommand command, HttpServletRequest request) {
        RequestUtil.initSearchBean(request, command);
        Map<String, Object> properties = buildProperties(command);
        Object[] results = userService.searchByProperties(properties, command.getSortExpression(), command.getSortDirection(),
                command.getFirstItem(), command.getMaxPageItems());
        command.setListResult((List<UserDTO>) results[1]);
        command.setTotalItems(Integer.valueOf(results[0].toString()));
    }

    private Map<String, Object> buildProperties(UserCommand command) {
        UserDTO pojo = command.getPojo();
        Map<String, Object> properties = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(pojo.getUserName())) {
            properties.put("userName", pojo.getUserName());
        }
        if (StringUtils.isNotBlank(pojo.getFullName())) {
            properties.put("fullName", pojo.getFullName());
        }
        properties.put("status", Constant.ACTIVE);
        return properties;
    }

    @RequestMapping(value = {"/ajax/admin/user/edit.html"})
    public ModelAndView addOrEditRole(@ModelAttribute(value = Constant.FORM_MODEL_KEY) UserCommand command,
                                      BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView("/admin/user/edit");
        String crudaction = command.getCrudaction();
        UserDTO pojo = command.getPojo();
        try {
            if (StringUtils.isNotBlank(crudaction) && Constant.INSERT_OR_UPDATE.equals(crudaction)) {
                userValidator.validate(command, bindingResult);
                if (!bindingResult.hasErrors()) {
                    if (pojo != null && pojo.getId() == null) {
                        String password = WebCommonUtil.generatePassword();
                        pojo.setPassword(password);
                        userService.saveUser(pojo);
                        Mail mail = new Mail();
                        mail.setMailFrom("lamjavaweb123@gmail.com");
                        mail.setMailTo("meoiancom@gmail.com");
                        mail.setMailSubject("Spring 4 - Email with FreeMarker template");
                        String template = "Tai khoan moi tao cua ban la: ${username} - ${password}";
                        Map<String, Object> model = new HashMap<String, Object>();
                        model.put("username", pojo.getUserName());
                        model.put("password", password);
                        mail.setModel(model);
                        mailService.sendEmail(mail, template);
                        return new ModelAndView("redirect:/admin/user/list.html");
                    }else {
                        //roleService.updateRole(pojo);
                        mav.addObject(Constant.MESSAGE_RESPONSE, Constant.REDIRECT_UPDATE);
                    }
                }
            }
            if (pojo != null && pojo.getId() != null) {
                command.setPojo(userService.findByUserId(pojo.getId()));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            mav.addObject(Constant.MESSAGE_RESPONSE, Constant.REDIRECT_ERROR);
        }
        referenceData(command);
        mav.addObject(Constant.FORM_MODEL_KEY, command);
        return mav;
    }

    private void referenceData(UserCommand command) {
        List<RoleDTO> listRole = roleService.findAllRole();
        command.setListRole(listRole);
    }
}
