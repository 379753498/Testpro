package org.com.testpro.base;


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
 * -----------------------------------------
 * Testng测试继承AbstractTestNGSpringContextTests
 * @SpringBootTest
 * @author Administrator
 *
 */
@SpringBootTest(webEnvironment=WebEnvironment.NONE)
public class BaseapplicationTestng  extends AbstractTestNGSpringContextTests {

		
}
