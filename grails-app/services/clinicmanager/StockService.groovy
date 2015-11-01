package clinicmanager

class StockService {

    final int LOW_LEVEL = 5 //Low stock threshold

    /**
     * Checks the stock levels of medication and returns a list of warnings if stock levels are low.
     * @param inStock
     * @return
     */
    def checkStockLevels(StockedMedicine inStock){
        List warnings = []
        inStock.each {
            //Check if the stock level is low
            if(it.quantity < LOW_LEVEL && it.quantity > 0){
                warnings.add(it.clinic.title + " is low on " + it.medication.title)
            }

            //check if the stock level is empty
            if(it.quantity == 0){
                warnings.add(it.clinic.title + " is out of " + it.medication.title)
            }
        }

        return warnings
    }

    def getAllStockLevels(){

    }

    def getStockLevel(Long clinicId) {

    }

    def getStockLevel(Long clinicId, Long medicationId) {

    }

    def getLowStocks(){

    }

    def getLowStocks(Long medicationId){

    }
}
