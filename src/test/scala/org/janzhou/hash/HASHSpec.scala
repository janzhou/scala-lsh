package org.janzhou.test

import org.janzhou.LSH

import org.scalatest._
import scala.io.Source

class HASHSpec extends FlatSpec with Matchers with LSH.NumberConversions {
  it should "test LSH" in {
    val hash = LSH.hyperplane(2)
    val hash2 = LSH.hyperplane(2)
    hash(Array(2,2)) shouldEqual hash(Array(2,2))
    hash(Array(1,2)) shouldEqual hash(Array(1,2))
  }
}

class LSHSpec extends FlatSpec with Matchers {
  val IntMin = 0
  val IntMax = 255

  def LSHTest(IntHash: Iterable[Int] => Unit){
    IntHash(Array(1,2,3,4))
    IntHash(Array(1,2,3,5))
    IntHash(Array(1,1,3,5))
    IntHash(Array(8,4,30,15))
    IntHash(Array(8,224,129,34))
    IntHash(Array(255,224,129,34))
    IntHash(Array(255,225,129,34))
    IntHash(Array(255,225,139,34))
    IntHash(Array(255,224,229,234))
  }

  it should "hash int Gaussian LSH" in {
    val _IntHash = LSH.forInt(4, 100)
    def IntHash(s:Iterable[Int]){
      val hash = _IntHash(LSH.move(IntMin, IntMax, s))
      println(s"Gaussian LSH($hash):$s")
    }
    LSHTest(IntHash)
  }

  it should "hash int MinMax LSH" in {
    val _IntHash = LSH.forInt(4, IntMin, IntMax, 100)
    def IntHash(s:Iterable[Int]){
      val hash = _IntHash(LSH.move(IntMin, IntMax, s))
      println(s"MinMax LSH($hash):$s")
    }
    LSHTest(IntHash)
  }

  def SimilarityTest(IntSignature: Iterable[Int] => Iterable[Int], a:Iterable[Int], b:Iterable[Int]){
    val hasha = IntSignature(a)
    val hashb = IntSignature(b)
    val sim1 = LSH.Cosine(hasha, hashb)
    val sim2 = LSH.Cosine(a, b)
    println(s"[$sim1, $sim2] LSH($hasha):$a LSH($hashb):$b")
  }

  it should "Get Similarity LSH" in {
    val _IntSignature = LSH.forIntSignature(8, 3)
    def IntSignature(data:Iterable[Int]) = _IntSignature(data)

    SimilarityTest(IntSignature, Array(1,2,3,4,1,2,3,4), Array(1,2,3,4,1,2,3,4))
    SimilarityTest(IntSignature, Array(1,2,3,4,9,2,3,4), Array(1,2,3,4,1,2,3,4))
    SimilarityTest(IntSignature, Array(1,91,3,4,1,132,-3,4), Array(1,2,3,4,1,2,3,4))
    SimilarityTest(IntSignature, Array(51,2,43,14,211,82,3,4), Array(1,2,3,4,1,2,3,4))
  }
}
