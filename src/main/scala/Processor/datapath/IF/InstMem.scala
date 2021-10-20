package Processor

import chisel3._
import chisel3.util._

class InstMem extends Module{
	val io = IO(new Bundle {
		val addrI = Input(UInt(32.W))
		val instruc = Output(UInt(32.W))
})

io.instruc:=0.U

/* First Test code

 addi x2,x0,0
 addi x2,x0,1
 addi x2,x0,2
 addi x2,x0,3
 addi x2,x0,4
 addi x2,x0,5
 addi x2,x0,6
 addi x2,x0,8
*/
when (io.addrI === 0.U){
	io.instruc := "b000000000000_00000_000_00010_0010011".U
	}
	.elsewhen (io.addrI === 1.U) {
	io.instruc := "b000000000001_00000_000_00010_0010011".U
	}
	.elsewhen (io.addrI === 2.U) {
	io.instruc := "b000000000010_00000_000_00010_0010011".U
	}
	.elsewhen (io.addrI === 3.U) {
	io.instruc := "b000000000011_00000_000_00010_0010011".U
	}
	.elsewhen (io.addrI === 4.U) {
	io.instruc := "b000000000100_00000_000_00010_0010011".U
	}
	.elsewhen (io.addrI === 5.U) {
	io.instruc := "b000000000101_00000_000_00010_0010011".U
	}
	.elsewhen (io.addrI === 6.U) {
	io.instruc := "b000000000110_00000_000_00010_0010011".U  
	}
	.elsewhen (io.addrI === 7.U) {
	io.instruc := "b000000001000_00000_000_00010_0010011".U  
	}
	
	
	
}


