package lari.project.registration_login_project;

import javax.print.DocFlavor.READER;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer  extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
            return application.sources(RegistrationLoginProjectApplication.class); // Replace DemoApplication with your main class
    }

}