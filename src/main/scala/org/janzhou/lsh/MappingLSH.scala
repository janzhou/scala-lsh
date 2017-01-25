package org.janzhou.LSH

/**
 * Convert a signature to Long type. It is intended to use in conjunction with [[MinSignatureLSH]].
 */
class MappingLSH[T <% Number[T]](private val signature: SignatureLSH[T], private val prime:T)
extends LSH[T] {
  def apply(data:Iterable[T]):Long = {
    var ret:Long = 0L
    signature(data).map(_.##).foreach(v => {
      val pos = v % 64
      ret = ret & ( 1L << pos )
    })
    ret
  }
}
