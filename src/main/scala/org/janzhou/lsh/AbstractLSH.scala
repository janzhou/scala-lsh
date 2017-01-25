package org.janzhou.LSH

/**
 * Abstract LSH. Take Iterable data as input. Generate a Long HASH value.
 * Implemented by [[HyperplaneLSH]], [[RepeatingLSH]] and [[MappingLSH]].
 */
abstract class LSH[T <% Number[T]] {
  def apply(data:Iterable[T]):Long
}

/**
 * Abstract SignatureLSH. Take Iterable data as input. Generate a set of LSH value.
 * Implemented by [[HyperplaneSignatureLSH]](for Cosine/Vector Similarity) and [[MinSignatureLSH]](for Jaccard/Set Similarity).
 */
abstract class SignatureLSH[T <% Number[T]] {
  val size:Int
  def apply(data:Iterable[T]):Iterable[T]
}
