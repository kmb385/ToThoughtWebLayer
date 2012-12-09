package org.tothought.spring.controllers.resume;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tothought.entities.Degree;
import org.tothought.entities.DegreeDetail;
import org.tothought.repositories.DegreeDetailRepository;
import org.tothought.repositories.DegreeRepository;
import org.tothought.spring.propertyeditors.DegreeDetailTypeEditor;

@Controller
@RequestMapping("/secure/resume/manager/degree")
public class SecureDegreeController {

	@Autowired
	DegreeRepository repository;
	
	@Autowired
	DegreeDetailRepository detailRepository;
	
	@RequestMapping("/")
	public String getDegrees(){
		return "degrees";
	}
	
	@RequestMapping("/new")
	public String createDegree(Model model){
		model.addAttribute("degree", new Degree());
		return "/resume/manager/manageDegree";
	}
	
	@RequestMapping("/edit/{degreeId}")
	public String editDegree(@PathVariable Integer degreeId, Model model){
		model.addAttribute("degree", repository.findOne(degreeId));
		return "/resume/manager/manageDegree";
	}
	
	@RequestMapping("/save")
	public String saveDegree(@ModelAttribute Degree degree){
		repository.save(degree);
		return "redirect:/resume/degree";
	}
	
	@RequestMapping("/{detailId}/deletedetail")
	public String deleteExperience(@PathVariable("detailId") Integer detailId, Model model){
		DegreeDetail degreeDetail = detailRepository.findOne(detailId);
		Degree degree = degreeDetail.getDegree();
		
		List<DegreeDetail> details = degree.getDegreeDetails();
		details.remove(degreeDetail);
		repository.save(degree);
		
		return "redirect:/secure/resume/manager/degree/edit/" + degree.getDegreeId().toString();
	}
	
	@RequestMapping("/delete/{degreeId}")
	public String deleteDegree(@PathVariable Integer degreeId){
		repository.delete(degreeId);
		return "redirect:/resume/degree";
	}

	/**
	 * Sets a binder to handle the conversion of the file.
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinderAll(WebDataBinder binder) {
		   SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		   dateFormat.setLenient(false);

		// true passed to CustomDateEditor constructor means convert empty String to null
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(DegreeDetail.class, new DegreeDetailTypeEditor(detailRepository));
	}

}
