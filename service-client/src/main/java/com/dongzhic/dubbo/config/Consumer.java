/*
 *
 *   Licensed to the Apache Software Foundation (ASF) under one or more
 *   contributor license agreements.  See the NOTICE file distributed with
 *   this work for additional information regarding copyright ownership.
 *   The ASF licenses this file to You under the Apache License, Version 2.0
 *   (the "License"); you may not use this file except in compliance with
 *   the License.  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package com.dongzhic.dubbo.config;

import com.dongzhic.action.ServiceConsumer;
import com.dongzhic.entity.OrderEntry;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

public class Consumer {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConsumerConfiguration.class);
        context.start();
        ServiceConsumer serviceConsumer = context.getBean(ServiceConsumer.class);
        OrderEntry entiry = serviceConsumer.getDetail("1");
        System.out.println("result: " + entiry.getMoney());

    }

    @Configuration
    @EnableDubbo(scanBasePackages = "com.dongzhic.action")
    @PropertySource("classpath:/dubbo/dubbo-consumer.properties")
    @ComponentScan(value = {"com.dongzhic.action"})
    static class ConsumerConfiguration {

    }
}
