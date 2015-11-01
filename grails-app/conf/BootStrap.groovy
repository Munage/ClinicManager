import clinicmanager.Clinic
import clinicmanager.Medication
import clinicmanager.StockedMedicine

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
                Clinic clin = new Clinic(title: "Clinic-${i}", country: "South Africa").save(flush: true)
            }

            //add stocks
            Clinic.getAll().each {
                it.addToMedication(new StockedMedicine(medication: Medication.findById(1), quantity: 10)).save()
                it.addToMedication(new StockedMedicine(medication: Medication.findById(2), quantity: 10)).save()
                it.addToMedication(new StockedMedicine(medication: Medication.findById(3), quantity: 10)).save(flush: true)
            }
        }

    }
    def destroy = {
    }
}
