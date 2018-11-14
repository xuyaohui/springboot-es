package cn.momosv.es;

import cn.momosv.es.entity.GoodInfo;
import cn.momosv.es.util.ElasticSearchUtil;
import net.minidev.json.JSONUtil;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.json.JsonXContent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Collections;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EsApplicationTests {

    @Autowired
    private RestHighLevelClient restHLClient;

    @Test
    public void indexTest() {
        try {
            // 借助indexRequest的json拼接工具
            XContentBuilder builder = JsonXContent.contentBuilder()
                    .startObject()
                    .startObject("mappings")
                    .startObject("doc")
                    .startObject("properties")
                    .startObject("title")
                    .field("type","text")
                    .field("analyzer","ik_max_word")
                    .endObject()
                    .startObject("content")
                    .field("type","text")
                    .field("index",true)
                    .field("analyzer","ik_max_word")
                    .endObject()
                    .startObject("uniqueId")
                    .field("type","keyword")
                    .field("index",false)
                    .endObject()
                    .startObject("created")
                    .field("type","date")
                    .field("format","strict_date_optional_time||epoch_millis")
                    .endObject()
                    .endObject()
                    .endObject()
                    .endObject()
                    .startObject("settings")
                    .field("number_of_shards",3)
                    .field("number_of_replicas",1)
                    .endObject()
                    .endObject();

             ElasticSearchUtil.createIndex("momoyas",builder);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

    }


    @Test
    public void indexTest2() {
       ElasticSearchUtil.createIndex("newspaper");

        try {
            XContentBuilder builder = JsonXContent.contentBuilder()
                    .startObject()
                    .startObject("properties")
                    .startObject("content")
                    .field("type", "text")
                    .field("analyzer", "ik_max_word")
                    .field("index", true)
                    .endObject()
                    .endObject()
                    .endObject();
           ElasticSearchUtil. createMapping("newspaper","sports",builder);

        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void indexTest3() throws IOException {
        ElasticSearchUtil.deleteIndex("resthighindex");
        ElasticSearchUtil.deleteIndex("customer");
        ElasticSearchUtil.deleteIndex("people");
        ElasticSearchUtil.deleteIndex("momoyas");
        ElasticSearchUtil.deleteIndex("momo");
        ElasticSearchUtil.deleteIndex("bank");
        ElasticSearchUtil.deleteIndex("testgood");
        ElasticSearchUtil.deleteIndex("demo");
        ElasticSearchUtil.deleteIndex("cjcj");

    }
    @Test
    public void indexTest4() throws Exception {
        ElasticSearchUtil.createIndex("newspaper");
        GoodInfo good =new GoodInfo();
        good.setId(123L);

        IndexRequest indexRequest = new IndexRequest("newspaper", "_doc").source(ElasticSearchUtil.objectToMap(good));
        ElasticSearchUtil.addIndexRequest(indexRequest);

    }


}
