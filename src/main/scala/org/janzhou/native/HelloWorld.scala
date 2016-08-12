package org.janzhou.native

import com.sun.jna.Library
import com.sun.jna.Native
import java.nio.ByteBuffer
import java.io.File


// http://stuf.ro/calling-c-code-from-java-using-jna
// https://janzhou.org/2015/09/call-c-function-in-java-scala/
// use JNI http://hohonuuli.blogspot.com/2013/08/a-simple-java-native-interface-jni.html
object HelloWorld {
    def main(args: Array[String]){ 
        //Native.loadLibrary("FileGDBAPI", PointShapeBuffer.class);
        val libt = Native.loadLibrary("ctest", classOf[libTest]).asInstanceOf[libTest]
        libt.helloFromC()
        println("Hello World!")
        /*
        val mysegment = new FileMessageSet( new File("first_segment"))
        val msg: String = " test1\n "
        val byteBuffer = ByteBuffer.wrap(msg.getBytes() )
        mysegment.append(byteBuffer)
        mysegment.append(byteBuffer)
        println( "size of bytebuffer: " + byteBuffer.limit )

        //val ioBufferSize = 1000
       // var readBuffer = ByteBuffer.allocate(ioBufferSize)
       // mysegment.readInto(readBuffer, 0)

        val builder = new StringBuilder()
        builder.append(getClass.getSimpleName + "(")
        //val iter = mysegment.read(0, 100).iterator(100).iterator
        val iter = mysegment.iterator
        var i = 0
        while(iter.hasNext && i < 100) {
          val message = iter.next
          builder.append(message)
          if(iter.hasNext)
            builder.append(", ")
          i += 1
        }
        println( "read results: "+ builder.toString )
        */
 
        }
    
}



