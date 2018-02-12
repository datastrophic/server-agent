package io.datastrophic.isle.agent

import ArgumentParser._

object Agent extends App {
  println("agent started. parsing arguments")
  val (arguments, error) = parseArguments(args)

  if(error.nonEmpty){
    println(error)
  } else {
    if(arguments(TypeKey) == FileCheckValue) {
      println("running filecheck")
      val check = new FileCheck(arguments(LocationKey))
      while (true) {
        check.checkFile()
        Thread.sleep(arguments(FrequencyKey).toLong * 1000)
      }
    } else {
      println("running heartbeat")
      val check = new HeartBeatCheck
      while (true) {
        check.heartbeat()
        Thread.sleep(arguments(FrequencyKey).toLong * 1000)
      }
    }
  }
}
