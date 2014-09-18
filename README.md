DevPing
=========

Devping is the Easiest Chatting Program.

Project config
---------
1. Servlet spec 3.0 이상
2. WAS vm option : -Dspring.profiles.active=develop 추가
3. stdlib-package mavne에 추가하기(path = devping-domain/lib)
   ```sh
   cd PATH/TO/devping-domain/lib
   mvn install:install-file -Dfile=stdlib-package.jar -DgroupId=edu.princeton.cs.introcs -DartifactId=stdlib-package -Dversion=1.0 -Dpackaging=jar
   ```




