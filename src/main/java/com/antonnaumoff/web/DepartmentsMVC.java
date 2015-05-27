package com.antonnaumoff.web;

import com.antonnaumoff.models.Department;
import com.antonnaumoff.service.DataService;
import com.antonnaumoff.utils.exceptions.DataBaseException;
import com.antonnaumoff.utils.forms.DepartmentForm;
import com.antonnaumoff.utils.portalInstruments.URLMaker;
import com.antonnaumoff.utils.validators.OvalValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.portlet.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

//Hello

@Controller
@RequestMapping(value = "VIEW")
public class DepartmentsMVC {

    @Autowired
    private DataService dataService;

    @Autowired
    private OvalValidator validator;

    private Logger logger = Logger.getLogger(this.getClass());

    @RenderMapping
    public String handleRenderRequest(RenderRequest renderRequest, RenderResponse renderResponse) throws DataBaseException {

        List<Department> list = dataService.getDepartmentList();
        renderRequest.setAttribute("message", "Some problem with database, please, try later");
        renderRequest.setAttribute("department", list);
        logger.info("Successfull departmentlist pick. Number of departments: " + list.size());
        return "departmentList";
    }

    @RenderMapping(params = "action=departmentList")
    public String handleListDepartments(RenderRequest renderRequest, RenderResponse renderResponse) throws DataBaseException {

        List<Department> list = dataService.getDepartmentList();
        renderRequest.setAttribute("department", list);
        logger.info("Successfull departmentlist pick. Number of departments: " + list.size());
        return "departmentList";
    }

    @RenderMapping(params = "action=createDepartment")
    public String handleDepartmentsCreationForm(ModelMap model, RenderRequest renderRequest, RenderResponse renderResponse) {
        DepartmentForm departmentForm = new DepartmentForm();
        model.put("departmentForm", departmentForm);
        logger.info("Department Form Handler. Rendering departmentFormFORM.jsp for Creating");
        return "departmentForm";
    }

    @ActionMapping(params = "action=validateCreation")
    public void departmentCreationValidation(ActionRequest actionRequest, ActionResponse actionResponse) throws DataBaseException, IOException {

        Department dep = createDepartmentFromView(actionRequest);
        Map result = validator.validate(dep);
        if (result.isEmpty()) {
            dataService.createDepartment(dep.getTitle());
            logger.info("Action Phase parameter validateCreation. Succesfull creating of Department");
            PortletURL redirectURL = URLMaker.getRenderUrl(actionRequest);
            redirectURL.setParameter("action", "departmentList");
            actionResponse.sendRedirect(redirectURL.toString());
        } else {
            dep.setId(0);
            actionRequest.setAttribute("department", dep);
            actionRequest.setAttribute("message", result.get("title"));
            actionResponse.setRenderParameter("action", "createDepartment");
        }
    }

    @RenderMapping(params = "action=editDepartment")
    public String handleDepartmentsEditingForm(RenderRequest renderRequest, RenderResponse renderResponse) throws DataBaseException {
        int id_dep = Integer.parseInt(renderRequest.getParameter("id_dep"));
        Department dep = dataService.getDepartmentById(id_dep);
        renderRequest.setAttribute("department", dep);
        logger.info("Department Form Handler. Rendering departmentForm for Editing with department " + dep.getTitle());
        return "departmentForm";
    }

    @ActionMapping(params="action=validateEditing")
    public void departmentEditionValidation(ActionRequest actionRequest, ActionResponse actionResponse) throws DataBaseException, IOException {
        Department dep =createDepartmentFromView(actionRequest);
        Map result = validator.validate(dep);
        if (result.isEmpty()) {
            dataService.editDepartment(dep);
            logger.info("Action Phase with parameter validateEditing. Successfull creating of Department");
            PortletURL redirectURL = URLMaker.getRenderUrl(actionRequest);
            redirectURL.setParameter("action", "departmentList");
            actionResponse.sendRedirect(redirectURL.toString());
        } else {
            actionRequest.setAttribute("department", dep);
            actionRequest.setAttribute("message", result.get("title"));
            actionResponse.setRenderParameter("action", "createDepartment");
        }
    }

    @ActionMapping(params="action=deleteDepartment")
    public void handleDepartmentDeleting(ActionRequest actionRequest, ActionResponse actionResponse) throws DataBaseException, IOException {
        dataService.deleteDepartment(Integer.parseInt(actionRequest.getParameter("id_dep")));
        logger.info("Action Phase with parameter deleteDepartment. Successfull deleting");
        PortletURL redirectURL = URLMaker.getRenderUrl(actionRequest);
        redirectURL.setParameter("action", "departmentList");
        actionResponse.sendRedirect(redirectURL.toString());
    }

    @ExceptionHandler(DataBaseException.class)
    public ModelAndView handleDataBaseException(DataBaseException e) {
        ModelAndView modelAndView = new ModelAndView("error");
        logger.error("Exception while database operation", e);
        modelAndView.addObject("message", "Some problem with database, please, try later");
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleDataBaseException(Exception e) {
        ModelAndView modelAndView = new ModelAndView("error");
        logger.error("Exception detected", e);
        modelAndView.addObject("message", "Some problem with database, please, try later");
        return modelAndView;
    }

    private Department createDepartmentFromView(ActionRequest actionRequest) {
        Department dep = new Department();
        dep.setTitle(actionRequest.getParameter("title").trim());
        dep.setId(Integer.parseInt(actionRequest.getParameter("id_dep")));
        return dep;
    }
}


