package io.datastrophic.isle.agent

import java.io.File

class FileCheck(path: String) {
  def checkFile() = {
    if(new File(path).exists()){
      println("[healthy]")
    } else {
      println(s"[unhealthy] file not found: $path")
    }
  }
}
