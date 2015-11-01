package clinicmanager

class ReportController {

    def stockService

    def getAllReport() {
        List stockLevels = stockService.getStockLevels(false)
        render (view: "report", model: [stockLevels:stockLevels])
    }

    def getLowStockedReport(){
        List stockLevels = stockService.getStockLevels(true)
        render (view: "report", model: [stockLevels:stockLevels])
    }
}
