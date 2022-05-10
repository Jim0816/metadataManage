package com.ljm.bo.mongo;

import com.mongodb.client.model.IndexOptions;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @ClassName Index
 * @Description MongoDB索引对象
 * @Author Jim
 * @Date 2022/4/3 19:23
 **/
@NoArgsConstructor
@Data
public class Index {
    public static final String INDEX_SINGLE = "single_key"; // 单索引
    public static final String INDEX_COMPOSITE = "composite_key"; // 复合索引
    public static final String INDEX_MULTI_KEY = "multi_key"; // 多键索引
    public static final String INDEX_TEXT_KEY = "text_key"; // 文本索引
    public static final String INDEX_HASH_KEY = "hash_key"; // 哈希索引

    //集合名称
    private String collectionName;

    // 索引类型
    private String type;

    //字段名称集合 <字段名称，排序方向>  1：升序  -1：降序
    private Map<String, Integer> fieldNames;
    /*
        map.put(name,1);
        map.put(phone,-1);

       // multi_key
        map.put(name,1);
        map.put(age,-1);
        size > 1

        linkedMpa
     */

    // 索引内容(定义索引的详细信息)
    private String value;

    // 索引配置项
    private IndexOptions options;

    // 索引名称
    private String indexName;

    // 索引命名空间
    private String namespace;
}
