package clinicmanager

class StockedMedicine {

    static belongsTo = [clinic:Clinic]

    Medication medication
    int quantity = 0

    static constraints = {
        medication nullable: false
        quantity min: 0
    }
}
