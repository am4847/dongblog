# dongblog
-	프로젝트설명:	<br/>
JPA, STS(SpringframeworkSecurity, BootApp) 기본적인 로직과 사용이유와 활용방법을 숙지하기 위한 프로젝트 <br/>
카카오Tistory를 보고 개인블로그를 구현해보고자하여 시행하게된 개인 프로젝트<br/>

-	개발 환경/기술:	<br/>
Front - 	Bootstrap, Jsp, JS, JQuery, Ajax<br/>
Back - 	Java, Jpa, stsBootApp, Mysql(DB)<br/>
Tools - 	STS4, Lombok, Github, SpringframeworkSecurity(인증), OAuth2.0(kakao인증) <br/>

-	구현내용: <br/>
Front<br/>
Bootstrap과 Jsp를 활용해 FrontPage 생성 <br/>
Json방식의 Data를 Ajax를 통한 전송, Ajax를 활용한 비동기 댓글 구축 <br/>
Back<br/>
Jpa를 활용한 model테이블구축 <br/>
Jparepository를 활용한 CRUD repository DAO구축 <br/>
정보의 무결성을 생각하여 transaction처리한 Service구축 <br/>
정보의 변경을 담당하는 ApiController와 page의 다이렉팅해주는 Controller구축 <br/>
Tools<br/>
Lombok를 활용하여 생성자와 getter,setter의 시각적인 안정성 활용 <br/>
OAuth2.0으로 로그인할 수 있는 환경구축 <br/>
Login.jsp, Join.jsp, principalDetail.java, principalDetailService.java, 해쉬encoder() 등 
SpringframeworkSecurity안정성만 활용하고 기타 view, model, DAO 직접 구축 <br/>

-	구현내용상세: <br/>
    src/main/java/com/dong/blog/config/ <br/> 
    STSSecurity인증에 관련된 설정파일과 Security형식으로 재정의된 Model과 Service <br/>
    src/main/java/com/dong/blog/controller/ <br/>
    page를 다이렉트해주는 일반 Controller와 정보를 변경할 때 받는 ApiController <br/>
    src/main/java/com/dong/blog/dto/ <br/>
    Data를 주고 받을 때 깔끔하게 받기 위해 새롭게 정의하여 처리 <br/>
    src/main/java/com/dong/blog/handler/ <br/>
    각 Exception 상황마다 이벤트 메세지를 받고 page전환해주는 Controller <br/>
    src/main/java/com/dong/blog/model/ <br/>
    JPA로 구현된 model <br/>
    src/main/java/com/dong/blog/repository/ <br/>
    JPA로 구현된 Repository, JPA에 없는 dao는 직접 구현 <br/>
    src/main/java/com/dong/blog/service/ <br/>
    Controller에서 Repository로 연결될 때 정보의 부정합의 방지를 위해 트랜젝션처리 <br/>
    src/main/resources/static/js/ <br/>
    JQuery를 활용한 Button의 이벤트 function처리, Json과 Ajax를 활용한 data 전송,Ajax의 비동기통신를 활용한 refresh없는 button설정<br/>
    src/main/webapp/WEB-INF/views/  <br/>
    Springboot4와 다양한 tag 라이브러리(jstl, el,springframeworksecurity) 활용하여 jsp구축<br/>

-	성과: <br/>
Json방식으로 Data를 보내는 이유와 Ajax로 비동기 통신의 이유, ORM(JPA)의 높은 활용성 인지  <br/>
SpringFramework보다 라이브러리 활용도가 한층 높아진 Spring Tool Suite를 통해 쉬운 유지관리가 가능하다는 것을 알게 되었습니다.  <br/>


- 시연영상: <br/>
1.User <br/>
<video src='https://user-images.githubusercontent.com/48432253/131433021-3bed2360-3851-4dca-8b69-2a3e4602f75c.mp4' width='600px' controls autoplay> <br/>
2.Board <br/>
