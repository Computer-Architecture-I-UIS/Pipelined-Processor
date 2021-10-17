package Processor

import chisel3._
import chisel3.util._


class Memo extends Module {
  val io = IO(new Bundle {
    val wen     = Input(Bool())
    val wrAddr  = Input(UInt(8.W))
    val wrData  = Input(UInt(32.W))
    val ren     = Input(Bool())
    val rdAddr  = Input(UInt(8.W))
    val rdData  = Output(UInt(32.W))
  })

  val mem = Mem(256, UInt(32.W))

  // write
  when (io.wen) { mem(io.wrAddr) := io.wrData }

  // read
  io.rdData := 0.U
  when (io.ren) { io.rdData := mem(io.rdAddr) }

}
