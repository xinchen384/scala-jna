/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package kafka.log

import java.util.Arrays
import java.security.MessageDigest
import java.nio.ByteBuffer

import java.io._
import java.nio._
import java.nio.channels._
import java.util.concurrent.atomic._
import scala.collection.mutable.ArrayBuffer

class MyFileChannel() {
 
  private var fileChannel: FileChannel = null

  def openChannel(file: File, mutable: Boolean, fileAlreadyExists: Boolean = false, initFileSize: Int = 0, preallocate: Boolean = false) = {
    if (mutable) {
      if (fileAlreadyExists)
        fileChannel = new RandomAccessFile(file, "rw").getChannel()
      else {
        if (preallocate) {
          val randomAccessFile = new RandomAccessFile(file, "rw")
          randomAccessFile.setLength(initFileSize)
          fileChannel = randomAccessFile.getChannel()
        }
        else
          fileChannel = new RandomAccessFile(file, "rw").getChannel()
      }
    }
    else
      fileChannel = new FileInputStream(file).getChannel()
  }

  def position(start: Int) =  fileChannel.position(start)
  def read(buffer: ByteBuffer, pos: Int): Int = fileChannel.read(buffer, pos)
  def size(): Long = fileChannel.size()
  def transferTo(pos: Long, count: Long, dest: WritableByteChannel): Long  = fileChannel.transferTo(pos, count, dest)
  def truncate(tSize: Long) = fileChannel.truncate(tSize)
  def force(sign: Boolean) = fileChannel.force(sign)
  def close() = fileChannel.close()
  def write(buffer: ByteBuffer): Int = fileChannel.write(buffer)
  
}
