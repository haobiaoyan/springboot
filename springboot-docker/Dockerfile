#基础镜像
FROM java:8
#配置文件夹映射
VOLUME /tmp
#添加本地的jar包到远程容器
ADD /target/springboot-docker-0.0.1-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
#暴露端口
EXPOSE 9999
#运行（入口点）
ENTRYPOINT ["java","-jar","/app.jar"]