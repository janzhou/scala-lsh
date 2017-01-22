package org.janzhou.hash.LSH

import org.janzhou.hash._
import org.janzhou.hash.Number._

class RepeatingLSH[T <% Number[T]](val hashs:Iterable[LSH[T]])(implicit zero:Number[T])
extends LSH[T] {
  private val rand = new scala.util.Random(System.nanoTime)
  private val seeds:Iterable[Long] = Array.fill(hashs.size)(rand.nextLong()).toIterable

  def hash(data:Iterable[T]) = hashs.map(_(data))
  def apply(data:Iterable[T]):Long = hash(data) * seeds
}
