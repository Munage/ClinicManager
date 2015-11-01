package clinicmanager

class Clinic {
    String title
    static hasMany = [medication:StockedMedicine]

    //Additional fields should be placed here
    //Employees
    //Address
    //Contact information

    static constraints = {
        title blank: false, nullable: false
        medication nullable: true
    }
}
