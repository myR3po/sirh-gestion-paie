package dev.paie.web.form.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.RemunerationEmployeRepository;
import dev.paie.web.form.model.BulletinSalaireForm;

@Component
public class BulletinSalaireFormValidator implements Validator {

	@Autowired
	RemunerationEmployeRepository remunerationEmployeRepository;
	
	@Autowired
	PeriodeRepository periodeRepository;
	
	
	private static final String FIELD_MATRICULE = "matricule";
	private static final String FIELD_PRIME_EXCEPTIONNELLE = "primeExceptionnelle";
	private static final String FIELD_PERIODE = "periode";
	
	@Override
	public boolean supports(Class<?> clazz) {
		return BulletinSalaireForm.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {

		BulletinSalaireForm bsf = (BulletinSalaireForm) obj;

		if(bsf.getPrimeExceptionnelle() != null && !bsf.getPrimeExceptionnelle().isEmpty() && !bsf.getPrimeExceptionnelle().matches("^[0-9]+(\\.[0-9]{0,2}|)$")) {
			errors.rejectValue(FIELD_PRIME_EXCEPTIONNELLE, "bulletinSalaire.primeExceptionnelle.invalid", "Format invalide");
		}
		
		if(bsf.getPeriode() != null && periodeRepository.findOne(bsf.getPeriode()) == null) {
			errors.rejectValue(FIELD_PERIODE, "bulletinSalaire.periode.Notexist", "Aucune periode ne correspond à votre sélection");
		}
		if(bsf.getMatricule() != null && !bsf.getMatricule().isEmpty() && remunerationEmployeRepository.findByMatricule(bsf.getMatricule().toUpperCase()) == null){
			errors.rejectValue(FIELD_MATRICULE, "bulletinSalaire.matricule.Notexist", "Aucun employé ne correspond à votre sélection");
		}
	}
	

}
