package ajaxdependancyselectexample

import org.springframework.dao.DataIntegrityViolationException

class DepartmentsController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [departmentsInstanceList: Departments.list(params), departmentsInstanceTotal: Departments.count()]
    }

    def create() {
        [departmentsInstance: new Departments(params)]
    }

    def save() {
        def departmentsInstance = new Departments(params)
        if (!departmentsInstance.save(flush: true)) {
            render(view: "create", model: [departmentsInstance: departmentsInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'departments.label', default: 'Departments'), departmentsInstance.id])
        redirect(action: "show", id: departmentsInstance.id)
    }

    def show(Long id) {
        def departmentsInstance = Departments.get(id)
        if (!departmentsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'departments.label', default: 'Departments'), id])
            redirect(action: "list")
            return
        }

        [departmentsInstance: departmentsInstance]
    }

    def edit(Long id) {
        def departmentsInstance = Departments.get(id)
        if (!departmentsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'departments.label', default: 'Departments'), id])
            redirect(action: "list")
            return
        }

        [departmentsInstance: departmentsInstance]
    }

    def update(Long id, Long version) {
        def departmentsInstance = Departments.get(id)
        if (!departmentsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'departments.label', default: 'Departments'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (departmentsInstance.version > version) {
                departmentsInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'departments.label', default: 'Departments')] as Object[],
                          "Another user has updated this Departments while you were editing")
                render(view: "edit", model: [departmentsInstance: departmentsInstance])
                return
            }
        }

        departmentsInstance.properties = params

        if (!departmentsInstance.save(flush: true)) {
            render(view: "edit", model: [departmentsInstance: departmentsInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'departments.label', default: 'Departments'), departmentsInstance.id])
        redirect(action: "show", id: departmentsInstance.id)
    }

    def delete(Long id) {
        def departmentsInstance = Departments.get(id)
        if (!departmentsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'departments.label', default: 'Departments'), id])
            redirect(action: "list")
            return
        }

        try {
            departmentsInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'departments.label', default: 'Departments'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'departments.label', default: 'Departments'), id])
            redirect(action: "show", id: id)
        }
    }
}
