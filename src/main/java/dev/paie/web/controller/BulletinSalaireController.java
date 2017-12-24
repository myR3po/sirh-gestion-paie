package dev.paie.web.controller;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import dev.paie.web.form.model.BulletinSalaireForm;
import dev.paie.web.form.validator.BulletinSalaireFormValidator;

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
	
	@Autowired
	private BulletinSalaireFormValidator bulletinSalaireFormValidator;
	   
	@InitBinder("BulletinSalaireForm")
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(bulletinSalaireFormValidator);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	public ModelAndView creerBulletin() {
		ModelAndView mv = getCreerBulletinForm();
		mv.addObject("bulletin", new BulletinSalaireForm());
		return mv;
	}
	
	@PostMapping("/creer")
	public ModelAndView creerBulletin(@Valid @ModelAttribute("bulletin") BulletinSalaireForm bsf, BindingResult results) {
		
		ModelAndView mv = new ModelAndView();
		if(results.hasErrors()) {
			mv = getCreerBulletinForm();
		}else {
		
			BulletinSalaire bulletinSalaire = new BulletinSalaire();
			bulletinSalaire.setPeriode(periodeRepository.findOne(bsf.getPeriode()));
			bsf.setPrimeExceptionnelle(bsf.getPrimeExceptionnelle() == null || bsf.getPrimeExceptionnelle().isEmpty()? "0" : bsf.getPrimeExceptionnelle()); 
			bulletinSalaire.setPrimeExceptionnelle(new BigDecimal(bsf.getPrimeExceptionnelle()));
			
			bulletinSalaire.setRemunerationEmploye(remunerationEmployeRepository.findByMatricule(bsf.getMatricule()));
			
			bulletinSalaireRepository.save(bulletinSalaire);
			mv.setViewName("redirect:lister");
		}		
		return mv;
	}
	
	@RequestMapping(path = "/lister")	
	public String listerBulletin(Model model) {
		model.addAttribute("bulletins", calculerRemunerationService.calculerForAll());
		return "bulletins/listerBulletin";

	}
	
	
	@GetMapping("/visualiser")	
	public ModelAndView visualiserBulletin(@RequestParam("id") Integer id) throws Exception {
		
		if(id == null) {
			throw new Exception("Ce bulletin de salaire n'existe pas");
		}
		
		if(!bulletinSalaireRepository.exists(id)) {
			throw new Exception("Ce bulletin de salaire n'existe pas");
		};
			
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/visualiserBulletin");
		mv.addObject("bulletinView", calculerRemunerationService.calculerForView(id));
		return mv;
	}
	
	private ModelAndView getCreerBulletinForm() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/creerBulletin");
		mv.addObject("employeList", remunerationEmployeRepository.findAll());
		mv.addObject("periodeList", periodeRepository.findAll());
		return mv;
	}
	
	
}