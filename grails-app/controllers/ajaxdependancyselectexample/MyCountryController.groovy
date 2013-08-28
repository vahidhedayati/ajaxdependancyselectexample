package ajaxdependancyselectexample

import org.springframework.dao.DataIntegrityViolationException

class MyCountryController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [myCountryInstanceList: MyCountry.list(params), myCountryInstanceTotal: MyCountry.count()]
    }

    def create() {
        [myCountryInstance: new MyCountry(params)]
    }

    def save() {
        def myCountryInstance = new MyCountry(params)
        if (!myCountryInstance.save(flush: true)) {
            render(view: "create", model: [myCountryInstance: myCountryInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'myCountry.label', default: 'MyCountry'), myCountryInstance.id])
        redirect(action: "show", id: myCountryInstance.id)
    }

    def show(Long id) {
        def myCountryInstance = MyCountry.get(id)
        if (!myCountryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'myCountry.label', default: 'MyCountry'), id])
            redirect(action: "list")
            return
        }

        [myCountryInstance: myCountryInstance]
    }

    def edit(Long id) {
        def myCountryInstance = MyCountry.get(id)
        if (!myCountryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'myCountry.label', default: 'MyCountry'), id])
            redirect(action: "list")
            return
        }

        [myCountryInstance: myCountryInstance]
    }

    def update(Long id, Long version) {
        def myCountryInstance = MyCountry.get(id)
        if (!myCountryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'myCountry.label', default: 'MyCountry'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (myCountryInstance.version > version) {
                myCountryInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'myCountry.label', default: 'MyCountry')] as Object[],
                          "Another user has updated this MyCountry while you were editing")
                render(view: "edit", model: [myCountryInstance: myCountryInstance])
                return
            }
        }

        myCountryInstance.properties = params

        if (!myCountryInstance.save(flush: true)) {
            render(view: "edit", model: [myCountryInstance: myCountryInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'myCountry.label', default: 'MyCountry'), myCountryInstance.id])
        redirect(action: "show", id: myCountryInstance.id)
    }

    def delete(Long id) {
        def myCountryInstance = MyCountry.get(id)
        if (!myCountryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'myCountry.label', default: 'MyCountry'), id])
            redirect(action: "list")
            return
        }

        try {
            myCountryInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'myCountry.label', default: 'MyCountry'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'myCountry.label', default: 'MyCountry'), id])
            redirect(action: "show", id: id)
        }
    }
}
