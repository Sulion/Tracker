package ru.shmoylova.tracker.web.controllers;

import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import ru.shmoylova.tracker.interfaces.beans.EmployeeSessionBeanLocal;

/**
 *
 * @author Ksiona
 */
@ManagedBean
@SessionScoped
public class SessionHolderBean {

    private static final String ERROR_LOGIN_PREFIX = "error_login_page_prefix";
    private static final String ERROR_LOGIN = "login_failed";
    private static final String BUNDLE_LOC = "ru.shmoylova.tracker.web.nls.messages";
    private static final String PAGE_INDEX = "index";
    private static final String PAGE_ERROR = "error";
    private int userID = 0;
    private String userLogin = null;
    private String userPassword = null;
    private String lastError = "";
    private MessagesController printer = null;
    ResourceBundle bundle;

    @EJB
    EmployeeSessionBeanLocal employeeBean;

    /**
     * Creates a new instance of SessionHolder
     */
    public SessionHolderBean() {
        printer = new MessagesController();
        bundle = ResourceBundle.getBundle(BUNDLE_LOC, FacesContext.getCurrentInstance().getViewRoot().getLocale());

    }

    public String processExit() {
        userLogin = null;
        userPassword = null;
        userID = 0;
        return PAGE_INDEX;
    }

    public String processLogin() {
        this.userID = employeeBean.processLogin(userLogin, userPassword);
        if (userID > 0) {
            return PAGE_INDEX;
        } else {
            this.lastError = bundle.getString(ERROR_LOGIN_PREFIX) + bundle.getString(ERROR_LOGIN);
            userLogin = null;
            userPassword = null;
            return PAGE_ERROR;
        }
    }

    public int getUserID() {
        return userID;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getLastError() {
        return lastError;
    }

    public void setLastError(String lastError) {
        this.lastError = lastError;
    }

    public void printLastError() {
        printer.printError(getLastError());
    }
}
