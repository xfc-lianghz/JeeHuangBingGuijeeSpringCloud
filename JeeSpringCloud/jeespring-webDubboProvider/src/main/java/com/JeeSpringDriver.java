/**
 * Copyright &copy; 2012-2016 <a href="https://gitee.com/JeeHuangBingGui/JeeSpring">JeeSpring</a> All rights reserved.
 */
package com;

import com.jeespring.common.websocket.WebSockertFilter;
import com.jeespring.modules.sys.service.SystemService;
import org.apache.catalina.connector.Connector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * jeespring
 * springboot的启动类
 * * * @author 黄炳桂 516821420@qq.com
 * Created on 2017/1/8 16:20
 *
 * @EnableAutoConfiguration(exclude = {
 * org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class,
 * org.activiti.spring.boot.SecurityAutoConfiguration.class,
 * })
 * 使用lazyInit缩短Spring Boot启动时间//, lazyInit = true
 */
@EnableCaching
@SpringBootApplication
@ServletComponentScan(basePackages = {"com.jeespring", "com.company","cn.xxx"})
@ComponentScan(basePackages = {"com.jeespring", "com.company","cn.xxx"})
@MapperScan(basePackages={"com.jeespring.modules.**.dao","com.company.project.modules.*.dao","cn.xxx.xxx.modules.*.dao"})
@EnableScheduling
@ComponentScan
@EnableAutoConfiguration
@Configuration
//部署开发异常处理在线文档：https://gitee.com/JeeHuangBingGui/jeeSpringCloud/wikis
//开发文档：https://gitee.com/JeeHuangBingGui/jeeSpringCloud/attach_files  （JeeSpringCloud中级培训文档.docx）
public class JeeSpringDriver {
    @Value("${http.port}")
    private Integer port;

    public static void main(String[] args) {
        //Spring boot run
        new SpringApplicationBuilder(JeeSpringDriver.class).web(true).run(args);
        SystemService.printKeyLoadMessage();
        //IM WebSocker
        WebSockertFilter w = new WebSockertFilter();
        w.startWebsocketChatServer();
        JeeSpringRunPrint.runPrint();
    }

    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
        // 添加http
        tomcat.addAdditionalTomcatConnectors(createStandardConnector());
        return tomcat;
    }

    /**
     * 配置http
     * @return Connector
     */
    private Connector createStandardConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setPort(port);
        return connector;
    }

}
