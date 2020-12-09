package com.example.demo.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.model.Account;
import com.example.demo.service.AccountServiceMapper;
import com.example.demo.service.AccountServiceMapper2;
import com.example.demo.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    /*@Autowired
    IAccountService accountService;*/

    @Autowired
    AccountServiceMapper accountServiceMapper;

    @Autowired
    AccountServiceMapper2 accountServiceMapper2;

    @Autowired
    private RestTemplate restTemplate;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /*
     * @Decription: get accounts
     * @CreateDate: Created in 2020/11/29 17:33
     * @Author: WangQ
     * @Modify:
     * @Return:
     * @param: null
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Account> getAccounts() {
        return accountServiceMapper.findAccountList();
    }

    /*
     * @Decription: get account by id
     * @CreateDate: Created in 2020/11/29 17:33
     * @Author: WangQ
     * @Modify:
     * @Return:
     * @param: null
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Account getAccountById(@PathVariable("id") int id) {
        return accountServiceMapper.findAccount(id);
    }

    /*
     * @Decription: update account
     * @CreateDate: Created in 2020/11/29 17:33
     * @Author: WangQ
     * @Modify:
     * @Return:
     * @param: null
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updateAccount(@PathVariable("id") int id, @RequestParam(value = "name", required = true) String name,
                                @RequestParam(value = "money", required = true) double money) {
        Account account = new Account();
        account.setMoney(money);
        account.setName(name);
        account.setId(id);
        int t = accountServiceMapper.update(name, money, id);
        if (t == 1) {
            return account.toString();
        } else {
            return "fail";
        }
    }

    /*
     * @Decription: add
     * @CreateDate: Created in 2020/11/29 17:33
     * @Author: WangQ
     * @Modify:
     * @Return:
     * @param: null
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String postAccount(@RequestParam(value = "name") String name,
                              @RequestParam(value = "money") double money) {
        Account account = new Account();
        account.setMoney(money);
        account.setName(name);
        int t = accountServiceMapper.add(name, money);
        if (t == 1) {
            return account.toString();
        } else {
            return "fail";
        }

    }

    /**
     * mybatis 通过xml文件操作数据库方式
     *
     * @return
     */
    @RequestMapping(value = "/mapper", method = RequestMethod.GET)
    public List<Account> accountmapper() {
        accountServiceMapper2.transfer();
        return accountServiceMapper.findAccountList();
    }

    /*
     * @Decription: RestTemplate实现调用第三方查询天气接口，内容编码为gzip
     * @CreateDate: Created in 2020/11/29 14:42
     * @Author: WangQ
     * @Modify:
     * @Return:
     * @param: null
     */
    @GetMapping("/queryWeather/{name}")
    public JSONObject queryWeather(@PathVariable("name") String name) {
        /**
         * GET请求
         * 1）url: 请求地址；
         * 2）method: 请求类型(如：POST,PUT,DELETE,GET)；
         * 3）requestEntity: 请求实体，封装请求头，请求内容
         * 4）responseType: 响应类型，根据服务接口的返回类型决定
         * 5）uriVariables: url中参数变量值
         */
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        headers.add("Accept-Language", "zh-CN");
        HttpEntity formEntity = new HttpEntity( null ,headers);

        ResponseEntity<String> exchange = restTemplate.exchange("http://wthrcdn.etouch.cn/weather_mini?city="+name, HttpMethod.GET, formEntity, String.class);
        HttpStatus statusCode = exchange.getStatusCode();
        int statusCodeValue = exchange.getStatusCodeValue();
        String storeNumber = exchange.toString();
        String body = exchange.getBody();
        String result = "";
        //因为返回内容Content-Encoding为gzip，要解码
        try {
            logger.info("解码前："+body);
            result = StringUtil.conventFromGzip(body);
            logger.info("解码后："+result);
        } catch (IOException e) {
            logger.error("~~~~~~~~~~~~~~~~~解码失败~~~~~~~~~~~~~~~~~~~~~");
            e.printStackTrace();
        }
        System.out.println(statusCode);
        System.out.println(statusCodeValue);
        System.out.println(storeNumber);
        System.out.println(body);
        JSONObject jsonObject = new JSONObject();
        if(!StringUtils.isEmpty(result)){
            jsonObject = JSONObject.parseObject(result);
        }
        return jsonObject;
    }

    /*
     * @Decription: RestTemplate调用接口
     * @CreateDate: Created in 2020/11/29 17:29
     * @Author: WangQ
     * @Modify:
     * @Return:
     * @param: null
     */
    @GetMapping("/restGet")
    public String restTemplateTest() {
        /**
         * GET请求
         * 1）url: 请求地址；
         * 2）method: 请求类型(如：POST,PUT,DELETE,GET)；
         * 3）requestEntity: 请求实体，封装请求头，请求内容
         * 4）responseType: 响应类型，根据服务接口的返回类型决定
         * 5）uriVariables: url中参数变量值
         */
        ResponseEntity<String> exchange = restTemplate.exchange("http://localhost:9000/account/list", HttpMethod.GET, null, String.class);
        HttpStatus statusCode = exchange.getStatusCode();
        int statusCodeValue = exchange.getStatusCodeValue();
        String storeNumber = exchange.toString();
        String body = exchange.getBody();
        System.out.println(statusCode);
        System.out.println(statusCodeValue);
        System.out.println(storeNumber);
        System.out.println(body);
        return body;
    }

}
