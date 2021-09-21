package pe.edu.upc.prestabooks.controller;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
            Model model,  RedirectAttributes redirectAttributes) throws Exception {
        if (result1.hasErrors() || result2.hasErrors()) {
            return "employee/employee";
        } else {
            try {
                User userCreated = userService.registerNewEmployeeAccount(user);
                employee.setId(userCreated.getId());
                employeeService.create(employee);
                redirectAttributes.addFlashAttribute("mensaje", "Se registró el empleado satisfactoriamente.");
                return "redirect:/employees/list";
            } catch (Exception e) {
                e.getStackTrace();
                System.err.println(e.getMessage());
            }

            return "redirect:/employees/list";
        }
    }
    @PostMapping("/update")
    public String updateEmployee(@Valid Employee employee, BindingResult result1,
            Model model, RedirectAttributes redirectAttributes) throws Exception {
        if (result1.hasErrors()) {
            return "employee/updateEmployee";
        } else {
            try {
                //User userCreated = userService.registerNewEmployeeAccount(user);
                //employee.setId(userCreated.getId());
                employeeService.create(employee);
                redirectAttributes.addFlashAttribute("mensaje", "Se modificó el empleado satisfactoriamente.");

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
    @RequestMapping("/delete")
	public String deleteBook(Map<String, Object> model, @RequestParam(value = "id") Integer id, RedirectAttributes redirectAttributes) {
		try {
			if (id != null && id > 0) {
				employeeService.deleteById(id);
				redirectAttributes.addFlashAttribute("mensaje", "Se eliminó el empleado correctamente.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "Ocurrió un error");
		}
		return "redirect:/employees/list";
	}
    
 // @Secured("ROLE_ADMIN")
 	@GetMapping("/detalle/{id}")
 	public String viewEmployee(@PathVariable(value = "id") int id, Model model, RedirectAttributes redirectAttributes) {
 		try {
 			Optional<Employee> employee = employeeService.findById(id);
 			model.addAttribute("listEmployees", employeeService.getAll());
 			if (!employee.isPresent()) {
                redirectAttributes.addFlashAttribute("mensaje", "El empleado no existe.");
 				return "redirect:/employees/list";
 			} else {
 				model.addAttribute("employee", employee.get());
 				return "employee/updateEmployee";
 			}
 		} catch (Exception e) {
 			System.out.println(e.getMessage());
 		}
 		return "employee/updateEmployee";
 	}

}
