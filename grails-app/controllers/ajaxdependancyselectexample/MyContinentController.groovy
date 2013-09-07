package ajaxdependancyselectexample

import org.springframework.dao.DataIntegrityViolationException

class MyContinentController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }
	def example() {}
	def example2() {}
	def norefselectionext() {}
	def norefselection() {}
	
	def example5() {render (view: 'example5', model: [params: params])}
	
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [myContinentInstanceList: MyContinent.list(params), myContinentInstanceTotal: MyContinent.count()]
    }

    def create() {
        [myContinentInstance: new MyContinent(params)]
    }

    def save() {
        def myContinentInstance = new MyContinent(params)
        if (!myContinentInstance.save(flush: true)) {
            render(view: "create", model: [myContinentInstance: myContinentInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'myContinent.label', default: 'MyContinent'), myContinentInstance.id])
        redirect(action: "show", id: myContinentInstance.id)
    }

    def show(Long id) {
        def myContinentInstance = MyContinent.get(id)
        if (!myContinentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'myContinent.label', default: 'MyContinent'), id])
            redirect(action: "list")
            return
        }

        [myContinentInstance: myContinentInstance]
    }

    def edit(Long id) {
        def myContinentInstance = MyContinent.get(id)
        if (!myContinentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'myContinent.label', default: 'MyContinent'), id])
            redirect(action: "list")
            return
        }

        [myContinentInstance: myContinentInstance]
    }

    def update(Long id, Long version) {
        def myContinentInstance = MyContinent.get(id)
        if (!myContinentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'myContinent.label', default: 'MyContinent'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (myContinentInstance.version > version) {
                myContinentInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'myContinent.label', default: 'MyContinent')] as Object[],
                          "Another user has updated this MyContinent while you were editing")
                render(view: "edit", model: [myContinentInstance: myContinentInstance])
                return
            }
        }

        myContinentInstance.properties = params

        if (!myContinentInstance.save(flush: true)) {
            render(view: "edit", model: [myContinentInstance: myContinentInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'myContinent.label', default: 'MyContinent'), myContinentInstance.id])
        redirect(action: "show", id: myContinentInstance.id)
    }

    def delete(Long id) {
        def myContinentInstance = MyContinent.get(id)
        if (!myContinentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'myContinent.label', default: 'MyContinent'), id])
            redirect(action: "list")
            return
        }

        try {
            myContinentInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'myContinent.label', default: 'MyContinent'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'myContinent.label', default: 'MyContinent'), id])
            redirect(action: "show", id: id)
        }
    }
}
