package vn.hcmute.web.logic.validator;

import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import vn.hcmute.web.logic.command.UserCommand;

/**
 * Created by Admin on 23/6/2017.
 */
@Component
public class UserValidator extends ApplicationObjectSupport implements Validator {
    public boolean supports(Class<?> aClass) {
        return false;
    }
    public void validate(Object o, Errors errors) {
        UserCommand command = (UserCommand) o;
        checkRequireField(command, errors);
    }

    private void checkRequireField(UserCommand command, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pojo.userName", "NotEmpty");
    }
}
