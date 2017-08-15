FROM java:8
VOLUME /tmp
ADD target/mediapp.jar app.jar
ADD wrapper.sh wrapper.sh
RUN bash -c 'chmod +x /wrapper.sh'
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["bash", "/wrapper.sh"]