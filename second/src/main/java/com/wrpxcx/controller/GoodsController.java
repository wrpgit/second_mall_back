package com.wrpxcx.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.wrpxcx.POJO.Goods;
import com.wrpxcx.POJO.User;
import com.wrpxcx.mapper.GoodsMapper;
import com.wrpxcx.mapper.UserMapper;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class GoodsController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @RequestMapping("/getHomeGoodsPage")
    public void HomeGoods(HttpServletRequest req, HttpServletResponse res){
        res.setContentType("application/json;charset=UTF-8");

        String openid = req.getParameter("openid");
        int kindId = Integer.parseInt(req.getParameter("kindId"));
        int number = Integer.parseInt(req.getParameter("number")); // 每次返回的数量
        int nowNumber = Integer.parseInt(req.getParameter("nowNumber"));     //当前已经显示的数量，查询的结果位 nowNumber后面number的

        System.out.println("openid: " + openid);
        //System.out.println("kindId: " + kindId);
        //System.out.println("number: " + number);
        //System.out.println("nowNumber: " + nowNumber);

        User user = userMapper.getUserByOpenid(openid);

        if(user == null)
            return;
        int schoolId = user.getUserSchool();

        List<Goods> goods = goodsMapper.getGoodsBySchoolAndKindPage(schoolId, kindId, nowNumber, number);

        String goodsString = JSONObject.toJSONString(goods);

        PrintWriter out = null;
        try {
            out = res.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.write(goodsString);
        out.flush();

    }

    @RequestMapping("getMyPublicGoods")
    public void getMyPublic(HttpServletResponse res, HttpServletRequest req){
        res.setContentType("application/json;charset=UTF-8");

        String openid = req.getParameter("openid");
        int number = Integer.parseInt(req.getParameter("number")); // 每次返回的数量
        int nowNumber = Integer.parseInt(req.getParameter("nowNumber"));     //当前已经显示的数量，查询的结果位 nowNumber后面number的
        if(openid == "" || openid == null){
            return;
        }

        List<Goods> goods = goodsMapper.getMyPublicGoodsPage(openid, nowNumber, number);
        String goodsString = JSONObject.toJSONString(goods);

        PrintWriter out = null;
        try {
            out = res.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.write(goodsString);
        out.flush();

    }

    @RequestMapping("getGoodsDetail")
    public void getGoodsDetail(HttpServletRequest req, HttpServletResponse res){

        res.setContentType("application/json;charset=UTF-8");
        String goodsId = req.getParameter("goodsId");

        Goods goods = goodsMapper.getGoodsByGoodsId(Integer.parseInt(goodsId));

        PrintWriter out = null;
        try {
            out = res.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String toJson = JSONObject.toJSONString(goods);
        out.write(toJson);
        out.flush();

    }

    @RequestMapping("/deleteGoods")
    public void deleteGoodsByGoodsId(HttpServletResponse res, HttpServletRequest req){
        String goodsId = req.getParameter("goodsId");
        String openid = req.getParameter("openid");

        Goods goods = goodsMapper.getGoodsByGoodsId(Integer.parseInt(goodsId));

        //防止直接访问接口 恶意删除，虽然很简单。。。
        if(goods.getOpenid().equals(openid)){
            goodsMapper.deleteGoodsByGoodsId(Integer.parseInt(goodsId));
        }
        else{
            System.out.println("删除失败，openid不符");
        }
    }

    @RequestMapping("/updateGoods")
    public void updateGoods(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json;charset=UTF-8");

        String goodsIdStr = req.getParameter("goodsId");
        String goodsName = req.getParameter("name");
        String description = req.getParameter("description");
        String schoolIdStr = req.getParameter("schoolId");
        String kindIdStr = req.getParameter("kindId");
        String priceStr = req.getParameter("price");

        if(goodsIdStr == null || schoolIdStr == null || kindIdStr == null || priceStr == null)
            return;
        Goods goods = goodsMapper.getGoodsByGoodsId(Integer.parseInt(goodsIdStr));
        if(goods == null) {
            System.out.println("没有找到该商品");
            return;
        }

        goods.setDescription(description);
        goods.setGoodsName(goodsName);
        goods.setKindId(Integer.parseInt(kindIdStr));
        goods.setSchoolId(Integer.parseInt(schoolIdStr));
        goods.setPrice(Double.parseDouble(priceStr));

        goodsMapper.updateGoods(goods);
        PrintWriter out = res.getWriter();
        out.write("{\"status\":\"200\"}");
        out.flush();

    }

    @RequestMapping("/searchGoods")
    public void searchGoods(HttpServletResponse res, HttpServletRequest req){
        res.setContentType("application/json;charset=UTF-8");

        int number = Integer.parseInt(req.getParameter("number")); // 每次返回的数量
        int nowNumber = Integer.parseInt(req.getParameter("nowNumber"));     //当前已经显示的数量，查询的结果位 nowNumber后面number的
        String context = req.getParameter("context");
        String openid = req.getParameter("openid");

        User user = userMapper.getUserByOpenid(openid);

        if(user == null)
            return;
        int schoolId = user.getUserSchool();
        List<Goods> goods = goodsMapper.searchGoodsBySchoolIdAndName(schoolId, context, nowNumber, number);
        String goodsString = JSONObject.toJSONString(goods);

        PrintWriter out = null;
        try { out = res.getWriter(); } catch (IOException e) { e.printStackTrace(); }
        out.write(goodsString);
        out.flush();

    }

    @RequestMapping("/addGoods")
    public void addGoods(HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        //允许跨域访问
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        String goodsName= new String();
        String desc = new String();
        String priceStr = new String();
        String schoolIdStr = new String();
        String openid = new String();
        int goodKind = 0;

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前时间：" + sdf.format(d));
        String nowTime = sdf.format(d);
        //System.out.println(bookName + " " + desc + " " + money + " " + openid);

        //得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全

        String savePath = request.getServletContext().getRealPath("/upload");
        //上传时生成的临时文件保存目录
        String tempPath = request.getServletContext().getRealPath("/temp");
        File tmpFile = new File(tempPath);
        if (!tmpFile.exists()) {
            //创建临时目录
            tmpFile.mkdir();
        }

        //消息提示
        String message = "";
        try{
            //使用Apache文件上传组件处理文件上传步骤：
            //1、创建一个DiskFileItemFactory工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。
            factory.setSizeThreshold(1024*100);//设置缓冲区的大小为100KB，如果不指定，那么缓冲区的大小默认是10KB
            //设置上传时生成的临时文件的保存目录
            factory.setRepository(tmpFile);
            //2、创建一个文件上传解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
            //监听文件上传进度
            upload.setProgressListener(new ProgressListener(){
                public void update(long pBytesRead, long pContentLength, int arg2) {
                    System.out.println("文件大小为：" + pContentLength + ",当前已处理：" + pBytesRead);
                    /**
                     * 文件大小为：14608,当前已处理：4096
                     文件大小为：14608,当前已处理：7367
                     文件大小为：14608,当前已处理：11419
                     文件大小为：14608,当前已处理：14608
                     */
//	                                float f = pBytesRead/pContentLength;
//	                                try {
//	                                    response.getWriter().write(f+"");
//	                                } catch (IOException e) {
//	                                    // TODO Auto-generated catch block
//	                                    e.printStackTrace();
//	                                }

                }
            });
            //解决上传文件名的中文乱码
            upload.setHeaderEncoding("UTF-8");
            //3、判断提交上来的数据是否是上传表单的数据
            if(!ServletFileUpload.isMultipartContent(request)){
                //按照传统方式获取数据
                return;
            }

            //设置上传单个文件的大小的最大值，目前是设置为1024*1024字节，也就是1MB
            upload.setFileSizeMax(1024*1024);
            //设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为10MB
            upload.setSizeMax(1024*1024*10);
            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = upload.parseRequest(request);
            for(FileItem item : list){
                //如果fileitem中封装的是普通输入项的数据
                if(item.isFormField()){
                    String name = item.getFieldName();
                    //解决普通输入项的数据的中文乱码问题
                    String value = item.getString("UTF-8");
                    if(name.equals("openid")) {
                        openid = value;
//	                                	System.out.println("test openid" + openid);
                    } else if(name.equals("desc")) {
                        desc = value;
//	                                	System.out.println("test desc" + desc);
                    } else if(name.equals("price")) {
                        priceStr = value;
                    } else if(name.equals("kind")) {
                        goodKind = Integer.parseInt(value);
                    } else if(name.equals("name")) {
                        goodsName = value;
                    }
                     else if(name.equals("schoolId")) {
                        schoolIdStr = value;
                    }

                //value = new String(value.getBytes("iso8859-1"),"UTF-8");
                    System.out.println(name + "=" + value);
                }else{//如果fileitem中封装的是上传文件
                    //得到上传的文件名称，
                    String filename = item.getName();
                    System.out.println(filename);
                    if(filename==null || filename.trim().equals("")){
                        continue;
                    }
                    //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    filename = filename.substring(filename.lastIndexOf("/")+1);
                    //得到上传文件的扩展名
                    String fileExtName = filename.substring(filename.lastIndexOf(".")+1);
                    //如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
                    System.out.println("上传的文件的扩展名是："+fileExtName);
                    //获取item中的上传文件的输入流
                    InputStream in = item.getInputStream();
                    //得到文件保存的名称
                    String saveFilename = makeFileName(filename);
                    //得到文件的保存目录
                    String realSavePath = makePath(saveFilename, savePath);
                    System.out.println(realSavePath);
                    //创建一个文件输出流
                    FileOutputStream out = new FileOutputStream(realSavePath + "/" + saveFilename);
                    //创建一个缓冲区
                    byte buffer[] = new byte[1024];
                    //判断输入流中的数据是否已经读完的标识
                    int len = 0;
                    //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                    while((len=in.read(buffer))>0){
                        //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                        out.write(buffer, 0, len);
                    }
                    //关闭输入流
                    in.close();
                    //关闭输出流
                    out.close();
                    //删除处理文件上传时生成的临时文件
                    item.delete();
                    message = "文件上传成功！";

                    User user = userMapper.getUserByOpenid(openid);

                    System.out.println("userName：  " + user.getUserName());
                    //String bookSchool = user.getUserSchool();

                    Double price = 0.0;
                    System.out.println("price" + priceStr);
                    System.out.println("schoolId: " + schoolIdStr);

                    int schoolId = 0;
                    try {
                        price = Double.parseDouble(priceStr);
                        schoolId = Integer.parseInt(schoolIdStr);
                    } catch (Exception e) {
                        System.out.println("捕获到了异常 在219行左右");
                        // TODO: handle exception
                    }

                    System.out.println("test realPath: " + realSavePath);
                    int pos = realSavePath.indexOf("upload");
                    realSavePath = realSavePath.substring(pos);
                    //realSavePath = realSavePath.replace("E:\\save_java\\mooc\\src\\main\\webapp\\WEB-INF\\", "");
                    realSavePath += "/" + saveFilename;
                    System.out.println("修改后的path：" + realSavePath);
                    //Goods goods = new Goods(openid, user.getUserName(), bookName, realSavePath, 0, desc, mon, "bookSchool", bookKind);
                    Goods goods = new Goods(openid, goodsName, price, desc, schoolId, goodKind,realSavePath, nowTime, 1);

                    goodsMapper.addGoods(goods);

                    PrintWriter outer = response.getWriter();
                    outer.write("{msg:'success',code:'200'}");
                    outer.flush();

                }
            }
        }catch (FileUploadBase.FileSizeLimitExceededException e) {
            e.printStackTrace();
            request.setAttribute("message", "单个文件超出最大值！！！");
            //request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        }catch (FileUploadBase.SizeLimitExceededException e) {
            e.printStackTrace();
            request.setAttribute("message", "上传文件的总的大小超出限制的最大值！！！");
            //request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        }catch (Exception e) {
            message= "文件上传失败！";
            e.printStackTrace();
        }
        request.setAttribute("message",message);

        // request.getRequestDispatcher("/message.jsp").forward(request, response);
    }

    /**
     * @Method: makeFileName
     * @Description: 生成上传文件的文件名，文件名以：uuid+"_"+文件的原始名称
     * @Anthor:孤傲苍狼
     * @param filename 文件的原始名称
     * @return uuid+"_"+文件的原始名称
     */
    private String makeFileName(String filename){  //2.jpg
        //为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
        //return UUID.randomUUID().toString() + "_" + filename;
        String t = filename.substring(filename.length() - 20, filename.length());  //取后20位保存
        return t;
    }

    /**
     * 为防止一个目录下面出现太多文件，要使用hash算法打散存储
     *
     * @param filename 文件名，要根据文件名生成存储目录
     * @param savePath 文件存储路径
     */
    private String makePath(String filename,String savePath){
        //得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
        int hashcode = filename.hashCode();
        int dir1 = hashcode&0xf;  //0--15
        int dir2 = (hashcode&0xf0)>>4;  //0-15
        //构造新的保存目录
        String dir = savePath + "/" + dir1 + "/" + dir2;  //upload\2\3  upload\3\5
        //File既可以代表文件也可以代表目录
        File file = new File(dir);
        //如果目录不存在
        if(!file.exists()){
            //创建目录
            file.mkdirs();
        }
        return dir;
    }
}
