package ru.gb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.gb.task1.SomeClass;


@SpringBootApplication
public class SpringAopDemoApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringAopDemoApplication.class, args);
        SomeClass bean = context.getBean(SomeClass.class);
        bean.someMethod();
        bean.someMethod();
    }
}
