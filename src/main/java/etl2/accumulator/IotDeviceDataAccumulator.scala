package etl2.accumulator

package com.jgw.bigdata.spark.AnjiWhiteTea.accumulator

import etl2.domain.IotDeviceData
import org.apache.spark.util.AccumulatorV2

import scala.collection.mutable.ListBuffer

class IotDeviceDataAccumulator extends AccumulatorV2[collection.mutable.ListBuffer[IotDeviceData], collection.mutable.ListBuffer[IotDeviceData]] {

  private var iotData: collection.mutable.ListBuffer[IotDeviceData] = new collection.mutable.ListBuffer[IotDeviceData]()

  override def isZero: Boolean = {
    iotData.isEmpty
  }

  override def copy(): AccumulatorV2[ListBuffer[IotDeviceData], ListBuffer[IotDeviceData]] = {
    val accumulator = new IotDeviceDataAccumulator
    accumulator.iotData = this.iotData
    accumulator
  }

  override def reset(): Unit = {
    iotData = new collection.mutable.ListBuffer[IotDeviceData]()
  }

  override def add(v: ListBuffer[IotDeviceData]): Unit = {
    iotData = iotData.union(v)
  }

  override def merge(other: AccumulatorV2[ListBuffer[IotDeviceData], ListBuffer[IotDeviceData]]): Unit = other match {
    case o: IotDeviceDataAccumulator =>
      iotData = iotData.union(o.iotData)
    case _ =>
      throw new UnsupportedOperationException(
        s"Cannot merge ${this.getClass.getName} with ${other.getClass.getName}")
  }

  override def value: ListBuffer[IotDeviceData] = {
    iotData
  }
}