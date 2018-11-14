package cn.momosv.es;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.UUIDs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;


@SpringBootApplication
@RestController
public class EsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsApplication.class, args);
    }

    @Bean
    public TransportClient client() throws UnknownHostException {

        Settings settings = Settings.builder()
                .put("cluster.name","momoes")
                .put("client.transport.sniff", false)
                .build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
     //   new InetSocketTransportAddress(ipAddress, 9300)
        return client;
    }


    @Autowired
    private TransportClient client;

    @GetMapping("/")
    public String index(){
        SearchResponse res = null;
        res = client.prepareSearch("people")
                .setTypes("user").get();
        System.out.println(res);
        // on shutdown
        return "index";
    }

    @GetMapping("create/{id}")
    public ResponseEntity Index(@PathVariable String id) throws IOException {
        //创建索引
        id = StringUtils.isEmpty(id)?UUIDs.base64UUID():id;
        IndexResponse response = client().prepareIndex("people", "user", id)
                .setSource(jsonBuilder()
                        .startObject()
                        .field("name", "jack")
                        .field("country", "china")
                        .field("age", "24")
                        .endObject()
                )
                .get();
        return new ResponseEntity(response.getResult(),HttpStatus.OK);
    }
    //根据id查询文档
    @GetMapping("get")
    public ResponseEntity get(@RequestParam(name="id",defaultValue = "")String id){
        if(id.isEmpty()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        GetResponse result = this.client.prepareGet("people","user",id).get();
        if(!result.isExists()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(result.getSource(),HttpStatus.OK);

    }

    //根据id修改文档
    @GetMapping("update")
    public void update(@RequestParam(name="id",defaultValue = "")String id) throws IOException {

        client.prepareUpdate("people", "user", id)
                .setDoc(jsonBuilder()
                        .startObject()
                        .field("name", "zhangs")
                        .endObject())
                .get();

    }

    //根据id删除文档
    @GetMapping("delete")
    public ResponseEntity delete(@RequestParam(name="id",defaultValue = "")String id){
        if(id.isEmpty()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        DeleteResponse response = client.prepareDelete("people", "user", id)
                .get();
        return new ResponseEntity(response.getResult(),HttpStatus.OK);

    }

    //根据条件返回list多条数据
    @GetMapping("getList")
    @ResponseBody
    public ResponseEntity getList(String[] ids){

        MultiGetResponse multiGetItemResponses = client.prepareMultiGet()
                .add("people", "user", ids)
                .get();

        for (MultiGetItemResponse itemResponse : multiGetItemResponses) {
            GetResponse response = itemResponse.getResponse();
            if (response.isExists()) {
                String json = response.getSourceAsString();
                System.out.println(json);
            }
        }

        return new ResponseEntity(multiGetItemResponses.getResponses(),HttpStatus.OK);

    }

    //用bulk api在一次请求中同时执行几次操作
    @GetMapping("bulk")
    @ResponseBody
    public ResponseEntity bulk(@RequestParam(name="id",defaultValue = "")String id) throws IOException {
        BulkRequestBuilder bulkRequest = client.prepareBulk();

        // 先创建索引
        bulkRequest.add(client.prepareIndex("people", "user", id)
                .setSource(jsonBuilder()
                        .startObject()
                        .field("user", "kimchy")
                        .field("country", "china")
                        .field("age", "18")
                        .endObject()
                )
        );

        bulkRequest.add(client.prepareDelete("people", "user",id)
        );

        BulkResponse bulkResponse = bulkRequest.get();
        return new ResponseEntity(bulkResponse.getItems(),HttpStatus.OK);

    }

  //  本文来自 lightTrace 的CSDN 博客 ，全文地址请点击：https://blog.csdn.net/suresand/article/details/78939268?utm_source=copy
}
