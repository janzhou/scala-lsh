package org.janzhou.LSH

/**
* Create HASH result by calculate dot product of a random seed and the signature. It is intended to use in conjunction with [[HyperplaneSignatureLSH]].
* @param signature The SignatureLSH used to generature signature vector.
* @param zero Implicit zero value for Type T.
*/
class RepeatingLSH[T <% Number[T]](private val signature:SignatureLSH[T])(implicit zero:T)
extends LSH[T] {
  private val rand = new scala.util.Random(System.nanoTime)
  private val seeds:Iterable[Long] = Array.fill(signature.size)(rand.nextLong())

  def apply(data:Iterable[T]):Long = Number.dot(
    signature(data).map(x => if(x > zero) 1L else 0L),
    seeds
  )
}
