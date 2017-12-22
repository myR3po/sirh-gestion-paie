package dev.paie.web.controller;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import dev.paie.entite.BulletinSalaire;
import dev.paie.repository.BulletinSalaireRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.RemunerationEmployeRepository;
import dev.paie.service.CalculerRemunerationService;

@Controller
@RequestMapping("/bulletins")
public class BulletinSalaireController {
	
	@Autowired
	BulletinSalaireRepository bulletinSalaireRepository;
	
	@Autowired
	RemunerationEmployeRepository remunerationEmployeRepository;
	
	@Autowired
	PeriodeRepository periodeRepository;
	
	@Autowired
	CalculerRemunerationService calculerRemunerationService;
	
	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	public ModelAndView creerBulletin() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/creerBulletin");
		mv.addObject("bulletin", new BulletinSalaire());
		mv.addObject("employeList", remunerationEmployeRepository.findAll());
		mv.addObject("periodeList", periodeRepository.findAll());
		return mv;
	}
	
	@PostMapping("/creer")
	public String creerBulletin(@RequestParam("primeExceptionnelle") String prime, @RequestParam("periode") Integer periode, @RequestParam("remunerationEmploye") Integer matricule) {
		BulletinSalaire bulletinSalaire = new BulletinSalaire();
		bulletinSalaire.setPeriode(periodeRepository.findOne(periode));
		
		bulletinSalaire.setPrimeExceptionnelle(new BigDecimal(prime));
		
		bulletinSalaire.setRemunerationEmploye(remunerationEmployeRepository.findOne(matricule));
		
		bulletinSalaireRepository.save(bulletinSalaire);		
		return "redirect:lister";
	}
	
	@RequestMapping(path = "/lister")	
	public String listerBulletin(Model model) {
		model.addAttribute("bulletins", calculerRemunerationService.calculerForAll());
		return "bulletins/listerBulletin";

	}
	
	
	@GetMapping("/visualiser")	
	public ModelAndView visualiserBulletin(@RequestParam("id") Integer id) {
		
		if(!bulletinSalaireRepository.exists(id)) {
			// throw new Exception();
		};
			
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/visualiserBulletin");
		
		mv.addObject("bulletinView", calculerRemunerationService.calculerForView(id));
		return mv;

	}
	
}
