package com.liangchen.config;
import com.liangchen.POJO.Notify;
import com.liangchen.POJO.NotifyList;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import javax.servlet.Filter;
import java.util.concurrent.CopyOnWriteArrayList;

@Configuration
@ImportResource("classpath:BeanConfig.xml")
@EnableTransactionManagement
@ComponentScan(basePackages ={"com.liangchen"},
        excludeFilters =
                {@ComponentScan.Filter(type = FilterType.ANNOTATION,classes = EnableWebMvc.class)},
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,classes =Repository.class ),
        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Service.class),
        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Controller.class)
        }
                )
public class rootConfig {
//    @Bean
//    public List<>

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer=new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.liangchen.DAO");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setAnnotationClass(Repository.class);
        return mapperScannerConfigurer;
    }
    @Bean
    public Filter rootFilter(){
        return new RootFilter();
    }
    @Bean
    public MultipartResolver multipartResolver(){
        return new StandardServletMultipartResolver();
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION,
    proxyMode = ScopedProxyMode.TARGET_CLASS
    )
    public NotifyList notifys(){
       return  new NotifyList();
    }

}
