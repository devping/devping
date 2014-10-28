DevPing
=========

Devping is the Easiest Chatting Program.

Project config
---------
1. Servlet spec 3.0 이상
2. git shell이나 Cmd창을 이용하여 Repository경로 잡은 뒤 git clone https://github.com/devping/devping.git 실행
   Local에 Download
3. C:\DevelopEnvironment\JBossDeveloperStudio\studio의 jbdevstudio.ini 제일 밑에 
   Encoding Type Setting
   -Dfile.encoding=UTF-8 세팅
4. JBoss Develop Stuido에서 maven import선택
5. WildFly Setting한 서버 더블 클릭
   Overview에서 Open Launch Configuration클릭
   WAS vm option : -Dspring.profiles.active=develop 추가
6. devping\devping-interface\pom.xml
   13번째 line에 <packaging>war</packaging>
7. Algorithm libraries  mavne에 추가하기(path = devping-domain/lib)
   cd PATH/TO/devping-domain/lib
   mvn install:install-file -Dfile=./stdlib-package.jar -DgroupId=edu.princeton.cs.introcs -DartifactId=stdlib-package -Dversion=1.0 -Dpackaging=jar
   mvn install:install-file -Dfile=./algs4-package.jar -DgroupId=edu.princeton.cs.introcs  -DartifactId=algs4-package -Dversion=1.0 -Dpackaging=jar

8. maven > update project 한번 해주시면 됩니다

참고사항> devping root에서 maven clean -> maven build -> Update Project를 해주니 Error가 다 사라집니다.
          안될 때는 maven clean과 maven build를 통해 Error를 확인하신 뒤 Root에서 한번 더 확인 후 작업 바랍니다.


DB setup
---------
1. DB 생성 => devping
2. 계정 생성 및 권한 부여
3. schema 생성 (/devping-domain/src/main/resources/db/schema.sql)
