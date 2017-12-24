package dev.paie.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.ProfilRemunerationRepository;
import dev.paie.repository.RemunerationEmployeRepository;
import dev.paie.web.form.model.RemunerationEmployeForm;
import dev.paie.web.form.validator.RemunerationEmployeValidator;

@Controller
@RequestMapping("/employes")
public class RemunerationEmployeController {
	
	@Autowired
	GradeRepository gradeRepository;
	
	@Autowired
	EntrepriseRepository entrepriseRepository;
	
	@Autowired
	ProfilRemunerationRepository profilRemunerationRepository;
	
	@Autowired
	RemunerationEmployeRepository remunerationEmployeRepository;
	
	@Autowired
	private RemunerationEmployeValidator remunerationEmployeValidator;
	   
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(remunerationEmployeValidator);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	public ModelAndView creerEmploye() {
		ModelAndView mv = getCreerEmployeForm();
		mv.addObject("remunerationEmploye", new RemunerationEmployeForm());
		return mv;
	}
	
	//@RequestMapping(method = RequestMethod.POST, path = "/creer")
	@PostMapping("/creer")
	public ModelAndView creerEmploye(@Valid @ModelAttribute("remunerationEmploye") RemunerationEmployeForm ref, BindingResult results) {
		ModelAndView mv = new ModelAndView();
		if(results.hasErrors()) {
			mv = getCreerEmployeForm();
		}else {
			RemunerationEmploye remunerationEmploye = new RemunerationEmploye();
			remunerationEmploye.setMatricule(ref.getMatricule().toUpperCase());
			
			remunerationEmploye.setEntreprise((new Entreprise()));
			remunerationEmploye.getEntreprise().setId(ref.getEntreprise());
			
			remunerationEmploye.setProfilRemuneration(new ProfilRemuneration());
			remunerationEmploye.getProfilRemuneration().setId(ref.getProfilRemuneration());

			remunerationEmploye.setGrade(new Grade());
			remunerationEmploye.getGrade().setId(ref.getGrade());
			
			remunerationEmployeRepository.save(remunerationEmploye);
			mv.setViewName("redirect:lister");
		}
		
		return mv;
	}
	
	@RequestMapping(path = "/lister")	
	public String listerEmploye(Model model) {
		model.addAttribute("employes", remunerationEmployeRepository.findAll());
		return "employes/listerEmploye";
	}
	
	private ModelAndView getCreerEmployeForm() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/creerEmploye");
		mv.addObject("gradeList", gradeRepository.findAll());
		mv.addObject("entrepriseList", entrepriseRepository.findAll());
		mv.addObject("profilList", profilRemunerationRepository.findAll());
		return mv;
	}
	
}
