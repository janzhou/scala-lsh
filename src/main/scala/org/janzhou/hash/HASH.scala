package org.janzhou.hash

import org.janzhou.hash.lsh._

abstract class HASH[T <% Number[T]] {
  def apply(data:Iterable[T])(implicit zero:Number[T]):T
}
