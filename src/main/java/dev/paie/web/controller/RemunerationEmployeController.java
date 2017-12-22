package dev.paie.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.ProfilRemunerationRepository;
import dev.paie.repository.RemunerationEmployeRepository;

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
	
	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	public ModelAndView creerEmploye() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/creerEmploye");
		mv.addObject("remunerationEmploye", new RemunerationEmploye());
		mv.addObject("gradeList", gradeRepository.findAll());
		mv.addObject("entrepriseList", entrepriseRepository.findAll());
		mv.addObject("profilList", profilRemunerationRepository.findAll());
		return mv;
	}
	
	//@RequestMapping(method = RequestMethod.POST, path = "/creer")
	@PostMapping("/creer")
	public String creerEmploye(@RequestParam("matricule") String matricule, @RequestParam("entreprise") Integer entrepriseId,  @RequestParam("grade") Integer gradeId,  @RequestParam("profilRemuneration") Integer profilId) {
		RemunerationEmploye remunerationEmploye = new RemunerationEmploye();
		
		remunerationEmploye.setEntreprise(entrepriseRepository.findOne(entrepriseId));
		remunerationEmploye.setGrade(gradeRepository.findOne(gradeId));
		remunerationEmploye.setProfilRemuneration(profilRemunerationRepository.findOne(profilId));
		remunerationEmploye.setMatricule(matricule);
		
		remunerationEmployeRepository.save(remunerationEmploye);		
		return "redirect:lister";
	}
	
	@RequestMapping(path = "/lister")	
	public String listerEmploye(Model model) {
		model.addAttribute("employes", remunerationEmployeRepository.findAll());
		return "employes/listerEmploye";

	}
	
}
