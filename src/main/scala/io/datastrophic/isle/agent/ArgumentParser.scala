package io.datastrophic.isle.agent

object ArgumentParser {
  val FrequencyKey = "--frequency"
  val TypeKey = "--type"
  val FileCheckValue = "filecheck"
  val HeartbeatCheckValue = "heartbeat"
  val LocationKey = "--location"

  val usage =
    """
      | heartbeat mode: java -jar agent.jar --type heartbeat --frequency 5
      | file check mode: java -jar agent.jar --type filecheck --location <path to file> --frequency 5
    """.stripMargin


  /**
    * @param args array of GNU-style arguments
    * @return tuple that contains a map of arguments and an error message in case of parsing problem
    */
  def parseArguments(args: Array[String]): (Map[String, String], String) = {
    var i = 0
    var error = ""
    var arguments = Map.empty[String, String]

    while (i < args.length){
      if(!args(i).startsWith("--")){
        arguments = Map()
        error = s"Argument name doesn't conform GNU style: [${args(i)}]. Usage: $usage"
        i = args.length
      } else if (i + 1 == args.length){
        arguments = Map()
        error = s"Argument not specified: [${args(i)}]. Usage: $usage"
        i = args.length
      } else {
        arguments += args(i) -> args(i+1)
        i = i+2
      }
    }

    if(error.isEmpty){
      val validation = validate(arguments)

      if(validation._1){
        (arguments, error)
      } else {
        (arguments, validation._2)
      }
    } else {
      (Map(), error)
    }

  }

  /**
    * Method provides validation for parsed arguments Map
    * @param args Map of parsed arguments
    * @return tuple that contains a result of validation and an error message in case of validation failure
    */
  def validate(args: Map[String, String]): (Boolean, String) = {
    if(args.get(FrequencyKey).isDefined && args(FrequencyKey).forall(_.isDigit)){
      if(args.get(TypeKey).isDefined && (args(TypeKey) == FileCheckValue || args(TypeKey) == HeartbeatCheckValue)){
        if(args(TypeKey) == FileCheckValue) {
          if (args.get(LocationKey).isDefined) {
            (true, "")
          } else {
            (false, s"$LocationKey is missing value for the FileCheck. Usage: $usage")
          }
        } else {
          (true, "")
        }
      } else {
        (false, s"$TypeKey key is missing or has invalid value. Usage: $usage")
      }
    } else {
      (false, s"$FrequencyKey key is missing or not a digit. Usage: $usage")
    }
  }
}
