package pe.edu.upc.prestabooks.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.prestabooks.entity.Employee;
import pe.edu.upc.prestabooks.entity.User;
import pe.edu.upc.prestabooks.service.EmployeeService;
import pe.edu.upc.prestabooks.service.UserService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserService userService;

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, null, new CustomDateEditor(dateFormat, true));
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/new")
    public String newEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("user", new User());
        return "employee/employee";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/save")
    public String saveEmployee(@Valid Employee employee, BindingResult result1, @Valid User user, BindingResult result2,
            Model model) throws Exception {
        if (result1.hasErrors() || result2.hasErrors()) {
            return "employee/employee";
        } else {
            try {
                User userCreated = userService.registerNewEmployeeAccount(user);
                employee.setId(userCreated.getId());
                employeeService.create(employee);
                model.addAttribute("mensaje", "Se registró exitosamente!!");
                return "redirect:/employees/list";
            } catch (Exception e) {
                e.getStackTrace();
                System.err.println(e.getMessage());
            }

            return "redirect:/employees/list";
        }
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/list")
    public String listBook(@ModelAttribute("employeeSearch") Employee employeeSearch, Model model) {
        try {
            if (employeeSearch.getFirstName() != null) {
                model.addAttribute("listEmployees", employeeService.findByFirstNameLikeOrLastNameLike(employeeSearch.getFirstName()));
            } else {
                model.addAttribute("listEmployees", employeeService.getAll());
                model.addAttribute("employeeSearch", new Employee());
            }
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "employee/list";
    }

}
