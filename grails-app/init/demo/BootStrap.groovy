package demo

class BootStrap {

    def init = { servletContext ->
        final boolean flush = true
        final boolean failOnError = true

        def sherlock = new User(username: 'sherlock', password: 'elementary')
        sherlock.save(flush: flush, failOnError: failOnError)

        def watson = new User(username: 'watson', password: 'houndsofbaskerville')
        watson.save(flush: flush, failOnError: failOnError)

        def detectiveRole = new Role(authority: 'ROLE_ADMIN')
        detectiveRole.save(flush: flush, failOnError: true)

        new UserRole(user: sherlock, role: detectiveRole).save(flush: flush, failOnError: failOnError)
        new UserRole(user: watson, role: detectiveRole).save(flush: flush, failOnError: failOnError)
    }
    def destroy = {
    }
}
