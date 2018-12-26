package org.com.testpro.Base;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
/**
 * Spring boot 2.0
 * --------------------------------
 * JUnit测试不继承任何类情况需要增加
 * @Runwith(Springrunner.calss)
 * @SpringBootTest(classes=application.class)
 * ------------------------------------------
 * JUnit测试继承AbstractJUnit4SpringContextTests
 * @SpringBootTest
 * ---------------------------------------------
 * Testng测试不继承任何类情况需要增加
 *  @Runwith(Springrunner.calss)
 * @SpringBootTest(classes=application.class)
 * -----------------------------------------
 * Testng测试继承AbstractTestNGSpringContextTests
 * @SpringBootTest
 * @author Administrator
 *
 */
@SpringBootTest(webEnvironment=WebEnvironment.NONE)
public class SpringTestngBase extends AbstractTestNGSpringContextTests {

}
