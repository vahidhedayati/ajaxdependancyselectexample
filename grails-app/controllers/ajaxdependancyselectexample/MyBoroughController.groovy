package ajaxdependancyselectexample

import org.springframework.dao.DataIntegrityViolationException

class MyBoroughController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [myBoroughInstanceList: MyBorough.list(params), myBoroughInstanceTotal: MyBorough.count()]
    }

    def create() {
        [myBoroughInstance: new MyBorough(params)]
    }

    def save() {
        def myBoroughInstance = new MyBorough(params)
        if (!myBoroughInstance.save(flush: true)) {
            render(view: "create", model: [myBoroughInstance: myBoroughInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'myBorough.label', default: 'MyBorough'), myBoroughInstance.id])
        redirect(action: "show", id: myBoroughInstance.id)
    }

    def show(Long id) {
        def myBoroughInstance = MyBorough.get(id)
        if (!myBoroughInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'myBorough.label', default: 'MyBorough'), id])
            redirect(action: "list")
            return
        }

        [myBoroughInstance: myBoroughInstance]
    }

    def edit(Long id) {
        def myBoroughInstance = MyBorough.get(id)
        if (!myBoroughInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'myBorough.label', default: 'MyBorough'), id])
            redirect(action: "list")
            return
        }

        [myBoroughInstance: myBoroughInstance]
    }

    def update(Long id, Long version) {
        def myBoroughInstance = MyBorough.get(id)
        if (!myBoroughInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'myBorough.label', default: 'MyBorough'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (myBoroughInstance.version > version) {
                myBoroughInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'myBorough.label', default: 'MyBorough')] as Object[],
                          "Another user has updated this MyBorough while you were editing")
                render(view: "edit", model: [myBoroughInstance: myBoroughInstance])
                return
            }
        }

        myBoroughInstance.properties = params

        if (!myBoroughInstance.save(flush: true)) {
            render(view: "edit", model: [myBoroughInstance: myBoroughInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'myBorough.label', default: 'MyBorough'), myBoroughInstance.id])
        redirect(action: "show", id: myBoroughInstance.id)
    }

    def delete(Long id) {
        def myBoroughInstance = MyBorough.get(id)
        if (!myBoroughInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'myBorough.label', default: 'MyBorough'), id])
            redirect(action: "list")
            return
        }

        try {
            myBoroughInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'myBorough.label', default: 'MyBorough'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'myBorough.label', default: 'MyBorough'), id])
            redirect(action: "show", id: id)
        }
    }
}
