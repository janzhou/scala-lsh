package org.janzhou.hash.lsh

import org.janzhou.hash._
import org.janzhou.hash.Number._

abstract class LSH[T <% Number[T]] extends HASH[T]

object LSH extends NumberConversions {
  val rand = new scala.util.Random(System.nanoTime)

  def apply(dim:Int):HyperplaneLSH[Int] = new HyperplaneLSH(Array.fill(dim)(rand.nextInt))

  private def seed(dim:Int, min:Int, max:Int):Iterable[Int] = {
    val range = max - min
    val mid = range / 2
    Array.fill(dim)(rand.nextInt(range) - mid).toIterable
  }

  private def seed(dim:Int):Iterable[Int] = {
    Array.fill(dim)(rand.nextGaussian()).map(x => if (x<0) -1 else 1).toIterable
  }

  def move(min:Int, max:Int, data:Iterable[Int]) = {
    val mid = (max + min)/2
    data.map(_ - mid)
  }

  def forInt(dim:Int, min:Int, max:Int, repeating:Int):RepeatingLSH[Int] = {
    val hashs = Array.fill(repeating)(
      seed(dim, min, max)
    ).map(seed => new HyperplaneLSH(seed))

    new RepeatingLSH(hashs)
  }

  def forInt(dim:Int, repeating:Int):RepeatingLSH[Int] = {
    val hashs = Array.fill(repeating)(
      seed(dim)
    ).map(seed => new HyperplaneLSH(seed))

    new RepeatingLSH(hashs)
  }

  def move(min:Byte, max:Byte, data:Iterable[Byte]) = {
    val mid = (max + min)/2
    data.map(x => (x - mid).toByte)
  }

  def forBytes(dim:Int, min:Byte, max:Byte, repeating:Int):RepeatingLSH[Byte] = {
    val hashs = Array.fill(repeating)(
      seed(dim, min, max).map(_.toByte)
    ).map(seed => new HyperplaneLSH(seed))

    new RepeatingLSH(hashs)
  }

  def forBytes(dim:Int, repeating:Int):RepeatingLSH[Byte] = {
    val hashs = Array.fill(repeating)(
      seed(dim).map(_.toByte)
    ).map(seed => new HyperplaneLSH(seed))

    new RepeatingLSH(hashs)
  }
}
