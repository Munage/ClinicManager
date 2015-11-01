package clinicmanager

import org.springframework.dao.DataIntegrityViolationException

class MedicationController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [medicationInstanceList: Medication.list(params), medicationInstanceTotal: Medication.count()]
    }

    def create() {
        [medicationInstance: new Medication(params)]
    }

    def save() {
        def medicationInstance = new Medication(params)
        if (!medicationInstance.save(flush: true)) {
            render(view: "create", model: [medicationInstance: medicationInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'medication.label', default: 'Medication'), medicationInstance.id])
        redirect(action: "show", id: medicationInstance.id)
    }

    def show(Long id) {
        def medicationInstance = Medication.get(id)
        if (!medicationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'medication.label', default: 'Medication'), id])
            redirect(action: "list")
            return
        }

        [medicationInstance: medicationInstance]
    }

    def edit(Long id) {
        def medicationInstance = Medication.get(id)
        if (!medicationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'medication.label', default: 'Medication'), id])
            redirect(action: "list")
            return
        }

        [medicationInstance: medicationInstance]
    }

    def update(Long id, Long version) {
        def medicationInstance = Medication.get(id)
        if (!medicationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'medication.label', default: 'Medication'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (medicationInstance.version > version) {
                medicationInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'medication.label', default: 'Medication')] as Object[],
                        "Another user has updated this Medication while you were editing")
                render(view: "edit", model: [medicationInstance: medicationInstance])
                return
            }
        }

        medicationInstance.properties = params

        if (!medicationInstance.save(flush: true)) {
            render(view: "edit", model: [medicationInstance: medicationInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'medication.label', default: 'Medication'), medicationInstance.id])
        redirect(action: "show", id: medicationInstance.id)
    }

    def delete(Long id) {
        def medicationInstance = Medication.get(id)
        if (!medicationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'medication.label', default: 'Medication'), id])
            redirect(action: "list")
            return
        }

        try {
            medicationInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'medication.label', default: 'Medication'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'medication.label', default: 'Medication'), id])
            redirect(action: "show", id: id)
        }
    }
}
