package org.janzhou.LSH

class MinSignatureLSH[T <% Number[T]](private val seeds:Iterable[(T, T)], private val prime:T)
extends SignatureLSH[T] {
  val size = seeds.size
  def apply(data:Iterable[T]):Iterable[T] = seeds.map({ case (a, b) =>
    var min = prime
    data.map(x => Number.hashCode(x, a, b, prime)).foreach(x => if(x < min) min = x)
    min
  })
}
