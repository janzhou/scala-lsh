package org.janzhou.hash

abstract class HASH[T <% Number[T]] {
  def apply(data:Iterable[T]):Long
}
