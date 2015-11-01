package clinicmanager

class StockService {

    private final int LOW_LEVEL = 5 //Low stock threshold

    /**
     * Checks the stock levels of medication and returns a list of clinics if stock levels are low.
     * @param inStock
     * @return
     */
    List getLowStockClinics(){
        def clinics = Clinic.getAll()
        Set warnings = []
        clinics.each {
            //Check if the stock level is low
            it.medication.each { stock ->
                if(stock.quantity < LOW_LEVEL){
                    warnings.add(it)
                }
            }
        }

        //Convert to list and sort
        List response = warnings.toList()
        response.sort { it.id }

        return response
    }

    /**
     * Checks the stock levels of medication and returns a list of warnings if stock levels are low.
     * @param inStock
     * @return
     */
    List checkStockLevels(List<StockedMedicine> inStock){
        List warnings = []
        inStock.each {
            //Check if the stock level is low
            if(it.quantity < LOW_LEVEL && it.quantity > 0){
                warnings.add(["message":it.clinic.title + " is low on " + it.medication.title, "style":"low"])
            }

            //check if the stock level is empty
            if(it.quantity == 0){
                warnings.add(["message":it.clinic.title + " is out of " + it.medication.title, "style":"empty"])
            }
        }

        return warnings
    }

    /**
     *
     * @return
     */
    def getAllStockLevels(){
        return StockedMedicine.getAll()
    }

    /**
     *
     * @param clinicId
     * @return
     */
    def getStockLevel(Long clinicId) {
        Clinic clinic = Clinic.findById(clinicId)
        return clinic.getMedication()
    }

    /**
     *
     * @param clinicId
     * @return
     */
    def getStockLevel(List clinicIds) {
        Clinic clinic = Clinic.findById(clinicId)
        return clinic.getMedication()
    }
    /**
     *
     * @param clinicId
     * @param medicationId
     * @return
     */
    int getStockLevel(Long clinicId, Long medicationId) {
        Clinic clinic = Clinic.findById(clinicId)
        return clinic.medication.each { if(it.id == medicationId) return it.quantity }
    }
}
