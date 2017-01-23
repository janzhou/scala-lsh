package org.janzhou.LSH

trait LSHHelper extends NumberConversions {
  val rand = new scala.util.Random(System.nanoTime)

  def hyperplane(dim:Int):HyperplaneLSH[Int] = new HyperplaneLSH(Array.fill(dim)(rand.nextInt))

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
    val signature = new HyperplaneSignatureLSH(Array.fill(repeating)(
      seed(dim, min, max)
    ))

    new RepeatingLSH(signature)
  }

  def forInt(dim:Int, repeating:Int):RepeatingLSH[Int] = {
    val signature = new HyperplaneSignatureLSH(Array.fill(repeating)(
      seed(dim)
    ))

    new RepeatingLSH(signature)
  }
}