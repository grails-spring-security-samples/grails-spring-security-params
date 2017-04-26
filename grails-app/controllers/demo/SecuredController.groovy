package demo

import grails.plugin.springsecurity.annotation.Secured

class SecuredController {

    @Secured(closure = {
        if ( request.parameterMap.get('val') == null || request.parameterMap.get('val')[0] == '' ) {
            return false
        }
        def val = request.parameterMap.get('val')[0] as int
        boolean isEven = ( val % 2 ) == 0
        authentication.name == 'sherlock' && isEven
    })
    def index() {
        render 'you have ROLE_ADMIN'
    }
}