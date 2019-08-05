package com.netty.server.powerserver;

import com.netty.server.utils.ConvertUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ChannelHandler.Sharable                                        //1
public class EchoServerHandler extends
        ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("已连接");

        ctx.fireChannelActive();

        String channelKey = ctx.channel().remoteAddress().toString();
        System.out.println("获取到的地址和端口号为"+channelKey);


    }
    @Override
    public void channelRead(ChannelHandlerContext ctx,
                            Object msg) {
        ByteBuf in = (ByteBuf) msg;
        System.out.println(msg);                    //2
        ctx.write(msg);                            //3
    }
    //写一个空的buf，并刷新写出区域。完成后关闭sock channel连接。
    /*@Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)//4
                .addListener(ChannelFutureListener.CLOSE);
    }*/

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) {
        System.out.println("aaa");
        cause.printStackTrace();                //5
        ctx.close();                            //6
    }
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("channelInactive");

    }
    public  String stringToGbk(String string) throws Exception
    {
        byte[] bytes = new byte[string.length() / 2];
        for(int i = 0; i < bytes.length; i ++){
            byte high = Byte.parseByte(string.substring(i * 2, i * 2 + 1), 16);
            byte low = Byte.parseByte(string.substring(i * 2 + 1, i * 2 + 2), 16);
            bytes[i] = (byte) (high << 4 | low);
        }
        String result = new String(bytes, "gbk");
        return result;
    }
    public static String unicodeToString(String str) {

        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            //group 6728
            String group = matcher.group(2);
            //ch:'木' 26408
            ch = (char) Integer.parseInt(group, 16);
            //group1 \u6728
            String group1 = matcher.group(1);
            str = str.replace(group1, ch + "");
        }
        return str;
    }

}
