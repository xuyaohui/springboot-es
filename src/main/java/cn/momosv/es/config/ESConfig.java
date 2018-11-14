package cn.momosv.es.config;

import cn.momosv.es.factory.ESClientSpringFactory;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author linshengwen
 * @version 1.0
 * @description
 * @date 2018/9/29 15:06
 **/
@Configuration
//@ComponentScan(basePackageClasses=ESClientSpringFactory.class)
@ConfigurationProperties(prefix = "diy.elasticsearch")
public class ESConfig {
    private String host;
    private int port;
    private String schema;
    private int maxConnectNum;
    private int maxConnectPerRoute;


    @Bean
    public HttpHost httpHost(){
        return new HttpHost(host,port,schema);
    }

    @Bean(initMethod="init",destroyMethod="close")
    public ESClientSpringFactory getFactory(){
        return ESClientSpringFactory.
                build(httpHost(), maxConnectNum, maxConnectPerRoute);
    }

    @Bean
    public RestClient getRestClient(){
        return getFactory().getClient();
    }

    @Bean
    public RestHighLevelClient getRHLClient(){
        return getFactory().getRhlClient();
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public void setMaxConnectNum(int maxConnectNum) {
        this.maxConnectNum = maxConnectNum;
    }

    public void setMaxConnectPerRoute(int maxConnectPerRoute) {
        this.maxConnectPerRoute = maxConnectPerRoute;
    }
}

