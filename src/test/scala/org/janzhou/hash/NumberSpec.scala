package org.janzhou.hash

import org.scalatest._

class NumberSpec extends FlatSpec with Matchers {
  it should "support dot product" in {
    Number.dot(Array[Int](), Array[Int]()) shouldEqual 0
    Number.dot(Array(1,2), Array(2,1)) shouldEqual 4
    Number.dot(Array(2,2), Array(2,-1)) shouldEqual 2
    Number.dot(Array(2L,2L), Array(2L,1L)) shouldEqual 6L
  }

  it should "support array dot" in {
    import Number._
    Array[Int]() dot Array[Int]() shouldEqual 0
    Array(1,2) * Array(2,1) shouldEqual 4
    Array(2,2) * Array(2,-1) shouldEqual 2
    Array(2L,2L) * Array(2L,1L) shouldEqual 6L
  }

  it should "support iterable dot" in {
    import Number._
    List[Int]() dot List[Int]() shouldEqual 0
    List(1,2) * List(2,1) shouldEqual 4
    List(2,2) * List(2,-1) shouldEqual 2
    List(2L,2L) * List(2L,1L) shouldEqual 6L

    Seq[Int]() dot Seq[Int]() shouldEqual 0
    Seq(1,2) * Seq(2,1) shouldEqual 4
    Seq(2,2) * Seq(2,-1) shouldEqual 2
    Seq(2L,2L) * Seq(2L,1L) shouldEqual 6L
  }
}
