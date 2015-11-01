import clinicmanager.Clinic
import clinicmanager.Medication

class BootStrap {

    def init = { servletContext ->
        //Create medications if none are defined
        if(Medication.getAll().size() < 1) {
            Medication NEVIRAPINE = new Medication(title: "Nevirapine").save()
            Medication STAVUDINE = new Medication(title: "Stavudine").save()
            Medication ZIDOTABINE = new Medication(title: "Zidotabine").save()
        }

        //Create clinics if none are defined
        if(Clinic.getAll().size() < 1){
            for(int i = 1; i < 11; i++){
                Clinic clin = new Clinic(title: "Clinic-${i}").save()
            }
        }
    }
    def destroy = {
    }
}
