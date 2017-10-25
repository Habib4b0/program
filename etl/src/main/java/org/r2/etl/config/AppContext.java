package org.r2.etl.config;

import org.r2.etl.common.util.FilePathUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * @author stpl
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
        "org.r2.etl.common"
})
//@Import({PersistenceContext.class})
@PropertySource("classpath:application.properties")

public class AppContext extends WebMvcConfigurerAdapter {
	
	
    @Override
    /**
     * 
     */
    public void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    
    @Bean
    /**
     * 
     * @return
     */
    public ViewResolver viewResolver() {
        final InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix(FilePathUtil.VIEW_RESOL_PRE);
        viewResolver.setSuffix(FilePathUtil.VIEW_RESOL_SUFF);

        return viewResolver;
    }
}

