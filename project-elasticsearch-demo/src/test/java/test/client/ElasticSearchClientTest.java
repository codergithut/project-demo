package test.client;

import client.ElasticSearchClientManage;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.bulk.byscroll.BulkByScrollResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.rest.RestStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotSame;
import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/7/10
 * @description
 */
public class ElasticSearchClientTest {

    TransportClient client;

    private String addId;

    IndexResponse response;

    @Before
    public void initParam() throws UnknownHostException {
        ElasticSearchClientManage elasticSearchClientManage = ElasticSearchClientManage.getInstanceEsClientManage();
        client = elasticSearchClientManage.getESClient();
    }

    /**
     * 插入数据
     * @throws IOException
     */
    @Test
    public void insertIndexAndDataTest() throws IOException {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("user","kimchy");
        json.put("postDate",new Date());
        json.put("message","trying out Elasticsearch");

        /**
         * 测试插入数据
         */
        response = client.prepareIndex("twitter", "tweet")
                .setSource(json)
                .get();


        String _index = response.getIndex();
        String _type = response.getType();
        addId = response.getId();
        long _version = response.getVersion();
        RestStatus status = response.status();
        /**
         * 验证结果
         */
        assertEquals("twitter", _index);
        assertEquals("tweet", _type);
        assertEquals("created", response.getResult().getLowercase());
    }

    /**
     * 根据id获取数据
     */
    @Test
    public void searchDataTest() {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("user","search");
        json.put("postDate",new Date());
        json.put("message","trying search");
        /**
         * 插入数据
         */
        response = client.prepareIndex("twitter", "tweet")
                .setSource(json)
                .get();
        String id = response.getId();
        /**
         * 测试获取数据
         */
        GetResponse getResponse = client.prepareGet("twitter", "tweet", id).get();

        /**
         * 验证数据
         */
        assertEquals("twitter", getResponse.getIndex());
        assertEquals("tweet", getResponse.getType());
        assertNotSame(json, getResponse.getSource());

    }

    /**
     * 删除数据
     */
    @Test
    public void deleteDataTest() {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("user","delete");
        json.put("postDate",new Date());
        json.put("message","delete test");

        /**
         * 测试插入数据
         */
        response = client.prepareIndex("twitter", "tweet")
                .setSource(json)
                .get();
        DeleteResponse deleteResponse = client.prepareDelete("twitter", "tweet", response.getId()).get();

        /**
         * 验证结果
         */
        assertEquals("twitter", deleteResponse.getIndex());
        assertEquals("tweet", deleteResponse.getType());
        assertEquals("deleted", deleteResponse.getResult().getLowercase());

    }

    /**
     * 查询删除操作，有点问题，assert断言有出入需要查看
     */
    @Test
    public void deleteDataByQueryTest() {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("user","delete");
        json.put("postDate",new Date());
        json.put("message","delete test");

        /**
         * 测试插入数据 user没有分析插入
         */
        response = client.prepareIndex("twitter", "tweet")
                .setSource(json)
                .get();
        BulkByScrollResponse bulkResponse =
                DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
                        .filter(QueryBuilders.matchQuery("user", "delete"))
                        .source("twitter")
                        .get();
        long deleted = bulkResponse.getDeleted();
        //assertTrue(deleted == 1);
    }

    /**
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     * 跟新文件操作
     */
    @Test
    public void updateTest() throws IOException, ExecutionException, InterruptedException {
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index("index");
        updateRequest.type("type");
        updateRequest.id("1");
        updateRequest.doc(jsonBuilder()
                .startObject()
                .field("gender", "male")
                .endObject());
        client.update(updateRequest).get();

    }

    /**
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     * 跟新或创建文件如果文件存在执行跟新操作，如果不存在则执行插入操作
     */
    @Test
    public void upSertTest() throws IOException, ExecutionException, InterruptedException {
        IndexRequest indexRequest = new IndexRequest("index", "type", "1")
                .source(jsonBuilder()
                        .startObject()
                        .field("name", "Joe Smith")
                        .field("gender", "male")
                        .endObject());
        UpdateRequest updateRequest = new UpdateRequest("index", "type", "1")
                .doc(jsonBuilder()
                        .startObject()
                        .field("gender", "male")
                        .endObject())
                .upsert(indexRequest);
        client.update(updateRequest).get();
    }


    /**
     * 测试多条查询结果，暂时都是空未放入测试数据需要修正
     */
    @Test
    public void multiQueryTest() {
        MultiGetResponse multiGetItemResponses = client.prepareMultiGet()
                .add("twitter", "tweet", "1")
                .add("twitter", "tweet", "2", "3", "4")
                .add("another", "type", "foo")
                .get();

        for (MultiGetItemResponse itemResponse : multiGetItemResponses) {
            GetResponse response = itemResponse.getResponse();
            if (response != null && response.isExists()) {
                String json = response.getSourceAsString();
                System.out.println(json);
            }
        }

    }

    @Test
    public void bulkInsert() throws IOException {
        BulkRequestBuilder bulkRequest = client.prepareBulk();

// either use client#prepare, or use Requests# to directly build index/delete requests
        bulkRequest.add(client.prepareIndex("twitter", "tweet", "1")
                .setSource(jsonBuilder()
                        .startObject()
                        .field("user", "kimchy")
                        .field("postDate", new Date())
                        .field("message", "trying out Elasticsearch")
                        .endObject()
                )
        );

        bulkRequest.add(client.prepareIndex("twitter", "tweet", "2")
                .setSource(jsonBuilder()
                        .startObject()
                        .field("user", "kimchy")
                        .field("postDate", new Date())
                        .field("message", "another post")
                        .endObject()
                )
        );

        BulkResponse bulkResponse = bulkRequest.get();
        if (bulkResponse.hasFailures()) {
            // process failures by iterating through each bulk response item
        }
    }


    @After
    public void clearResource() {
        if(response != null) {
            client.prepareDelete("twitter", "tweet", response.getId()).get();
        }

        ElasticSearchClientManage.closeClient();
    }
}
