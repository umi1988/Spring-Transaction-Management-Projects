Spring-Boot-Transaction-Propogation-Supports-Example-:


failure use case:-

{
  "id": 109,
  "productId": 1,
  "quantity": 1
}





2026-01-02T15:02:27.164+05:30  INFO 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2026-01-02T15:02:27.164+05:30  INFO 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2026-01-02T15:02:27.169+05:30  INFO 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 5 ms
2026-01-02T15:02:27.275+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(2059893097<open>)] for JPA transaction
2026-01-02T15:02:27.276+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Creating new transaction with name [com.starttohkar.service.OrderProcessingService.placeAnOrder]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT
2026-01-02T15:02:27.286+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Exposing JPA transaction as JDBC [org.springframework.orm.jpa.vendor.HibernateJpaDialect$HibernateConnectionHandle@4c825c53]
2026-01-02T15:02:27.289+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(2059893097<open>)] for JPA transaction
2026-01-02T15:02:27.289+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T15:02:27.322+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(2059893097<open>)] for JPA transaction
2026-01-02T15:02:27.322+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T15:02:27.323+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(2059893097<open>)] for JPA transaction
2026-01-02T15:02:27.323+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T15:02:27.331+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(2059893097<open>)] for JPA transaction
2026-01-02T15:02:27.331+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T15:02:27.331+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Participating transaction failed - marking existing transaction as rollback-only
2026-01-02T15:02:27.331+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Setting JPA transaction on EntityManager [SessionImpl(2059893097<open>)] rollback-only
2026-01-02T15:02:27.331+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(2059893097<open>)] for JPA transaction
2026-01-02T15:02:27.332+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Suspending current transaction, creating new transaction with name [com.starttohkar.handler.AuditLogHandler.logAuditDetails]
2026-01-02T15:02:27.332+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Opened new EntityManager [SessionImpl(1134613597<open>)] for JPA transaction
2026-01-02T15:02:27.334+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Exposing JPA transaction as JDBC [org.springframework.orm.jpa.vendor.HibernateJpaDialect$HibernateConnectionHandle@74cf008a]
2026-01-02T15:02:27.335+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1134613597<open>)] for JPA transaction
2026-01-02T15:02:27.335+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T15:02:27.348+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Initiating transaction commit
2026-01-02T15:02:27.348+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Committing JPA transaction on EntityManager [SessionImpl(1134613597<open>)]
2026-01-02T15:02:27.363+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Closing JPA EntityManager after transaction
2026-01-02T15:02:27.363+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Resuming suspended transaction after completion of inner transaction
Customer details fetched !!!!!
2026-01-02T15:02:27.363+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Initiating transaction commit
2026-01-02T15:02:27.363+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Committing JPA transaction on EntityManager [SessionImpl(2059893097<open>)]
2026-01-02T15:02:27.366+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Not closing pre-bound JPA EntityManager after transaction
2026-01-02T15:02:27.367+05:30 ERROR 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed: org.springframework.transaction.UnexpectedRollbackException: Transaction silently rolled back because it has been marked as rollback-only] with root cause

org.springframework.transaction.UnexpectedRollbackException: Transaction silently rolled back because it has been marked as rollback-only
	at org.springframework.transaction.support.AbstractPlatformTransactionManager.processCommit(AbstractPlatformTransactionManager.java:803) ~[spring-tx-7.0.2.jar:7.0.2]
	at org.springframework.transaction.support.AbstractPlatformTransactionManager.commit(AbstractPlatformTransactionManager.java:757) ~[spring-tx-7.0.2.jar:7.0.2]
	at org.springframework.transaction.interceptor.TransactionAspectSupport.commitTransactionAfterReturning(TransactionAspectSupport.java:684) ~[spring-tx-7.0.2.jar:7.0.2]
	at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:406) ~[spring-tx-7.0.2.jar:7.0.2]
	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:118) ~[spring-tx-7.0.2.jar:7.0.2]
	at org.s


    Happy Use case


{
  "id": 110,
  "productId": 3,
  "quantity": 1
}



2026-01-02T15:03:53.729+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-2] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1592893390<open>)] for JPA transaction
2026-01-02T15:03:53.730+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-2] o.s.orm.jpa.JpaTransactionManager        : Creating new transaction with name [com.starttohkar.service.OrderProcessingService.placeAnOrder]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT
2026-01-02T15:03:53.737+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-2] o.s.orm.jpa.JpaTransactionManager        : Exposing JPA transaction as JDBC [org.springframework.orm.jpa.vendor.HibernateJpaDialect$HibernateConnectionHandle@50367a60]
2026-01-02T15:03:53.738+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-2] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1592893390<open>)] for JPA transaction
2026-01-02T15:03:53.739+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-2] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T15:03:53.748+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-2] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1592893390<open>)] for JPA transaction
2026-01-02T15:03:53.748+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-2] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T15:03:53.749+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-2] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1592893390<open>)] for JPA transaction
2026-01-02T15:03:53.749+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-2] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T15:03:53.754+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-2] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1592893390<open>)] for JPA transaction
2026-01-02T15:03:53.755+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-2] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T15:03:53.756+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-2] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1592893390<open>)] for JPA transaction
2026-01-02T15:03:53.756+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-2] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T15:03:53.757+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-2] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1592893390<open>)] for JPA transaction
2026-01-02T15:03:53.757+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-2] o.s.orm.jpa.JpaTransactionManager        : Suspending current transaction, creating new transaction with name [com.starttohkar.handler.AuditLogHandler.logAuditDetails]
2026-01-02T15:03:53.758+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-2] o.s.orm.jpa.JpaTransactionManager        : Opened new EntityManager [SessionImpl(1898221332<open>)] for JPA transaction
2026-01-02T15:03:53.761+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-2] o.s.orm.jpa.JpaTransactionManager        : Exposing JPA transaction as JDBC [org.springframework.orm.jpa.vendor.HibernateJpaDialect$HibernateConnectionHandle@6171f21]
2026-01-02T15:03:53.761+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-2] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1898221332<open>)] for JPA transaction
2026-01-02T15:03:53.761+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-2] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T15:03:53.767+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-2] o.s.orm.jpa.JpaTransactionManager        : Initiating transaction commit
2026-01-02T15:03:53.767+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-2] o.s.orm.jpa.JpaTransactionManager        : Committing JPA transaction on EntityManager [SessionImpl(1898221332<open>)]
2026-01-02T15:03:53.774+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-2] o.s.orm.jpa.JpaTransactionManager        : Closing JPA EntityManager after transaction
2026-01-02T15:03:53.774+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-2] o.s.orm.jpa.JpaTransactionManager        : Resuming suspended transaction after completion of inner transaction
Customer details fetched !!!!!
2026-01-02T15:03:53.774+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-2] o.s.orm.jpa.JpaTransactionManager        : Initiating transaction commit
2026-01-02T15:03:53.774+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-2] o.s.orm.jpa.JpaTransactionManager        : Committing JPA transaction on EntityManager [SessionImpl(1592893390<open>)]
2026-01-02T15:03:53.791+05:30 DEBUG 30452 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-2] o.s.orm.jpa.JpaTransactionManager        : Not closing pre-bound JPA EntityManager after transaction
