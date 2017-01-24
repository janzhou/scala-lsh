package org.janzhou.LSH

/**
 * Define Number operations required for LSH.
 * To support custom data types, you must defind implicit conversion and implicit zero value.
 */
trait Number[T] {
  def +(x: T): T
  def -(x: T): T
  def *(x: T): T
  def %(x: T): T
  def /(x: T): Double
  def >(x: T): Boolean
  def <(x: T): Boolean
}

class NumberDouble(val self:Double) extends Number[Double] {
  def +(x: Double): Double = self + x
  def -(x: Double): Double = self - x
  def *(x: Double): Double = self * x
  def %(x: Double): Double = self % x
  def /(x: Double): Double = self / x
  def >(x: Double): Boolean = self > x
  def <(x: Double): Boolean = self < x
}

class NumberFloat(val self:Float) extends Number[Float] {
  def +(x: Float): Float = self + x
  def -(x: Float): Float = self - x
  def *(x: Float): Float = self * x
  def %(x: Float): Float = self % x
  def /(x: Float): Double = self.toDouble / x.toDouble
  def <(x: Float): Boolean = self < x
  def >(x: Float): Boolean = self > x
}

class NumberLong(val self:Long) extends Number[Long] {
  def +(x: Long): Long = self + x
  def -(x: Long): Long = self - x
  def *(x: Long): Long = self * x
  def %(x: Long): Long = self % x
  def /(x: Long): Double = self.toDouble / x.toDouble
  def <(x: Long): Boolean = self < x
  def >(x: Long): Boolean = self > x
}

class NumberInt(val self:Int) extends Number[Int] {
  def +(x: Int): Int = self + x
  def -(x: Int): Int = self - x
  def *(x: Int): Int = self * x
  def %(x: Int): Int = self % x
  def /(x: Int): Double = self.toDouble / x.toDouble
  def <(x: Int): Boolean = self < x
  def >(x: Int): Boolean = self > x
}

object Number {
  def dot[T <% Number[T]](a: Iterable[T], b: Iterable[T])(implicit zero: T):T = {
    a.zip(b).map({ case (x, y) => x*y }).foldLeft(zero)(_+_)
  }

  def hashCode[T <% Number[T]](x: T, a: T, b: T, prime: T):T = {
    (a * x + b ) % prime
  }
}

trait NumberConversions {

  import scala.language.implicitConversions

  implicit class NumberArray[T <% Number[T]](val self: Array[T])(implicit zero: T){
    def *(that: Array[T]):T = dot(that)
    def dot(that: Array[T]):T = Number.dot(self, that)
  }

  implicit class NumberIterable[T <% Number[T]](val self: Iterable[T])(implicit zero: T){
    def *(that: Iterable[T]):T = dot(that)
    def dot(that: Iterable[T]):T = Number.dot(self, that)
  }

  implicit def _Double2DumberDouble(x:Double):Number[Double] = new NumberDouble(x)
  implicit def _NumberDouble2Double(x:NumberDouble):Double = x.self
  implicit val _ZeroDouble:Double = 0

  implicit def _Float2NumberFloat(x:Float):Number[Float] = new NumberFloat(x)
  implicit def _NumberFloat2Float(x:NumberFloat):Float = x.self
  implicit val _ZeroFloat:Float = 0

  implicit def _Long2NumberLong(x:Long):Number[Long] = new NumberLong(x)
  implicit def _NumberLong2Long(x:NumberLong):Long = x.self
  implicit val _ZeroLong:Long = 0

  implicit def _Int2NumberInt(x:Int):Number[Int] = new NumberInt(x)
  implicit def _NumberInt2Int(x:NumberInt):Int = x.self
  implicit val _ZeroInt:Int = 0
}
