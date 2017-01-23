package org.janzhou

/**
 * Locality Sensitive Hashing (LSH) is a family of hashing methods that tent to produce the same hash (or signature) for similar items.
 *
 * {{{
 * import org.janzhou.LSH
 *
 * val hash = LSH.forInt(dimension, minValue, maxValue, repeating)
 * hash(LSH.move(minValue, maxValue, Array(...)))
 * }}}
*/
package object LSH extends LSHHelper
