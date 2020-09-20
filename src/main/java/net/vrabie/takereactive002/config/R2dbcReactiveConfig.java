package net.vrabie.takereactive002.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class R2dbcReactiveConfig {

//    @Bean
//    ReactiveTransactionManager r2dbcReactiveTxManager(@Qualifier("connectionFactory") ConnectionFactory connectionFactory) {
//        return new R2dbcTransactionManager(connectionFactory);
//    }

//@Qualifier("r2dbcReactiveTxManager")
//    @Bean
//    TransactionalOperator transactionalOperator( ReactiveTransactionManager reactiveTransactionManager) {
//        return TransactionalOperator.create(reactiveTransactionManager);
//    }

}
