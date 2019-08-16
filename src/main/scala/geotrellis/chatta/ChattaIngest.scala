package geotrellis.chatta

import geotrellis.raster.Tile
import geotrellis.spark.SpatialKey
import geotrellis.spark.etl.Etl
import geotrellis.spark._
import geotrellis.spark.util.SparkUtils
import geotrellis.vector.ProjectedExtent

import org.apache.spark.SparkConf

object ChattaIngest extends App {
  val arg = Array[String](
    "--input",
    "D:\\data\\input.json",
    "--output",
    "D:\\data\\output.json",
    "--backend-profiles",
    "D:\\data\\backend-profiles.json"
  );

//  implicit val sc = SparkUtils.createSparkContext("GeoTrellis ETL SinglebandIngest", new SparkConf(true))
  implicit val sc = SparkUtils.createLocalSparkContext("local[*]",
    "GeoTrellis ETL SinglebandIngest", new SparkConf(true))

  try {
    Etl.ingest[ProjectedExtent, SpatialKey, Tile](arg)
  } finally {
    sc.stop()
  }
}
