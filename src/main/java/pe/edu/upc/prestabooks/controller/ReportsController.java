package pe.edu.upc.prestabooks.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.prestabooks.service.LoanService;

@Controller
@RequestMapping("/reports")
public class ReportsController {
    @Autowired
	private LoanService loanService;

    @RequestMapping()
	public String Report()
	{
		return "reports/reports";
	}
	@RequestMapping("/report1")
	public String report1(Map<String, Object> model) {
		model.put("listReport1", loanService.listReport1());
		return "reports/report1";
	}
	@RequestMapping("/report2")
	public String report2(Map<String, Object> model) {
		model.put("listReport2", loanService.listReport2());
		return "reports/report2";
	}
	@RequestMapping("/report3")
	public String report3(Map<String, Object> model) {
		model.put("listReport3", loanService.listReport3());
		return "reports/report3";
	}
}
