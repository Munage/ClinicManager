package clinicmanager

class Clinic {
    String title
    String country
    static hasMany = [medication:StockedMedicine]

    //Additional fields should be placed here
    //Employees
    //Address
    //Contact information

    static constraints = {
        title blank: false, nullable: false
        title blank: true, nullable: true
        medication nullable: true
    }
}
