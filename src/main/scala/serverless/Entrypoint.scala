package serverless

import com.typesafe.scalalogging.LazyLogging
import org.apache.spark.sql.SparkSession
import org.savvytools.stepfunctions.TaskToken

case class Person(id: String, name: String)

object SimpleSparkApp extends LazyLogging {

  /**
   * *
   *   do your stuff
   *   notify success
   *   Exception
   *     Notify failure
   *
   * * * *
   * @param args
   */

  def main(args: Array[String]): Unit = {
      logger.info("---- BJ -  main ----------->")
      logger.info("args Length:" + args.length)
      logger.info(args.mkString(","))
      logger.info("--------------------------")

      implicit val spark:SparkSession = SparkSession
        .builder()
        .appName("Test EMR Serverless")
        .getOrCreate()

      val token = args(0)
      try {
        run()
        TaskToken.success(token, null)
      } catch {
        case e: Exception => {
          logger.error("Error while executing job", e)
          TaskToken.fail(token)
        }
      }

    }

    def run()(implicit spark: SparkSession): Unit = {
      import spark.implicits._
      val data =
        Seq(Person(id = "1", name = "A"), Person(id = "2", name = "B")).toDS

      data.show(false)
    }

}

