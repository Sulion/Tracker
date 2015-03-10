package ru.shmoylova.tracker.web.validators;

import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("ru.shmoylova.tracker.web.validators.LoginValidator")
public class LoginValidator implements Validator {

    private static final String BUNDLE_LOC = "ru.shmoylova.tracker.web.nls.messages";
    private static final String LOGIN_COMPONENT_NAME = "userLogin";
    private static final String ERROR_PASS_REQUIRED = "pass_required";
    private static final String ERROR_LOGIN_LENGTH = "login_length_error";
    private static final String ERROR_LOGIN_FIRST_LETTER = "first_letter_error";

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_LOC, FacesContext.getCurrentInstance().getViewRoot().getLocale());

        try {
            String newValue = value.toString();

            EditableValueHolder holder = (EditableValueHolder) component.getParent().findComponent(LOGIN_COMPONENT_NAME);
            String login = holder.getValue().toString();

            if (login == null || login.isEmpty() || newValue == null
                    || newValue.isEmpty()) {
                return;
            }

            if (newValue.length() < 1) {
                throw new IllegalArgumentException(bundle.getString(ERROR_PASS_REQUIRED));
            }

            if (login.length() < 5) {
                throw new IllegalArgumentException(bundle.getString(ERROR_LOGIN_LENGTH));
            }

            if (!Character.isLetter(login.charAt(0))) {
                throw new IllegalArgumentException(bundle.getString(ERROR_LOGIN_FIRST_LETTER));
            }

        } catch (IllegalArgumentException e) {
            FacesMessage message = new FacesMessage(e.getMessage());
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }

    }
}
