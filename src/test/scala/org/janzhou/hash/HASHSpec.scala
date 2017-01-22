package org.janzhou.hash.lsh

import org.janzhou.hash.Number

import org.scalatest._
import scala.io.Source

class HASHSpec extends FlatSpec with Matchers {
  it should "test LSH" in {
    val hash = LSH(2)
    val hash2 = LSH(2)
    hash.hash(Array(2,2)) shouldEqual Number.dot(Array(2,2), hash.seed)
    hash(Array(2,2)) shouldEqual hash(Array(2,2))
    hash(Array(1,2)) shouldEqual hash(Array(1,2))
  }

  def LSHTest(IntHash: Iterable[Int] => Unit){
    IntHash(Array(1,2,3,4))
    IntHash(Array(1,2,3,5))
    IntHash(Array(1,1,3,5))
    IntHash(Array(8,4,30,15))
    IntHash(Array(8,224,129,34))
    IntHash(Array(255,224,129,34))
    IntHash(Array(255,224,229,234))
  }

  it should "hash int min max LSH" in {
    val IntMin = 0
    val IntMax = 255
    val _IntHash = LSH.forInt(4, 1000)
    def IntHash(s:Iterable[Int]){
      val lsh = _IntHash(LSH.move(IntMin, IntMax, s))
      println(s"lsh1($lsh):$s")
    }
    LSHTest(IntHash)
  }

  it should "hash int LSH" in {
    val _IntHash = LSH.forInt(4, 100)
    def IntHash(s:Iterable[Int]){
      val lsh = _IntHash(s)
      println(s"lsh2($lsh):$s")
    }
    LSHTest(IntHash)
  }

  val StrMin:Byte = 48
  val StrMax:Byte = 120
  val _StringHash = LSH.forBytes(64, StrMin, StrMax, 1000)
  def StringHash(s:String){
    val lsh = _StringHash(LSH.move(StrMin, StrMax, s.getBytes))
    println(s"lsh($lsh):$s")
  }

  it should "hash string HyperplaneLSH" in {
    for (line <- Source.fromFile("LICENSE").getLines()) {
      StringHash(line)
    }
  }
}
