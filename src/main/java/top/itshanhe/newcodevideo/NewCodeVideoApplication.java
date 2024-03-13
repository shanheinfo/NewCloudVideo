package top.itshanhe.newcodevideo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("top.itshanhe.newcodevideo.web.mapper")
public class NewCodeVideoApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(NewCodeVideoApplication.class, args);
    }
    
}
