package com.util.validcode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 生成验证码图片的工具类
 */
public class ValidCodeUtil {

    private static final Random random = new Random();

    /**
     * 生成图片, 并将验证码放入session中
     *
     * @param request request对象, 从这里获取session
     * @param width   图片宽
     * @param height  图片高
     * @return
     * @throws Exception
     */
    public static BufferedImage drawValidCode(HttpServletRequest request, HttpServletResponse response, int width, int height) throws Exception {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        //响应类型为图片类型的加上这一句
        response.setContentType("image/jpeg");
        //设置响应头控制浏览器不缓存图片数据
        response.setDateHeader("expries", -1);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");

        BufferedImage bufImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //也可以使用bufImg.getGraphics(), get是返回一个Graphics对象, create是返回一个Graphics2D对象
        Graphics2D g = bufImg.createGraphics();
        //画出图片验证码的背景(200到250是一个浅色调的背景)
        g.setColor(ColorUtils.getRandColor(200, 250));
        //填充一个矩形
        g.fillRect(0, 0, width, height);
        //画出图片验证码的干扰线, 给出两个点的坐标, 两点确定一条直线
        for (int i = 0; i < 10; i++) {
            g.setColor(ColorUtils.getRandColor(160, 200));
            int x1 = random.nextInt(width / 2);
            int y1 = random.nextInt(height);
            int x2 = random.nextInt(width);
            int y2 = random.nextInt(height);
            g.drawLine(x1, y1, x2, y2);
        }
        //画一个边框
        g.setColor(Color.RED);
        //四个参数(前面两个是起笔位置, 后面两个是宽高)
        g.drawRect(1, 1, width - 3, height - 3);
        //画出图片验证码的内容
        String data = getCodeContent(request.getSession());
        int _x = 5;
        for (int i = 0; i < data.length(); i++) {
            //定义旋转的弧度
            int deg = random.nextInt() % 30;
            g.setColor(ColorUtils.getRandColor(20, 130));// 20到130之间是深色调
            g.rotate(deg * Math.PI / 180, _x, 20);
            //g.rotate(deg*Math.PI/180);
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
            g.drawString(data.charAt(i) + "", _x, 20);
            //这里必须设置一个恢复旋转角度的代码, 否则后面的内容会显示到边框的外面
            g.rotate(-deg * Math.PI / 180, _x, 20);
            //g.rotate(-deg*Math.PI/180);
            _x += width / data.length();
        }
        return bufImg;
    }

    /**
     * 获取随机的验证码内容(字母加数字)
     * 同时将验证码放入session中
     */
    static String getCodeContent(HttpSession session) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder code = new StringBuilder();
        for (int x = 1; x <= 4; x++) {
            Random a = new Random();
            int m = a.nextInt(str.length());
            code.append(str.charAt(m));
        }
        session.setAttribute("validCode", code.toString());
        return code.toString();
    }
}
