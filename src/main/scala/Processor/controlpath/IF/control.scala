
package Processor

import chisel3._
import chisel3.util._


class control extends Module{
	val io = IO(new Bundle {
		val opcode = Input(UInt(6.W))
		val muxwen = Output(Bool())     // Habilitar escritura de la memoria
		val muxren = Output(Bool())     // Habilitar lectura de la memoria
		val muxALUin2 = Output(Bool())  // Seleccion del in2 de la ALU
    val muxrd = Output(Bool())      // Seleccion valor de rd
})

  muxwen := opcode(6,2) === 8.U
  muxren := opcode(6,2) === 0.U
  muxALUint := opcode(5)===1.U
  muxrd := (opcode(6,2) === 8.U) && (opcode(6,2) === 0.U)

}
