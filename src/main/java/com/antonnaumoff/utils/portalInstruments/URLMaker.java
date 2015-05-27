package com.antonnaumoff.utils.portalInstruments;


import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletURLFactoryUtil;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

public class URLMaker {

    public static PortletURL getRenderUrl(ActionRequest actionRequest) {
        String portletName = (String) actionRequest.getAttribute(WebKeys.PORTLET_ID);
        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
        return PortletURLFactoryUtil.create(PortalUtil.getHttpServletRequest(actionRequest), portletName,
                themeDisplay.getLayout().getPlid(), PortletRequest.RENDER_PHASE);

    }

}
