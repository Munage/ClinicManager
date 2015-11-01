package clinicmanager

class Medication {

    String title

    //Additional fields should be placed here
    //String usageInstructions
    //String additionalInformation

    static constraints = {
        title blank: false, nullable: false
    }
}
