package dev.paie.web.form.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dev.paie.entite.Entreprise;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.ProfilRemunerationRepository;
import dev.paie.repository.RemunerationEmployeRepository;
import dev.paie.web.form.model.RemunerationEmployeForm;

@Component
public class RemunerationEmployeValidator implements Validator {

	@Autowired
	GradeRepository gradeRepository;
	
	@Autowired
	EntrepriseRepository entrepriseRepository;
	
	@Autowired
	ProfilRemunerationRepository profilRemunerationRepository;
	
	@Autowired
	RemunerationEmployeRepository remunerationEmployeRepository;
	
	private static final String FIELD_MATRICULE = "matricule";
	private static final String FIELD_ENTREPRISE = "entreprise";
	private static final String FIELD_PROFIL_REMUNERATION = "profilRemuneration";
	private static final String FIELD_GRADE = "grade";
	
	@Override
	public boolean supports(Class<?> clazz) {
		return RemunerationEmployeForm.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		RemunerationEmployeForm ref = (RemunerationEmployeForm) obj;
		
		if(ref.getGrade()!= null && !gradeRepository.exists(ref.getGrade())){
			errors.rejectValue(FIELD_GRADE, "remunerationEmploye.grade.Notexist", "Cette grade n'existe pas");
		}
		
		Entreprise entreprise = null;
		
		if(ref.getEntreprise() != null) {
			entreprise = entrepriseRepository.findOne(ref.getEntreprise());
			
			if(entreprise == null){
				errors.rejectValue(FIELD_ENTREPRISE, "remunerationEmploye.entreprise.Notexist", "Cette entreprise n'existe pas");
			}
		}
		
		if(ref.getProfilRemuneration() != null && !profilRemunerationRepository.exists(ref.getProfilRemuneration())){
			errors.rejectValue(FIELD_PROFIL_REMUNERATION, "remunerationEmploye.profiRemuneration.Notexist", "Ce profil n'existe pas");
		}
		
		
		
		
		if(ref.getMatricule() != null) {
			RemunerationEmploye re = remunerationEmployeRepository.findByMatricule(ref.getMatricule().toUpperCase());
			if( re != null && entreprise != null && entreprise.getDenomination().equals(re.getEntreprise().getDenomination())){
				errors.rejectValue(FIELD_MATRICULE, "remunerationEmploye.matricule.exist", "Cet employé existe");
			}
		}
	}
	

}
