package vn.hcmute.web.logic.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import vn.hcmute.web.logic.command.RoleCommand;

/**
 * Created by Admin on 20/6/2017.
 */
@Component
public class RoleValidator extends ApplicationObjectSupport implements Validator {

    public boolean supports(Class<?> aClass) {
        return false;
    }

    public void validate(Object o, Errors errors)
    {
        RoleCommand command  = (RoleCommand) o;
        checkRequiredField(errors);
        trimFields(command);
    }

    private void trimFields(RoleCommand command) {
        if (StringUtils.isNotBlank(command.getPojo().getName())) {
            command.getPojo().setName(command.getPojo().getName().trim());
        }
        if (StringUtils.isNotBlank(command.getPojo().getCode())) {
            command.getPojo().setCode(command.getPojo().getCode().trim());
        }
    }

    private void checkRequiredField(Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pojo.name", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pojo.code", "NotEmpty");
    }
}
