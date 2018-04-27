custom.serverid=2

server.port=981${custom.serverid}

eureka.instance.hostname=${spring.application.name}-${custom.serverid}
eureka.instance.instance-id=${spring.application.name}:${server.port}
eureka.instance.lease-expiration-duration-in-seconds=10
eureka.instance.lease-renewal-interval-in-seconds=10
eureka.client.register-with-eureka=true
eureka.client.fetch-registry=true
eureka.client.serviceUrl.defaultZone=http://eureka-server-1:9001/eureka,http://eureka-server-2:9002/eureka

custom.test.appConfigKey=end1-t${custom.serverid}
