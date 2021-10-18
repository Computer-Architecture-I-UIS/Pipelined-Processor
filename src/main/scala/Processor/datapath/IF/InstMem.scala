package Proccesor

import chisel3._
import chisel3.util._

class InstMem extends Module{
	val io = IO(new Bundle {
		val addrI = Input(UInt(32.W))
		val instruc = Output(UInt(32.W))
})

io.instruc:=0.U



when (io.addrI === 0.U){
	io.instruc := "b000000000000_00000_000_00000_0000000".U
	}
	.elsewhen (io.addrI === 1.U) {
	io.instruc := "b000000000000_00000_000_00000_0000000".U
	}
	.elsewhen (io.addrI === 2.U) {
	io.instruc := "b000000000000_00000_000_00000_0000000".U
	}
	.elsewhen (io.addrI === 3.U) {
	io.instruc := "b000000000000_00000_000_00000_0000000".U
	}
	.elsewhen (io.addrI === 4.U) {
	io.instruc := "b000000000000_00000_000_00000_0000000".U
	}
	.elsewhen (io.addrI === 5.U) {
	io.instruc := "b000000000000_00000_000_00000_0000000".U
	}
	.elsewhen (io.addrI === 6.U) {
	io.instruc := "b000000000000_00000_000_00000_0000000".U  
	}
	
	
	
}


