package com.dhruv.cvt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dhruv.cvt.model.LocData;
import com.dhruv.cvt.services.CoronaVirusService;

@Controller
public class BaseController {
	
	@Autowired
	CoronaVirusService coronaVirusService;

	@GetMapping("/")
	public String Home(Model model) {
		List<LocData> allStats = coronaVirusService.getAllData();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);
		return "home";
	}
}
