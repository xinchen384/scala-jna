package org.janzhou.native




import java.util.Locale


object CompressionCodec {
  def getCompressionCodec(codec: Int): CompressionCodec = {
    codec match {
      case NoCompressionCodec.codec => NoCompressionCodec
      case GZIPCompressionCodec.codec => GZIPCompressionCodec
      case SnappyCompressionCodec.codec => SnappyCompressionCodec
      case LZ4CompressionCodec.codec => LZ4CompressionCodec
      //case _ => throw new kafka.common.UnknownCodecException("%d is an unknown compression codec".format(codec))
    }
  }
  def getCompressionCodec(name: String): CompressionCodec = {
    name.toLowerCase(Locale.ROOT) match {
      case NoCompressionCodec.name => NoCompressionCodec
      case GZIPCompressionCodec.name => GZIPCompressionCodec
      case SnappyCompressionCodec.name => SnappyCompressionCodec
      case LZ4CompressionCodec.name => LZ4CompressionCodec
      //case _ => throw new kafka.common.UnknownCodecException("%s is an unknown compression codec".format(name))
    }
  }
}

object BrokerCompressionCodec {

  val brokerCompressionCodecs = List(UncompressedCodec, SnappyCompressionCodec, LZ4CompressionCodec, GZIPCompressionCodec, ProducerCompressionCodec)
  val brokerCompressionOptions = brokerCompressionCodecs.map(codec => codec.name)

  def isValid(compressionType: String): Boolean = brokerCompressionOptions.contains(compressionType.toLowerCase(Locale.ROOT))

  def getCompressionCodec(compressionType: String): CompressionCodec = {
    compressionType.toLowerCase(Locale.ROOT) match {
      case UncompressedCodec.name => NoCompressionCodec
      case _ => CompressionCodec.getCompressionCodec(compressionType)
    }
  }

  def getTargetCompressionCodec(compressionType: String, producerCompression: CompressionCodec): CompressionCodec = {
    if (ProducerCompressionCodec.name.equals(compressionType))
      producerCompression
    else
      getCompressionCodec(compressionType)
  }
}

sealed trait CompressionCodec { def codec: Int; def name: String }
sealed trait BrokerCompressionCodec { def name: String }

case object DefaultCompressionCodec extends CompressionCodec with BrokerCompressionCodec {
  val codec = GZIPCompressionCodec.codec
  val name = GZIPCompressionCodec.name
}

case object GZIPCompressionCodec extends CompressionCodec with BrokerCompressionCodec {
  val codec = 1
  val name = "gzip"
}

case object SnappyCompressionCodec extends CompressionCodec with BrokerCompressionCodec {
  val codec = 2
  val name = "snappy"
}

case object LZ4CompressionCodec extends CompressionCodec with BrokerCompressionCodec {
  val codec = 3
  val name = "lz4"
}

case object NoCompressionCodec extends CompressionCodec with BrokerCompressionCodec {
  val codec = 0
  val name = "none"
}

case object UncompressedCodec extends BrokerCompressionCodec {
  val name = "uncompressed"
}

case object ProducerCompressionCodec extends BrokerCompressionCodec {
  val name = "producer"
}
