package scripts

import info.inpureprojects.core.INpureCore

class GroovyTest {
    void testPrint() {
        INpureCore.proxy.print("Testing Groovy integration.")
    }
}

GroovyTest c = new GroovyTest()
c.testPrint()

