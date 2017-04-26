package demo

import geb.spock.GebSpec
import grails.test.mixin.integration.Integration
import grails.transaction.Rollback
import spock.lang.Ignore

@Rollback
@Integration
class SecuredControllerSpec extends GebSpec {

    def setup() {
        browser.baseUrl = "http://localhost:${serverPort}/"
    }

    def "test login as sherlock with value odd param, with ROLE_ADMIN, you are able to see the secured page"() {
        when:
        go 'secured?val=1'

        then:
        at LoginPage

        when:
        login('sherlock', 'elementary')

        then:
        browser.driver.pageSource.contains 'Denied'

    }

    def "test login as sherlock with value even param you are able to see the secured page"() {

        when:
        go 'secured?val=2'

        then:
        at LoginPage

        when:
        login('sherlock', 'elementary')

        then:
        browser.driver.pageSource.contains 'you have ROLE_ADMIN'

    }

    def "test login as sherlock without a param you are not able to see the secured page"() {

        when:
        go 'secured'

        then:
        at LoginPage

        when:
        login('sherlock', 'elementary')

        then:
        browser.driver.pageSource.contains 'Denied'
    }

    def "test login as watson with value even param you are not able to see the secured page"() {
        when:
        go 'secured?val=2'

        then:
        at LoginPage

        when:
        login('watson', 'houndsofbaskerville')

        then:
        browser.driver.pageSource.contains 'Denied'
    }
}
