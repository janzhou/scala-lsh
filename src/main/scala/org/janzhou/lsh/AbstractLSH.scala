package org.janzhou.LSH

abstract class LSH[T <% Number[T]] {
  def apply(data:Iterable[T]):Long
}

abstract class SignatureLSH[T <% Number[T]] {
  val size:Int
  def apply(data:Iterable[T]):Iterable[T]
}
