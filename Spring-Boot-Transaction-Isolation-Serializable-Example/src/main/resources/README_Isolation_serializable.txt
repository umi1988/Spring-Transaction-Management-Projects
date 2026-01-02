
Spring-Boot-Transaction-Isolation-Serializable-Example-:

-Execute txn 1 by 1 by appliying locking mechanism, it will slow down the performance 
-not concurrently
- Here Txn A finished, committed
-Then Txn B started and finished and committed

2026-01-02T18:23:11.290+05:30  INFO 40163 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2026-01-02T18:23:11.291+05:30  INFO 40163 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2026-01-02T18:23:11.295+05:30  INFO 40163 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 4 ms
Transaction A: Stock updated to 5
Transaction A: Committed the update
Transaction B: Read stock as 5
Stock read by Transaction B: 5
