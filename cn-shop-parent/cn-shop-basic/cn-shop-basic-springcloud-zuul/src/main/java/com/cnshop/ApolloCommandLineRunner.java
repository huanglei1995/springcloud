package com.cnshop;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ApolloCommandLineRunner implements CommandLineRunner {

    @ApolloConfig
    private Config config;

    @Override
    public void run(String... args) throws Exception {
        log.info("#################    ApolloCommandLineRunner 启动     ####################");
        config.addChangeListener((changeEvent) ->{
            log.info("Apollo 分布式配置中心监听######" + changeEvent.changedKeys().toString());
        });
    }
}
