package ajaxdependancyselectexample

import org.springframework.dao.DataIntegrityViolationException

class MyCityController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [myCityInstanceList: MyCity.list(params), myCityInstanceTotal: MyCity.count()]
    }

    def create() {
        [myCityInstance: new MyCity(params)]
    }

    def save() {
        def myCityInstance = new MyCity(params)
        if (!myCityInstance.save(flush: true)) {
            render(view: "create", model: [myCityInstance: myCityInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'myCity.label', default: 'MyCity'), myCityInstance.id])
        redirect(action: "show", id: myCityInstance.id)
    }

    def show(Long id) {
        def myCityInstance = MyCity.get(id)
        if (!myCityInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'myCity.label', default: 'MyCity'), id])
            redirect(action: "list")
            return
        }

        [myCityInstance: myCityInstance]
    }

    def edit(Long id) {
        def myCityInstance = MyCity.get(id)
        if (!myCityInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'myCity.label', default: 'MyCity'), id])
            redirect(action: "list")
            return
        }

        [myCityInstance: myCityInstance]
    }

    def update(Long id, Long version) {
        def myCityInstance = MyCity.get(id)
        if (!myCityInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'myCity.label', default: 'MyCity'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (myCityInstance.version > version) {
                myCityInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'myCity.label', default: 'MyCity')] as Object[],
                          "Another user has updated this MyCity while you were editing")
                render(view: "edit", model: [myCityInstance: myCityInstance])
                return
            }
        }

        myCityInstance.properties = params

        if (!myCityInstance.save(flush: true)) {
            render(view: "edit", model: [myCityInstance: myCityInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'myCity.label', default: 'MyCity'), myCityInstance.id])
        redirect(action: "show", id: myCityInstance.id)
    }

    def delete(Long id) {
        def myCityInstance = MyCity.get(id)
        if (!myCityInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'myCity.label', default: 'MyCity'), id])
            redirect(action: "list")
            return
        }

        try {
            myCityInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'myCity.label', default: 'MyCity'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'myCity.label', default: 'MyCity'), id])
            redirect(action: "show", id: id)
        }
    }
}
