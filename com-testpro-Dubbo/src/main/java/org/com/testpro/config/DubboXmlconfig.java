package org.com.testpro.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.ReferenceBean;
import com.gsafety.bigdata.lifeline.service.BridgeQueryService;
import com.gsafety.bigdata.lifeline.service.GasQueryService;
import com.gsafety.bigdata.lifeline.service.HeatQueryService;
import com.gsafety.bigdata.lifeline.service.WaterQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan(basePackages="com.gsafety.*")
//@ImportResource("classpath:spring-config-dubbo.xml")
public class DubboXmlconfig {


    @Autowired
    Environment environment;
    /*
     * dubbo基本配置
     * */
    @Bean
    public RegistryConfig registryConfig(){
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(environment.getProperty("spring.dubbo.register.address"));
        registryConfig.setProtocol(environment.getProperty("spring.dubbo.register.protocol"));
        return registryConfig;
    }


    @Bean
    public ApplicationConfig applicationConfig(){
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(environment.getProperty("spring.dubbo.application.name"));
        applicationConfig.setOrganization(environment.getProperty("spring.dubbo.application.organization"));
        applicationConfig.setOwner(environment.getProperty("spring.dubbo.application.owner"));
        return applicationConfig;
    }




    /*
     * 四个consumer 接口配置
     * */

    @Bean
    public ReferenceBean<BridgeQueryService> getDubboBridgeQueryServiceIf(){
        ReferenceBean<BridgeQueryService> referenceBean = new ReferenceBean<>();
        referenceBean.setInterface(BridgeQueryService.class);
        referenceBean.setVersion("1.4");
        referenceBean.setTimeout(600000);
        return referenceBean;
    }


    @Bean
    public ReferenceBean<WaterQueryService> getDubboWaterQueryServiceIf(){
        ReferenceBean<WaterQueryService> referenceBean = new  ReferenceBean<>();
        referenceBean.setInterface(WaterQueryService.class);
        referenceBean.setVersion("1.4");
        referenceBean.setTimeout(30000);
        return referenceBean;
    }


    @Bean
    public ReferenceBean<GasQueryService> getDubboGasQueryServiceIf(){
        ReferenceBean<GasQueryService> referenceBean = new  ReferenceBean<>();
        referenceBean.setInterface(GasQueryService.class);
        referenceBean.setVersion("1.4");
        referenceBean.setTimeout(30000);
        return referenceBean;
    }


    @Bean
    public ReferenceBean<HeatQueryService> getDubboHeatQueryServiceIf(){
        ReferenceBean<HeatQueryService> referenceBean = new  ReferenceBean<>();
        referenceBean.setInterface(HeatQueryService.class);
        referenceBean.setVersion("1.4");
        referenceBean.setTimeout(600000);
        return referenceBean;
    }

	
	
	
}
