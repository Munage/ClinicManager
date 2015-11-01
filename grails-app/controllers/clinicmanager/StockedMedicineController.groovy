package clinicmanager

import org.springframework.dao.DataIntegrityViolationException

class StockedMedicineController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [stockedMedicineInstanceList: StockedMedicine.list(params), stockedMedicineInstanceTotal: StockedMedicine.count()]
    }

    def create() {
        [stockedMedicineInstance: new StockedMedicine(params)]
    }

    def save() {
        def stockedMedicineInstance = new StockedMedicine(params)
        if (!stockedMedicineInstance.save(flush: true)) {
            render(view: "create", model: [stockedMedicineInstance: stockedMedicineInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'stockedMedicine.label', default: 'StockedMedicine'), stockedMedicineInstance.id])
        redirect(action: "show", id: stockedMedicineInstance.id)
    }

    def show(Long id) {
        def stockedMedicineInstance = StockedMedicine.get(id)
        if (!stockedMedicineInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stockedMedicine.label', default: 'StockedMedicine'), id])
            redirect(action: "list")
            return
        }

        [stockedMedicineInstance: stockedMedicineInstance]
    }

    def edit(Long id) {
        def stockedMedicineInstance = StockedMedicine.get(id)
        if (!stockedMedicineInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stockedMedicine.label', default: 'StockedMedicine'), id])
            redirect(action: "list")
            return
        }

        [stockedMedicineInstance: stockedMedicineInstance]
    }

    def update(Long id, Long version) {
        def stockedMedicineInstance = StockedMedicine.get(id)
        if (!stockedMedicineInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stockedMedicine.label', default: 'StockedMedicine'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (stockedMedicineInstance.version > version) {
                stockedMedicineInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'stockedMedicine.label', default: 'StockedMedicine')] as Object[],
                        "Another user has updated this StockedMedicine while you were editing")
                render(view: "edit", model: [stockedMedicineInstance: stockedMedicineInstance])
                return
            }
        }

        stockedMedicineInstance.properties = params

        if (!stockedMedicineInstance.save(flush: true)) {
            render(view: "edit", model: [stockedMedicineInstance: stockedMedicineInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'stockedMedicine.label', default: 'StockedMedicine'), stockedMedicineInstance.id])
        redirect(action: "show", id: stockedMedicineInstance.id)
    }

    def delete(Long id) {
        def stockedMedicineInstance = StockedMedicine.get(id)
        if (!stockedMedicineInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stockedMedicine.label', default: 'StockedMedicine'), id])
            redirect(action: "list")
            return
        }

        try {
            stockedMedicineInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'stockedMedicine.label', default: 'StockedMedicine'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'stockedMedicine.label', default: 'StockedMedicine'), id])
            redirect(action: "show", id: id)
        }
    }
}
