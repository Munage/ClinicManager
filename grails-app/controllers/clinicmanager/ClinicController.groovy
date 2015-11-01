package clinicmanager

import org.springframework.dao.DataIntegrityViolationException

class ClinicController {

    def stockService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        List<Clinic> lowStocks = stockService.getLowStockWarnings()

        [clinicInstanceList: Clinic.list(params), clinicInstanceTotal: Clinic.count(), lowStocks:lowStocks]
    }

    def create() {
        [clinicInstance: new Clinic(params)]
    }

    def save() {
        def clinicInstance = new Clinic(params)
        if (!clinicInstance.save(flush: true)) {
            render(view: "create", model: [clinicInstance: clinicInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'clinic.label', default: 'Clinic'), clinicInstance.id])
        redirect(action: "show", id: clinicInstance.id)
    }

    def show(Long id) {
        def clinicInstance = Clinic.get(id)
        if (!clinicInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'clinic.label', default: 'Clinic'), id])
            redirect(action: "list")
            return
        }

        //Create a sorted list so medication always appear in the same order
        List sortedMedication = clinicInstance.medication.toList().sort { it.medication.title }

        List warnings = stockService.checkStockLevels(sortedMedication)

        [clinicInstance: clinicInstance, sortedMedication:sortedMedication, warnings:warnings]
    }

    def edit(Long id) {
        def clinicInstance = Clinic.get(id)
        if (!clinicInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'clinic.label', default: 'Clinic'), id])
            redirect(action: "list")
            return
        }

        [clinicInstance: clinicInstance]
    }

    def update(Long id) {
        def clinicInstance = Clinic.get(id)
        if (!clinicInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'clinic.label', default: 'Clinic'), id])
            redirect(action: "list")
            return
        }

        clinicInstance.properties = params

        if (!clinicInstance.save(flush: true)) {
            render(view: "edit", model: [clinicInstance: clinicInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'clinic.label', default: 'Clinic'), clinicInstance.id])
        redirect(action: "show", id: clinicInstance.id)
    }

    def delete(Long id) {
        def clinicInstance = Clinic.get(id)
        if (!clinicInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'clinic.label', default: 'Clinic'), id])
            redirect(action: "list")
            return
        }

        try {
            clinicInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'clinic.label', default: 'Clinic'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'clinic.label', default: 'Clinic'), id])
            redirect(action: "show", id: id)
        }
    }
}
