package org.janzhou.LSH

/**
 * HyperplaneLSH implementation. Use on dataset with Cosine/Vector Similarity.
 * @param seed Randomly generated seed.
 */
class HyperplaneLSH[T <% Number[T]](private val seed:Iterable[T])(implicit zero:T) extends LSH[T] {
  def signature(data:Iterable[T]):T = {
    Number.dot(seed, data)
  }

  def apply(data:Iterable[T]):Long = {
    if ( signature(data) > zero ) 1 else 0
  }
}

/**
 * Generate Hyperplane signature. Use on dataset with Cosine/Vector Similarity.
 * @param seeds A number of randomly generated seeds. The size of the output signature will be equal to the number of seeds.
 */
class HyperplaneSignatureLSH[T <% Number[T]](private val seeds:Iterable[Iterable[T]])(implicit zero:T)
extends SignatureLSH[T] {
  private val hyperplanes = seeds.map(seed => new HyperplaneLSH(seed))
  val size = hyperplanes.size
  def apply(data:Iterable[T]):Iterable[T] = hyperplanes.map(_.signature(data))
}
