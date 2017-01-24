package org.janzhou.LSH

class RepeatingLSH[T <% Number[T]](private val signature:SignatureLSH[T])(implicit zero:T)
extends LSH[T] {
  private val rand = new scala.util.Random(System.nanoTime)
  private val seeds:Iterable[Long] = Array.fill(signature.size)(rand.nextLong())

  def apply(data:Iterable[T]):Long = Number.dot(
    signature(data).map(x => if(x > zero) 1L else 0L),
    seeds
  )
}
