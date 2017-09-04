package com.oa.core.utils;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;


/**
 * 登录用的工具类,如验证码图片生成
 */
public class LoginUtil {

    /**
     * 创建验证码图片
     */
    public static BufferedImage createCodeImage(String code, int width, int height) {
        Random random = new Random();
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) bi.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        char[] codes = code.toCharArray();

        // 创建字体
        Font fontFormat = new Font("Tahoma", Font.BOLD, 24);//英文网站常用字体,为更好区分1IILOo等


        g.setColor(getRandColor(220, 250));
        g.fillRect(0, 0, width, height);

        for (int i = 0; i < 100; i++) {

            // 改变图形的当前颜色为随机生成的颜色
            g.setColor(getRandColor(0, 200));

            // // 画出背景图(圆角)前端已实现,效果优于这里
            // g.fillRoundRect(0, 0, width, height,10 , 10);

            // 画出背景图
            g.fillOval(random.nextInt(width), random.nextInt(height), random.nextInt(3), random.nextInt(3));

        }


        // 防止最后一个字超出图片边缘
        int tempWidth = width - fontFormat.getSize() / 2 - 5 / 2 * 3;
        if (codes.length > 0) {
            for (int i = 0; i < codes.length; i++) {

			/*
             * 随机缩放文字并将文字旋转指定角度
			 *
			 * 画一个字旋转缩放一次,旋转轴以当前所画的字的中心
			 */

                // 将文字旋转指定角度
                AffineTransform tempTrans = g.getTransform();

                // 改变图形的当前颜色为随机生成的颜色
                g.setColor(getRandColor(50, 150));
                g.setFont(fontFormat);

                AffineTransform trans = new AffineTransform();
                // 旋转正负45度.以文字中心为轴
                trans.rotate((random.nextBoolean() ? -1 : 1) * random.nextInt(10) * Math.PI / 180, tempWidth / codes.length * i + i * 5 + fontFormat.getSize() / 2, height / 2 + fontFormat.getSize() / 2);

                // 缩放文字
                float scaleSize = random.nextFloat() * 0.2f + 0.8f;
                trans.scale(scaleSize, scaleSize);
                g.setTransform(trans);


                // 将文字花在图片上
                g.drawString(String.valueOf(codes[i]), tempWidth / codes.length * i + i * 5, height / 2 + fontFormat.getSize() / 2);


                g.setTransform(tempTrans);

            }
        }


        int size = 5;
        for (int i = 0; i < size; i++) {
            g.setColor(getRandColor(150, 200));
            g.drawLine(random.nextInt(width / 4), random.nextInt(height / 4 * 3) + height / 4, random.nextInt(width / 4 * 3) + width / 4, random.nextInt(height));
        }

/*		int[] xPoints = new int[size];
		int[] yPoints = new int[size];
		for (int j = 0; j < size; j++) {
			xPoints[j] = random.nextInt(width - 1);
			yPoints[j] = random.nextInt(height - 1);
			g.setColor(getRandColor(0, 100));
			g.drawLine(random.nextInt(width / 2), random.nextInt(height / 2), random.nextInt(width / 2) + width / 2, random.nextInt(height / 2) + height / 2);
		}*/
//		 改变图形的当前颜色为随机生成的颜色
       /* g.setColor(getRandColor(0, 100));
        g.drawPolyline(xPoints, yPoints, size);*/

        // 释放画笔
        g.dispose();
        return bi;
    }

    /**
     * 获取随机颜色
     *
     * @param s 开始色0~255
     * @param e 结束色0~255
     */
    public static Color getRandColor(int s, int e) {
        Random random = new Random();
        if (s > 255)
            s = 255;
        if (e > 255)
            e = 255;
        int r = s + random.nextInt(e - s);
        int g = s + random.nextInt(e - s);
        int b = s + random.nextInt(e - s);
        return new Color(r, g, b);
    }

    /**
     * 生成验证码
     *
     * @param len 验证码长度
     */
    public static String getRandCode(int len) {
        StringBuilder sb = new StringBuilder();
        char[] ignore = new char[]{'0', 'o', 'O', '1', 'I', 'L', 'l'};
        for (int i = 0; i < len; i++) {
            char charset = getRandCode();

            for (int j = 0; j < ignore.length; j++) {
                if (charset == ignore[j]) {
                    i--;
                    break;
                }
                //最后一个都不有 break ,则不忽略
                if (j == ignore.length - 1)
                    sb.append(charset);
            }

        }
        return sb.toString();
    }

    /**
     * 创建单个随机字符
     */
    public static char getRandCode() {
        Random rand = new Random();

        char oneCode = (char) (rand.nextInt(26) + 'A');
        char twoCode = (char) (rand.nextInt(26) + 'a');
        char threeCode = (char) (rand.nextInt(9) + '0');

        switch (rand.nextInt(3) + 1) {
            case 1:
                return oneCode;
            case 2:
                return twoCode;
            case 3:
                return threeCode;
            default:
                return oneCode;
        }
    }
}
