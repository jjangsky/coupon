# Redis&Kafka 활용 선착순 쿠폰 발급

Notion : https://www.notion.so/jjangsky/Redis-Kafka-085d4c842a834340bcfaacfeed52eeef

> **Tech Stacks**

`Springboot` `JPA` `Mysql` `Redis` `Kafka`

> **요구사항**

<aside>
💡 **선착순 100명에게 할인 쿠폰을 제공하는 이벤트를 진행**

이벤트는 아래와 같은 조건을 만족하여야 한다.

-   선착순 100명에게만 지급 되어야 한다.
-   101개 이상이 지급되면 안된다.
-   순간적으로 몰리는 트래픽을 버틸 수 있어야 한다.\*\*

</aside>

**발생할 수 있는 문제점**

-   쿠폰이 100개보다 많이 발급 되는 경우
-   이벤트 페이지 접근 불가
-   이벤트 페이지와 상관 없단 다른 페이지들 속도 저하
