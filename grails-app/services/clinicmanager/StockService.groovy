package clinicmanager

class StockService {

    private final int LOW_LEVEL = 5 //Low stock threshold

    /**
     * Checks the stock levels of medication and returns a list of clinics with warnings if stock levels are low.
     * @param inStock
     * @return
     */
    List getLowStockWarnings(){
        def clinics = getLowStockClinics()
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
     * Returns a list of clinics with low stock levels
     * @return
     */
    List getLowStockClinics(){
        def clinics = Clinic.getAll()

        Set lowClinics = []
        clinics.each {
            //Check if the stock level is low
            it.medication.each { stock ->
                if(stock.quantity < LOW_LEVEL){
                    lowClinics.add(it)
                }
            }
        }

        //Convert to list and sort
        List response = lowClinics.toList()
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
     * Returns a list of clinics and their stock levels
     * @param lowOnly
     * @return
     */
    def getStockLevels(boolean lowOnly){
        def clinics
        if(lowOnly){
            clinics = getLowStockClinics()
        } else {
            clinics= Clinic.getAll()
        }

        def stocks = []


        clinics.each {
            def report = [:]
            report.put("clinic", it.title)
            it.medication.each {
                if(it.medication.title == "Nevirapine"){
                    report.put("Nevirapine", it.quantity)
                }

                if(it.medication.title == "Stavudine"){
                    report.put("Stavudine", it.quantity)
                }

                if(it.medication.title == "Zidotabine"){
                    report.put("Zidotabine", it.quantity)
                }
            }

            stocks.add(report)
        }

        return stocks
    }
}
