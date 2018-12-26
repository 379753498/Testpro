package org.com.testpro.Aop;

import java.lang.reflect.Method;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.com.testpro.Annotation.ChooseDataSource;
import org.com.testpro.Enum.DataEnum;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Slf4j
public class ChoseDataSource  {

	/**
	 * 切面设置要选择的数据库
	 * 
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */

	@Around("@annotation(org.com.testpro.Annotation.ChooseDataSource)")
	public Object permission(ProceedingJoinPoint joinPoint) throws Throwable {


		Object[] args = joinPoint.getArgs();
		Method method = getMethod(joinPoint, args);// 获取指定的方法
		// 获取数据库名称参数
		ChooseDataSource chooseDataSource = method
				.getAnnotation(ChooseDataSource.class);
		if (chooseDataSource != null) {
			//获取注解的数据库名称
			DataEnum dataSourceName = chooseDataSource.dataSourceName();
			//判断当前数据库是否是有值
			if(CustomContextHolder.getCustomerType()!=null)
			{
				DataEnum[] values = DataEnum.values();
				for (DataEnum dataEnum : values) {
					//如果枚举中的名称与注解数据库名称相同
					if(dataEnum.getDataname().equals(dataSourceName.getDataname()))
					{
						//再跟进枚举中对应的数据库的数据源信息进行匹配判断当前的数据源是否与枚举中的数据源一致 如果一致就过说明数据源是一致的不需要更新
						if(dataEnum.getSpringDatasourceBeanName().equals(CustomContextHolder.getCustomerType()))
						
						{
							log.info("数据库相同不需要进行切换" + CustomContextHolder.getCustomerType());
							return joinPoint.proceed();
						}
						
						else{
							CustomContextHolder.setCustomerType(dataEnum.getSpringDatasourceBeanName());
							log.info("切换新的数据库" + CustomContextHolder.getCustomerType());
							return joinPoint.proceed();
						}
					}
					

				}
				
			}
		}
		
		else{
			log.error(ChooseDataSource.class.getSimpleName()+"dataSourceName 必须填写");
			throw new Exception(ChooseDataSource.class.getSimpleName()+"dataSourceName 必须填写");
		}
		
		return joinPoint.proceed();
	}

	private Method getMethod(ProceedingJoinPoint joinPoint, Object[] args) {
		String methodName = joinPoint.getSignature().getName();
		Class<? extends Object> clazz = joinPoint.getTarget().getClass();
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			if (methodName.equals(method.getName())) {
				return method;
			}
		}
		return null;
	}

	
public static void main(String[] args) {
	
	
	DataEnum[] values = DataEnum.values();
	for (DataEnum dataEnum : values) {
		System.out.println(dataEnum.getDataname());
		System.out.println(dataEnum.getSpringDatasourceBeanName());

	}
}
}



