package cn.itcast.solrj.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.taotao.common.bean.EasyUIResult;
import com.taotao.search.pojo.Item;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.junit.Before;

import java.util.List;


public class ItemDataImport {

    private HttpSolrServer httpSolrServer;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
        // 在url中指定core名称：taotao
        //http://solr.taotao.com/#/taotao   -- 界面操作
        String url = "http://solr.taotao.com/taotao";//服务地址
        HttpSolrServer httpSolrServer = new HttpSolrServer(url); //定义solr的server
        httpSolrServer.setParser(new XMLResponseParser()); // 设置响应xml解析器----没有json解析器
        httpSolrServer.setMaxRetries(1); // 设置重试次数，推荐设置为1
        httpSolrServer.setConnectionTimeout(500); // 建立连接的最长时间

        this.httpSolrServer = httpSolrServer;
    }
    
    @org.junit.Test
    public void dbData() throws Exception{
        //通过后台系统的接口查询商品数据
        String url = "http://manage.taotao.com/rest/item?page={page}&rows=100";
        int page=1;
        int pageSize;
//        do {
//            String u = StringUtils.replace(url, "{page}", "" + page);
//            String jsonData=doGet(u);
//            System.out.println(u);
//            JsonNode jsonNode = MAPPER.readTree(jsonData);
//            String rowsStr=jsonNode.get("rows").toString();
//            List<Item> items = MAPPER.readValue(rowsStr,
//                    MAPPER.getTypeFactory().constructCollectionType(List.class, Item.class));
//            pageSize=items.size();
//            this.httpSolrServer.addBeans(items);
//            this.httpSolrServer.commit();
//            page++;
//        } while (pageSize==100);

        do{
            String jsonData = doGet(StringUtils.replace(url, "{page}", "" + page));
            page++;
            EasyUIResult easyUIResult = EasyUIResult.formatToList(jsonData, Item.class);
            List<Item> items=(List<Item>)easyUIResult.getRows();
            pageSize=items.size();
            if(pageSize==0){
                break;
            }
            //将数据写入solr中
            this.httpSolrServer.addBeans(items);
        }while (pageSize==100);
        this.httpSolrServer.commit();
    }

    private String doGet(String url) throws Exception{
        //创建Httpclient对象
        CloseableHttpClient httpClient= HttpClients.createDefault();
        //创建http GET请求
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response=null;

        try {
            //执行请求
            response = httpClient.execute(httpGet);
            //判断返回状态是否为200
            if(response.getStatusLine().getStatusCode()==200){
                return EntityUtils.toString(response.getEntity(), "utf-8");
            }
        } finally {
            if(response!=null){
                response.close();
            }
            httpClient.close();
        }
        return null;
    }
    
}
