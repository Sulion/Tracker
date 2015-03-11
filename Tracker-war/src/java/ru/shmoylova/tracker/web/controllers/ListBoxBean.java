package ru.shmoylova.tracker.web.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author adminko
 */
@ManagedBean
@RequestScoped
public class ListBoxBean {

    private static final String BUNDLE_LOC = "ru.shmoylova.tracker.web.nls.content";
    private final ResourceBundle bundle;
    private String actionList;
    private List<String> actionListItems;

    /**
     * Creates a new instance of ListBoxBean
     */
    public ListBoxBean() {
        bundle = ResourceBundle.getBundle(BUNDLE_LOC, FacesContext.getCurrentInstance().getViewRoot().getLocale());
    }

    public List<String> getActionListItems() {
        actionListItems = new ArrayList<>();
        for (Iterator<String> it = bundle.keySet().iterator(); it.hasNext();) {
            String item = it.next();
            if (item.startsWith("action_listBox")) {
                actionListItems.add(bundle.getString(item));
            }
        }
        return actionListItems;
    }

    public void setActionListItems(List<String> actionListItems) {
        this.actionListItems = actionListItems;
    }

    public String getActionList() {
        return actionList;
    }

    public void setActionList(String actionList) {
        this.actionList = actionList;
    }

    public void performAction(ValueChangeEvent event) {
        String selected = (String) event.getNewValue();
        FacesContext context = FacesContext.getCurrentInstance();
        for (String action : actionListItems) {
            if (selected.equals(action)) {
                context.getApplication().getNavigationHandler().handleNavigation(context, "empList", action);
            }
        }
    }

}
