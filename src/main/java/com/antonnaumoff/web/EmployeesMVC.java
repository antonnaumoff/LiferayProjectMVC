package com.antonnaumoff.web;

import com.antonnaumoff.models.Employee;
import com.antonnaumoff.service.DataService;
import com.antonnaumoff.utils.exceptions.DataBaseException;
import com.antonnaumoff.utils.parsers.RequestParser;
import com.antonnaumoff.utils.portalInstruments.URLMaker;
import com.antonnaumoff.utils.validators.OvalValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.portlet.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "VIEW")
public class EmployeesMVC {

    @Autowired
    private DataService dataService;

    @Autowired
    private OvalValidator validator;

    private Logger logger = Logger.getLogger(this.getClass());

    @RenderMapping(params = "action=listEmployees")
    public String handleListEmployees(RenderRequest renderRequest, RenderResponse renderResponse) throws DataBaseException {
        int id_dep;
        if (renderRequest.getAttribute("id_dep") == null) {
            id_dep = Integer.parseInt(renderRequest.getParameter("id_dep"));
        } else {
            id_dep = (Integer) renderRequest.getAttribute("id_dep");
        }
        List emp = dataService.getEmloyeeListById(id_dep);
        renderRequest.setAttribute("id_dep", id_dep);
        renderRequest.setAttribute("list", emp);
        logger.info("Render listEmployees with parameter id_dep: " + id_dep + " and number of Employees: " + emp.size());
        return "employeeList";
    }

    @RenderMapping(params = "action=createEmployee")
    public String handleEmployeeCreateForm(RenderRequest renderRequest, RenderResponse renderResponse) throws DataBaseException {
        if (renderRequest.getAttribute("id_dep") == null) {
            renderRequest.setAttribute("id_dep", renderRequest.getParameter("id_dep"));
        }
        logger.info("Render Phase with parameter createEmployee");
        return "employeeForm";
    }

    @ActionMapping(params = "action=validateEmpCreation")
    public void employeeCreationValidation(ActionRequest actionRequest, ActionResponse actionResponse) throws DataBaseException, IOException {
        Employee emp = createEmployeeFromView(actionRequest);
        Map result = validator.validate(emp);
        if (result.isEmpty()) {
            dataService.createEmployee(emp);
            String id_dep = String.valueOf(emp.getDep_id());
            PortletURL redirectURL = URLMaker.getRenderUrl(actionRequest);
            redirectURL.setParameter("id_dep", id_dep);
            redirectURL.setParameter("action", "listEmployees");
            logger.info("Action Phase with parameter validateEmpCreation and id_dep= "+id_dep+" Successfull creating of Employee");
            actionResponse.sendRedirect(redirectURL.toString());
        } else {
            actionRequest.setAttribute("id_dep", emp.getDep_id());
            actionRequest.setAttribute("emp", emp);
            actionRequest.setAttribute("messages", result);
            actionResponse.setRenderParameter("action", "createEmployee");

        }
    }

    @RenderMapping(params = "action=editEmployee")
    public String handleEmployeeEditForm(RenderRequest renderRequest, RenderResponse renderResponse) throws DataBaseException {
        int id = Integer.parseInt(renderRequest.getParameter("id"));
        renderRequest.setAttribute("editor", 1);
        renderRequest.setAttribute("emp", dataService.getEmloyeeById(id));
        renderRequest.setAttribute("id_dep", dataService.getId_dById(id));
        logger.info("Render Phase with parameter editEmployee and id_dep= "+dataService.getId_dById(id));
        return "employeeForm";
    }

    @ActionMapping(params = "action=validateEmpEditing")
    public void employeeEditingValidation(ActionRequest actionRequest, ActionResponse actionResponse) throws DataBaseException, IOException {
        Employee emp = createEmployeeFromView(actionRequest);
        emp.setId(Integer.parseInt(actionRequest.getParameter("id")));
        Map result = validator.validate(emp);
        if (result.isEmpty()) {
            dataService.editEmployee(emp);
            String id_dep = String.valueOf(emp.getDep_id());
            PortletURL redirectURL = URLMaker.getRenderUrl(actionRequest);
            redirectURL.setParameter("id_dep", id_dep);
            redirectURL.setParameter("action", "listEmployees");
            logger.info("Action Phase with parameter validateEmpEditing and id_dep= "+id_dep+" Successfull editing of Employee");
            actionResponse.sendRedirect(redirectURL.toString());
        } else {
            actionRequest.setAttribute("editor", 1);
            actionRequest.setAttribute("id_dep", emp.getDep_id());
            actionRequest.setAttribute("emp", emp);
            actionRequest.setAttribute("messages", result);
            actionResponse.setRenderParameter("action", "createEmployee");
        }
    }

    @ActionMapping(params = "action=deleteEmployee")
    public void handleEmployeeDeleting(ActionRequest actionRequest, ActionResponse actionResponse) throws DataBaseException, IOException {
        int id_dep = dataService.getId_dById(Integer.parseInt(actionRequest.getParameter("id")));
        dataService.deleteById(Integer.parseInt(actionRequest.getParameter("id")));
        PortletURL redirectURL = URLMaker.getRenderUrl(actionRequest);
        redirectURL.setParameter("id_dep", String.valueOf(id_dep));
        redirectURL.setParameter("action", "listEmployees");
        logger.info("Action Phase with parameter deleteEmployee and id_dep= "+id_dep+" Successfull deleting of Employee");
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

    private Employee createEmployeeFromView(ActionRequest actionRequest) {
        Employee emp = new Employee();
        emp.setJob_title(actionRequest.getParameter("job_title"));
        emp.setFirst_name(actionRequest.getParameter("first_name"));
        emp.setSecond_name(actionRequest.getParameter("second_name"));
        int salary = RequestParser.parseIntWithDefaultValue(actionRequest, "salary");
        emp.setSalary(salary);
        emp.setDate(RequestParser.parseDateDeafultValue(actionRequest, "date"));
        emp.setDep_id(Integer.parseInt(actionRequest.getParameter("id_dep")));
        emp.setId(0);
        return emp;
    }
}
