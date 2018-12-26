package org.com.testpro.Annotation;

import java.lang.annotation.*;

import org.com.testpro.Enum.DataEnum;

@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ChooseDataSource {
	/**
	 * 数据库名称 默认似13
	 * 
	 * @return 数据库名称
	 */
	DataEnum dataSourceName() default DataEnum.DATA_SOURCE_15;

}
