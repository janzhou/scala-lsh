package org.janzhou.hash.LSH

import org.janzhou.hash._
import org.janzhou.hash.Number._

class RepeatingLSH[T <% Number[T]](val signature:HyperplaneSignatureLSH[T])(implicit zero:Number[T])
extends LSH[T] {
  private val rand = new scala.util.Random(System.nanoTime)
  private val seeds:Iterable[Long] = Array.fill(signature.size)(rand.nextLong()).toIterable

  def apply(data:Iterable[T]):Long = signature(data).map(x => if(x > zero.T) 1L else 0L) * seeds
}
