package org.janzhou.hash.lsh

import org.janzhou.hash._
import org.janzhou.hash.Number._

class HyperplaneLSH[T <% Number[T]](val seed:Iterable[T]) extends HASH[T] {
  def apply(data:Iterable[T])(implicit zero:Number[T]):T = {
    seed * data
  }
}

object HyperplaneLSH {
  def apply(seed:Array[Int]) = new HyperplaneLSH(seed)
}
