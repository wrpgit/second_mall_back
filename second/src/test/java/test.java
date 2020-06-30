import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wrpxcx.POJO.*;
import com.wrpxcx.mapper.GoodsMapper;
import com.wrpxcx.mapper.OrderMapper;
import com.wrpxcx.mapper.UserMapper;
import com.wrpxcx.util.WeChatUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;


public class test {

    //@Resource
    //private UserMapper userMapper;
    @Test
    public void test1(){
        //User user = new User("open2", "海阔天空", "http://www.wrpxcx.com", 1);

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userMapper = (UserMapper) context.getBean("userMapper");
        //userMapper.addUser(user);
        User user = userMapper.getUserByOpenid("123");
        System.out.println(user);

    }

    @Test
    public void goodsTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        GoodsMapper goodsMapper = (GoodsMapper) context.getBean("goodsMapper");

        Goods good = new Goods("124", "124", 134.0, "124", 1
        ,1, "214", "124", 1);
        //goodsMapper.addGoods(goods);
        //goodsMapper.addGoods(good);


        List<Goods> goods3 = goodsMapper.searchGoodsBySchoolIdAndName(0, "1", 0, 6);
        System.out.println(goods3);
        if(1 + 1 == 2)
            return;

        //int schoolId = 1;
        Integer schoolId = 1;
        Integer kindId = 2;
        List<Goods> goods = goodsMapper.getGoodsBySchoolAndKindPage(1, 2,0, 6);
        //List<Goods> goods = goodsMapper.getGoodsBySchoolAndKind(schoolId, kindId);

        String goodsString = JSONObject.toJSONString(goods);
        System.out.println(goodsString);
        System.out.println("hahah");

        //Goods goods1 = goodsMapper.getGoodsByGoodsId(1);
        //System.out.println(goods1);

        //List<Goods> goods_s = goodsMapper.getGoodsBySchoolAndKind(1, 2);

        //System.out.println(goods_s);

        //goods_s = goodsMapper.getGoodsByOpenid("123");
        //System.out.println(goods_s);

        //goodsMapper.deleteGoodsByGoodsId(1);


    }
    @Test
    public void navTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        GoodsMapper goodsMapper = (GoodsMapper) context.getBean("goodsMapper");

        List<GoodsKind> nav = goodsMapper.getNav();
        String navString = JSONObject.toJSONString(nav);
        System.out.println(navString);
    }

    @Test
    public void weChatTest(){
        String en3 = "F7ZZkS9dgB4Jxmsfh6lFJpK4q1UB0RSQpre3pNiZO/a8febul6IGkCXr0x3EZ9GyqXXmpO6ehe9yajVP0fyGtopFHsNF30nvfEM7lOjOxx1bRw6k9NeF3Nsr4ktyXf4tqIsVmkr8/AHxVoEpHUYUZVAHMF58YPMSNDSGpSb164pwwMX/nvYf++XgyOuQh8EAXu5fXT7sgPIaP9gbgS1H5r7A4F2emRZzm9UvDVyjUHnTIE7eL1oySENyHFmlZ6vGYXTo6eK8c5T59JO6GUqk+afk8ZdktvZxEwT5/Udihb+VHnovRlfbE0WiT5NdJFViahcKNJT2nXh5yWMOwSu+0XTFdPh/XcXjb0nxGm8JS7KPMXpFeXbUtC5t7AXWnCePDsJ07X01hEcln0/DYrI4DL94tT7kVRBZ6fN391m77c9KHZhrMYpiR4Yev4cDJSxHFCb3mgvLVFnNr7qkmL3DBE9UvyfseVIOXBol9N2QoLk=\n";
        String session3 = "ps7kKS12JETXRHox95U9Xg==";
        String iv3 = "N4NSC/bQFqU8YzF/EH2edA==";

        System.out.println(System.getProperty("file.encoding"));
        System.setProperty("file.encoding", "GBK");
        System.out.println(System.getProperty("file.encoding"));

        //System.out.println(System.getProperty("file.encoding"));

        //String rest = WeChatUtil.decryptData(en3, session3, iv3);
        //System.out.println(rest);

    }

    @Test
    public void orderTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        OrderMapper orderMapper = (OrderMapper) context.getBean("orderMapper");

        Order order = new Order("oB55s5VwcZjjwjht_WuykmtZneNI", "jskdlf", 1, 1, "1234");

        //orderMapper.addOrder(order);

        //List<OrderDetail> orderDetails = orderMapper.getOrderByBuyOpenid("oB55s5VwcZjjwjht_WuykmtZneNI", 1, 2);
        //System.out.println(orderDetails);

        //String jsonObject = JSONObject.toJSONString(orderDetails);

        //System.out.println(jsonObject);
        OrderDetail orderDetail = orderMapper.getOrderByOrderId(3);
        System.out.println(orderDetail);

    }
}
