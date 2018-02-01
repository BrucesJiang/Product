package com.product.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@WebServlet(name = "VerifyCodeServlet", urlPatterns = "/check",initParams = {@WebInitParam(name="VerifyWords", value = "new_words"), @WebInitParam(name =  "encoding",value = "UTF-8")})
public class VerifyCodeServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(VerifyCodeServlet.class);

    private List<String> words = new ArrayList<String>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        String path = config.getInitParameter("VerifyWords");
        String encoding = config.getInitParameter("encoding");
        try{
            URL url = this.getClass().getClassLoader().getResource(path);
            System.out.println(url.getFile());
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(url.getFile()), encoding));
            String line = null;
            while((line = br.readLine()) != null) {
                //System.out.println(line);
                words.add(line);
            }
        }catch(FileNotFoundException e){
            logger.error(e.getMessage(), e);
        }catch(IOException e){
            logger.error(e.getMessage(), e);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 禁止缓存
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", -1);

        int width = 120;
        int height = 30;

        //1. 在内存中绘制一张图片
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //2. 设置图片背景颜色
        Graphics graphics = bi.getGraphics(); // 得到画图对象，也就是画笔
        //设置画笔颜色
        graphics.setColor(getRandColor(200, 250));
        graphics.fillRect(0, 0, width - 1, height - 1);

        //3. 绘制边框
        graphics.setColor(Color.WHITE);
        graphics.drawRect(0, 0, width - 1, height - 1);

        //4. 四个随机数字，获取成语
        Graphics2D graphics2D = (Graphics2D) graphics;

        //设置输出字体
        graphics2D.setFont(new Font("宋体", Font.BOLD, 18));

        Random random = new Random();
        int index = random.nextInt(words.size());
        String word = words.get(index);

        //定义x坐标
        int x = 10;
        for (int i = 0; i < word.length(); i++) {
            //随机颜色
            graphics2D.setColor(new Color(20 + random.nextInt(120), 20 + random.nextInt(110), 20 + random.nextInt(110)));

            //旋转 -30 ~ 30
            int tran = random.nextInt(60) - 30;
            //换算成弧度
            double theta = tran * Math.PI / 180;

            //获取字母数字
            char c = word.charAt(i);

            //将字母输出到图片
            graphics2D.rotate(theta, x, 20);
            graphics2D.drawString(String.valueOf(c), x, 20);
            graphics2D.rotate(-theta, x, 20);
            x += 30;
        }
        //将验证码保存到Session
        //System.out.println("ckCode = " + word);
        request.getSession().setAttribute("ckcode", word);


        //5. 绘制干扰线
        graphics.setColor(getRandColor(160, 200));
        int x1;
        int x2;
        int y1;
        int y2;
        for (int i = 0; i < 30; i++) {
            x1 = random.nextInt(width);
            x2 = random.nextInt(12);
            y1 = random.nextInt(height);
            y2 = random.nextInt(12);
            graphics.drawLine(x1, y1, x1 + x2, x2 + y2);
        }

        //将图片输出到浏览器 ImageIO
        graphics.dispose(); //释放资源
        ImageIO.write(bi, "jpg", response.getOutputStream());
    }

    /**
     * 取其某一范围的color
     *
     * @param fc int 范围参数1
     * @param bc int 范围参数2
     * @return Color
     */
    private Color getRandColor(int fc, int bc) {
        // 取其随机颜色
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
