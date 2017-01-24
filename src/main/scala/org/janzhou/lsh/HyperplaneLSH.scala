package org.janzhou.LSH

class HyperplaneLSH[T <% Number[T]](private val seed:Iterable[T])(implicit zero:T) extends LSH[T] {
  def signature(data:Iterable[T]):T = {
    Number.dot(seed, data)
  }

  def apply(data:Iterable[T]):Long = {
    if ( signature(data) > zero ) 1 else 0
  }
}

class HyperplaneSignatureLSH[T <% Number[T]](private val seeds:Iterable[Iterable[T]])(implicit zero:T)
extends SignatureLSH[T] {
  private val hyperplanes = seeds.map(seed => new HyperplaneLSH(seed))
  val size = hyperplanes.size
  def apply(data:Iterable[T]):Iterable[T] = hyperplanes.map(_.signature(data))
}
