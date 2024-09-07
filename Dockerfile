fROM azul/zulu-openjdk:17-latest
VOLUME /tmp
COPY build/libs/*.jar app.jar
# Render가 제공하는 포트를 사용하도록 환경 변수 설정
ENV PORT 8080

# 포트를 컨테이너 외부로 노출
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
