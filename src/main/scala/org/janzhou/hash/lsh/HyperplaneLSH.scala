package org.janzhou.hash.LSH

import org.janzhou.hash._
import org.janzhou.hash.Number._

class HyperplaneLSH[T <% Number[T]](val seed:Iterable[T])(implicit zero:Number[T]) extends LSH[T] {
  def hash(data:Iterable[T]):T = {
    seed * data
  }

  def apply(data:Iterable[T]):Long = {
    val H = hash(data)
    if ( H > zero.T ) 1 else 0
  }
}
