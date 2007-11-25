package com.liferay.portlet.atlassian.jira.action;

import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.PortletRequestUtils;
import org.springframework.web.portlet.mvc.AbstractController;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * Save the preferences for this portlet and redisplays the preferences.
 *
 * @author Jian Cao
 * @version $Revision$
 */
public class CreateTicketPreferencesController
    extends AbstractController {

     protected ModelAndView handleRenderRequestInternal(final RenderRequest req,
                                                       final RenderResponse response)
        throws Exception {
        ModelAndView modelView =
            new ModelAndView(JiraPortletConstants.PREFS_VIEW);
        PortletPreferences prefs = req.getPreferences();
        final String url = prefs.getValue(JiraPortletConstants.URL_PREFERENCE,
                                          "");
        final String userName = prefs.getValue(
            JiraPortletConstants.USER_NAME_PREFERENCE, "");
        final String password = prefs.getValue(
            JiraPortletConstants.PASSWORD_PREFERENCE, "");
        modelView.addObject(JiraPortletConstants.URL_PREFERENCE, url);
        modelView.addObject(JiraPortletConstants.USER_NAME_PREFERENCE,
                            userName);
        modelView.addObject(JiraPortletConstants.PASSWORD_PREFERENCE, password);
        return modelView;
    }
    
    protected void handleActionRequestInternal(final ActionRequest request,
                                               final ActionResponse response)
        throws Exception {
        final PortletPreferences prefs = request.getPreferences();
        final String url =
            PortletRequestUtils.getStringParameter(request,
                                                   JiraPortletConstants.URL_PREFERENCE);
        prefs.setValue(JiraPortletConstants.URL_PREFERENCE, url);

        final String userName =
            PortletRequestUtils.getStringParameter(request,
                                                   JiraPortletConstants.USER_NAME_PREFERENCE);
        prefs.setValue(JiraPortletConstants.USER_NAME_PREFERENCE, userName);

        final String password =
            PortletRequestUtils.getStringParameter(request,
                                                   JiraPortletConstants.PASSWORD_PREFERENCE);
        prefs.setValue(JiraPortletConstants.PASSWORD_PREFERENCE, password);

        prefs.store();

        final PortletSession session = request.getPortletSession();
        session.removeAttribute(JiraPortletConstants.PROXY_KEY);
        session.removeAttribute(JiraPortletConstants.SECURITY_TOKEN_KEY);
        session.removeAttribute(JiraPortletConstants.MODE_KEY);


    }
}