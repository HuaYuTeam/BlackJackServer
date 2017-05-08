package org.game.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.game.controller.DataController;

/**
 * 说明：处理器
 *
 * @author <a href="http://www.waylau.com">waylau.com</a> 2015年11月7日 
 */
public class ServerHandler extends SimpleChannelInboundHandler<Object> {

	ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	private DataController dataController;

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		Channel newComing = ctx.channel();
		channels.add(newComing);
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object obj)
			throws Exception {
		dataController = new DataController(obj).parseData().doAction();
		ctx.writeAndFlush(dataController.returnData());
		/*String jsonString = "";

		if (obj instanceof GameData) {
			GameData user = (GameData)obj;
			
			ctx.writeAndFlush(user);

			jsonString = JacksonMapper.getInstance().writeValueAsString(user); // 对象转为json字符串

		} else {
			ctx.writeAndFlush(obj);
			jsonString = JacksonMapper.getInstance().writeValueAsString(obj); // 对象转为json字符串
		}
		
		System.out.println("Server get msg form Client -" + jsonString);*/
	}
	
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { 
    	Channel incoming = ctx.channel();
		System.out.println("Client:"+incoming.remoteAddress()+"异常");
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}