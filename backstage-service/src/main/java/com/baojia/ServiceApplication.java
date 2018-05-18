package com.baojia;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableDubboConfiguration
public class ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class);
    }

    /*@Autowired
    private KafkaSender kafkaSender;


    //然后每隔1分钟执行一次
    //@Scheduled(fixedRate = 1000 * 3)
    public void testKafka() throws Exception {
        kafkaSender.sendTest();
    }*/
}
