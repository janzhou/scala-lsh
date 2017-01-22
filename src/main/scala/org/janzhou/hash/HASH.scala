package org.janzhou.hash

abstract class HASH[T <% Number[T]] {
  def apply(data:Iterable[T]):Long
}

abstract class SignatureHASH[T <% Number[T]] {
  val size:Int
  def apply(data:Iterable[T]):Iterable[T]
}
