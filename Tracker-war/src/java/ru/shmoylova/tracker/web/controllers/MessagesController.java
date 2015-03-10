package ru.shmoylova.tracker.web.controllers;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

/**
 * Класс для вывода сообщений
 *
 * @author Ksiona
 */
public class MessagesController {

    public MessagesController() {

    }

    final public FacesMessage printMessage(String text, Severity level) {
        if (level == null) {
            level = FacesMessage.SEVERITY_ERROR;
        }
        FacesMessage fm = new FacesMessage(text);
        fm.setSeverity(level);
        FacesContext.getCurrentInstance().addMessage(null, fm);
        return fm;
    }

    final public void printError(String text) {
        printMessage(text, FacesMessage.SEVERITY_ERROR);
    }

    final public void printInfo(String text) {
        printMessage(text, FacesMessage.SEVERITY_INFO);
    }
}
