package com.ljm.common;

import com.alibaba.fastjson.JSONObject;
import com.ljm.bo.mongo.Index;
import com.ljm.bo.mongo.QueryModel;
import com.ljm.utils.MongoDBUtil;
import com.mongodb.MongoNamespace;
import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * @ClassName MongoDNUtilTest
 * @Description
 * @Author Jim
 * @Date 2022/4/1 14:04
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class MongoDBUtilTest {
    @Autowired
    private MongoDBUtil mongoDBUtil;

    private static final String COLL_NAME = "tb_test";

    @Test
    public void testCreateCollection(){
        mongoDBUtil.createCollection(COLL_NAME);
    }
    @Test
    public void testRenameCollection(){

        mongoDBUtil.renameCollection("zhangsan14","zhangsan15");
    }


    @Test
    public void testDropCollection(){
        mongoDBUtil.dropCollection(COLL_NAME);
    }

    @Test
    public void testCreateSingleIndex(){
        // 创建单索引
        Index index1 = new Index();
        index1.setCollectionName(COLL_NAME);
        index1.setType(Index.INDEX_SINGLE);
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("name", 1);
        index1.setFieldNames(map1);
        mongoDBUtil.createIndex(index1);
    }

    @Test
    public void testCreateCompositeIndex(){
        // 创建复合索引
        Index index2 = new Index();
        index2.setCollectionName(COLL_NAME);
        index2.setType(Index.INDEX_COMPOSITE);
        Map<String, Integer> map2 = new LinkedHashMap<>();
        map2.put("id", 1);
        map2.put("name", -1);
        index2.setFieldNames(map2);
        mongoDBUtil.createIndex(index2);
    }

    @Test
    public void testCreateHashIndex(){
        // 创建hash索引
        Index index1 = new Index();
        index1.setCollectionName(COLL_NAME);
        index1.setType(Index.INDEX_HASH_KEY);
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("age", -1);
        index1.setFieldNames(map1);
        mongoDBUtil.createIndex(index1);
    }

    @Test
    public void testCreateTextIndex(){
        // 创建文本索引
        Index index1 = new Index();
        index1.setCollectionName(COLL_NAME);
        index1.setType(Index.INDEX_TEXT_KEY);
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("remark", 1);
        index1.setFieldNames(map1);
        mongoDBUtil.createIndex(index1);
    }

    @Test
    public void testUpdateIndex(){
        // 创建单索引
        Index index1 = new Index();
        index1.setCollectionName(COLL_NAME);
        index1.setType(Index.INDEX_SINGLE);
        index1.setIndexName("name_1");
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("name", -1);
        index1.setFieldNames(map1);
        mongoDBUtil.updateIndex(index1);
    }

    @Test
    public void testRemoveIndex(){
        String indexName = "id_1_name_-1";
        mongoDBUtil.removeIndex(COLL_NAME, indexName);
    }

    @Test
    public void testRemoveAllIndex(){
        mongoDBUtil.removeAllIndex(COLL_NAME);
    }

    @Test
    public void testGetAllIndexes(){
        List<Document> indexes = mongoDBUtil.getAllIndex(COLL_NAME);
    }

    @Test
    public void testInsert(){
        List<JSONObject> data = new ArrayList<>();
        for (int i = 1 ; i < 101 ; i++){
            String info = "{\"name\": \"jim\", \"sex\": \"男\", \"age\": " + i + "}";
            data.add(JSONObject.parseObject(info));
        }
        mongoDBUtil.insertMulti(data, COLL_NAME);
    }

    @Test
    public void testUpdateById(){
        String info = "{\"name\": \"jim\", \"sex\": \"女\", \"age\": 10}";
        mongoDBUtil.updateById("624bffd5a5cc8528560c180f", COLL_NAME, JSONObject.parse(info));
    }

    @Test
    public void testDeleteById(){
        mongoDBUtil.deleteById("624bffd5a5cc8528560c180f", COLL_NAME);
    }

    @Test
    public void testQuery(){
        QueryModel queryModel = new QueryModel(COLL_NAME, "*", 1, 10);
        List<String> res = mongoDBUtil.query(queryModel, String.class);
        for (String item : res){
            System.out.println(item);
        }
    }
    @Test
    public void testQuery1(){

        List<String> res = mongoDBUtil.query();

        for (String item : res){
            System.out.println(item);
        }
    }

    @Test
    public void testNoConditionQuery(){
        QueryModel queryModel = new QueryModel(COLL_NAME, null, null, null);
        List<String> res = mongoDBUtil.query(queryModel, String.class);
        for (String item : res){
            System.out.println(item);
        }
    }

    @Test
    public void testEexcute(){
        mongoDBUtil.multiQuery();
    }


}
