package ajaxdependancyselectexample

import org.springframework.dao.DataIntegrityViolationException

class StreetsController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [streetsInstanceList: Streets.list(params), streetsInstanceTotal: Streets.count()]
    }

    def create() {
        [streetsInstance: new Streets(params)]
    }

    def save() {
        def streetsInstance = new Streets(params)
        if (!streetsInstance.save(flush: true)) {
            render(view: "create", model: [streetsInstance: streetsInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'streets.label', default: 'Streets'), streetsInstance.id])
        redirect(action: "show", id: streetsInstance.id)
    }

    def show(Long id) {
        def streetsInstance = Streets.get(id)
        if (!streetsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'streets.label', default: 'Streets'), id])
            redirect(action: "list")
            return
        }

        [streetsInstance: streetsInstance]
    }

    def edit(Long id) {
        def streetsInstance = Streets.get(id)
        if (!streetsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'streets.label', default: 'Streets'), id])
            redirect(action: "list")
            return
        }

        [streetsInstance: streetsInstance]
    }

    def update(Long id, Long version) {
        def streetsInstance = Streets.get(id)
        if (!streetsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'streets.label', default: 'Streets'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (streetsInstance.version > version) {
                streetsInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'streets.label', default: 'Streets')] as Object[],
                          "Another user has updated this Streets while you were editing")
                render(view: "edit", model: [streetsInstance: streetsInstance])
                return
            }
        }

        streetsInstance.properties = params

        if (!streetsInstance.save(flush: true)) {
            render(view: "edit", model: [streetsInstance: streetsInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'streets.label', default: 'Streets'), streetsInstance.id])
        redirect(action: "show", id: streetsInstance.id)
    }

    def delete(Long id) {
        def streetsInstance = Streets.get(id)
        if (!streetsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'streets.label', default: 'Streets'), id])
            redirect(action: "list")
            return
        }

        try {
            streetsInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'streets.label', default: 'Streets'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'streets.label', default: 'Streets'), id])
            redirect(action: "show", id: id)
        }
    }
}
