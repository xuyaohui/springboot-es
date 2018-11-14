//package cn.momosv.es.config;
//
//import org.apache.http.HttpHost;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.client.config.RequestConfig.Builder;
//import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestClientBuilder;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.beans.factory.annotation.Autowire;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Scope;
//
//import java.io.IOException;
//
//
///**
// * @author linshengwen
// * @version 1.0
// * @description
// * @date 2018/9/26 17:09
// **/
//@ConfigurationProperties(prefix = "diy.elasticsearch")
//@Configuration
//public class ElasticSearchConfig {
//    private String host;
//    private int port;
//    private String schema;
//    private int connectTimeOut;
//    private int socketTimeOut;
//    private int connectionRequestTimeOut;
//    private int maxConnectNum;
//    private int maxConnectPerRoute;
//    private HttpHost httpHost;
//    private boolean uniqueConnectTimeConfig;
//    private boolean uniqueConnectNumConfig;
//    private RestClientBuilder builder;
//    private RestHighLevelClient client;
//
//    /**
//     * Bean name default  函数名字     *     * @return
//     */
//    @Bean
//    public RestHighLevelClient getRestHighLevelClient() {
//        httpHost = new HttpHost(host, port, schema);
//        builder = RestClient.builder(httpHost);
//        if (uniqueConnectTimeConfig) {
//            setConnectTimeOutConfig();
//        }
//        if (uniqueConnectNumConfig) {
//            setMutiConnectConfig();
//        }
//        client = new RestHighLevelClient(builder);
//        return client;
//    }
//
//    /**
//     * 异步httpclient的连接延时配置
//     */
//    public void setConnectTimeOutConfig() {
//        builder.setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
//            @Override
//            public Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
//                requestConfigBuilder.setConnectTimeout(connectTimeOut);
//                requestConfigBuilder.setSocketTimeout(socketTimeOut);
//                requestConfigBuilder.setConnectionRequestTimeout(connectionRequestTimeOut);
//                return requestConfigBuilder;
//            }
//        });
//    }
//
//    /**
//     * 异步httpclient的连接数配置
//     */
//    public void setMutiConnectConfig() {
//        builder.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
//            @Override
//            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
//                httpClientBuilder.setMaxConnTotal(maxConnectNum);
//                httpClientBuilder.setMaxConnPerRoute(maxConnectPerRoute);
//                return httpClientBuilder;
//            }
//        });
//    }
//
//    /**
//     * 关闭连接
//     */
//    public void close() {
//        if (client != null) {
//            try {
//                client.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public String getHost() {
//        return host;
//    }
//
//    public void setHost(String host) {
//        this.host = host;
//    }
//
//    public int getPort() {
//        return port;
//    }
//
//    public void setPort(int port) {
//        this.port = port;
//    }
//
//    public String getSchema() {
//        return schema;
//    }
//
//    public void setSchema(String schema) {
//        this.schema = schema;
//    }
//
//    public int getConnectTimeOut() {
//        return connectTimeOut;
//    }
//
//    public void setConnectTimeOut(int connectTimeOut) {
//        this.connectTimeOut = connectTimeOut;
//    }
//
//    public int getSocketTimeOut() {
//        return socketTimeOut;
//    }
//
//    public void setSocketTimeOut(int socketTimeOut) {
//        this.socketTimeOut = socketTimeOut;
//    }
//
//    public int getConnectionRequestTimeOut() {
//        return connectionRequestTimeOut;
//    }
//
//    public void setConnectionRequestTimeOut(int connectionRequestTimeOut) {
//        this.connectionRequestTimeOut = connectionRequestTimeOut;
//    }
//
//    public int getMaxConnectNum() {
//        return maxConnectNum;
//    }
//
//    public void setMaxConnectNum(int maxConnectNum) {
//        this.maxConnectNum = maxConnectNum;
//    }
//
//    public int getMaxConnectPerRoute() {
//        return maxConnectPerRoute;
//    }
//
//    public void setMaxConnectPerRoute(int maxConnectPerRoute) {
//        this.maxConnectPerRoute = maxConnectPerRoute;
//    }
//
//    public HttpHost getHttpHost() {
//        return httpHost;
//    }
//
//    public void setHttpHost(HttpHost httpHost) {
//        this.httpHost = httpHost;
//    }
//
//    public boolean isUniqueConnectTimeConfig() {
//        return uniqueConnectTimeConfig;
//    }
//
//    public void setUniqueConnectTimeConfig(boolean uniqueConnectTimeConfig) {
//        this.uniqueConnectTimeConfig = uniqueConnectTimeConfig;
//    }
//
//    public boolean isUniqueConnectNumConfig() {
//        return uniqueConnectNumConfig;
//    }
//
//    public void setUniqueConnectNumConfig(boolean uniqueConnectNumConfig) {
//        this.uniqueConnectNumConfig = uniqueConnectNumConfig;
//    }
//
//    public RestClientBuilder getBuilder() {
//        return builder;
//    }
//
//    public void setBuilder(RestClientBuilder builder) {
//        this.builder = builder;
//    }
//
//    public RestHighLevelClient getClient() {
//        return client;
//    }
//
//    public void setClient(RestHighLevelClient client) {
//        this.client = client;
//    }
//}
//
//
//
