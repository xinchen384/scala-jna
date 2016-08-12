package org.janzhou.native

import com.sun.jna._

trait libTest extends Library {
  def helloFromC();
}


