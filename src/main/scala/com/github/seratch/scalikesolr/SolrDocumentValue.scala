/*
 * Copyright 2011 Kazuhiro Sera
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package com.github.seratch.scalikesolr

import scala.beans.BeanProperty
import scala.collection.JavaConverters._
import org.joda.time.{ DateTime, LocalDate, LocalTime }
import java.text.SimpleDateFormat
import java.util.{ Locale, Date, Calendar }

case class SolrDocumentValue(@BeanProperty val rawValue: String) {

  def toListOrElse(defaultValue: List[String]): List[String] = {
    try {
      // List(book, hardcover)
      val values = rawValue.replaceFirst("List\\(", "").replaceFirst("\\)", "")
      values.split(",").toList
    } catch {
      case _: Throwable => defaultValue
    }
  }

  def toListInJavaOrElse(defaultValue: java.util.List[String]): java.util.List[String] = {
    try {
      toListOrElse(if (defaultValue == null) Nil else defaultValue.asScala.toList).asJava
    } catch {
      case _: Throwable => defaultValue
    }
  }

  def toDateOrElse(defaultValue: Date): Date = {
    try {
      toDateTimeOrElse(null) match {
        case null => defaultValue
        case dateTime => dateTime.toDate
      }
    } catch {
      case _: Throwable => defaultValue
    }
  }

  def toCalendarOrElse(defaultValue: Calendar): Calendar = {
    try {
      val date = toDateOrElse(null)
      val cal = Calendar.getInstance()
      cal.setTime(date)
      cal
    } catch {
      case _: Throwable => defaultValue
    }
  }

  def toDateTimeOrElse(defaultValue: DateTime): DateTime = {
    try {
      new DateTime(rawValue)
    } catch {
      case _: Throwable =>
        try {
          val dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH)
          new DateTime(dateFormat.parse(rawValue))
        } catch {
          case _: Throwable => defaultValue
        }
    }
  }

  def toLocalTimeOrElse(defaultValue: LocalTime): LocalTime = {
    try {
      toDateTimeOrElse(null).toLocalTime
    } catch {
      case _: Throwable => defaultValue
    }
  }

  def toLocalDateOrElse(defaultValue: LocalDate): LocalDate = {
    try {
      toDateTimeOrElse(null).toLocalDate
    } catch {
      case _: Throwable => defaultValue
    }
  }

  def toBooleanOrElse(defaultValue: Boolean): Boolean = {
    try {
      rawValue.toBoolean
    } catch {
      case _: Throwable => defaultValue
    }
  }

  def toByteOrElse(defaultValue: Byte): Byte = {
    try {
      rawValue.toByte
    } catch {
      case _: Throwable => defaultValue
    }
  }

  def toFloatOrElse(defaultValue: Float): Float = {
    try {
      rawValue.toFloat
    } catch {
      case _: Throwable => defaultValue
    }
  }

  def toDoubleOrElse(defaultValue: Double): Double = {
    try {
      rawValue.toDouble
    } catch {
      case _: Throwable => defaultValue
    }
  }

  def toIntOrElse(defaultValue: Int): Int = {
    try {
      rawValue.toInt
    } catch {
      case _: Throwable => defaultValue
    }
  }

  def toLongOrElse(defaultValue: Long): Long = {
    try {
      rawValue.toLong
    } catch {
      case _: Throwable => defaultValue
    }
  }

  def toShortOrElse(defaultValue: Double): Double = {
    try {
      rawValue.toShort
    } catch {
      case _: Throwable => defaultValue
    }
  }

  def toNullableBooleanOrElse(defaultValue: java.lang.Boolean): java.lang.Boolean = {
    try {
      rawValue.toBoolean
    } catch {
      case _: Throwable => defaultValue
    }
  }

  def toNullableByteOrElse(defaultValue: java.lang.Byte): java.lang.Byte = {
    try {
      rawValue.toByte
    } catch {
      case _: Throwable => defaultValue
    }
  }

  def toNullableFloatOrElse(defaultValue: java.lang.Float): java.lang.Float = {
    try {
      rawValue.toFloat
    } catch {
      case _: Throwable => defaultValue
    }
  }

  def toNullableDoubleOrElse(defaultValue: java.lang.Double): java.lang.Double = {
    try {
      rawValue.toDouble
    } catch {
      case _: Throwable => defaultValue
    }
  }

  def toNullableIntOrElse(defaultValue: java.lang.Integer): java.lang.Integer = {
    try {
      rawValue.toInt
    } catch {
      case _: Throwable => defaultValue
    }
  }

  def toNullableLongOrElse(defaultValue: java.lang.Long): java.lang.Long = {
    try {
      rawValue.toLong
    } catch {
      case _: Throwable => defaultValue
    }
  }

  def toNullableShortOrElse(defaultValue: java.lang.Double): java.lang.Double = {
    try {
      rawValue.toShort
    } catch {
      case _: Throwable => defaultValue
    }
  }

  override def toString(): String = rawValue

}