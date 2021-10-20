package Processor

import chisel3._
import chisel3.util._

import chisel3.experimental._ // formal verification
import chisel3.stage.ChiselStage // verilog, sverilog generation

class TOP (val formal:Boolean=false) extends Module{
	val io = IO(new Bundle {
		val out = Output(UInt(32.W))
})

	//Starting modules
	val InstDeco = Module(new InstDeco)
	val ALU = Module(new ALU)
	val InstMem = Module(new InstMem)
	val Memory = Module(new Memory)
	val Control = Module(new Control)

	// **** Starting Program Counter ****
	val addrI = RegInit(0.U(32.W))

	// **** Starting Pipeline Registers ****
	// First stage -> Second stage
	val regPipeEx = RegInit(0.U(32.W)) // Pipeline Register between First stage and Second stage
	val regMemDeco = RegInit(0.U(32.W)) // Pipeline Register between InstMem and InstDeco

	// Second Stage -> Third stage
	val regPipeMWB = RegInit(0.U(32.W)) // Pipeline Register between second stage and third stage for Address PC
	val regPipeALU = RegInit(0.U(32.W)) // Pipeline Register between second stage and third stage for Output ALU
	val muxRegOfVecPipe = RegInit(0.U(4.W))
	val regPipeimm = RegInit(0.U(32.W))
	val regPipemuxren = RegInit(0.U(1.W))
	val regPipemuxwen = RegInit(0.U(1.W))

	//*************************************************
	val RegOfVec = RegInit(VecInit(Seq.fill(32)(0.U(32.W))))

	//Starting Instructions object
	val ins = Instructions

	//state (Output InstDeco - Input Control)
	Control.io.state := InstDeco.io.state

	//addrI (Address Instructions memory)
	InstMem.io.addrI := addrI
	regPipeEx := addrI
	val nextregPipeEx = regPipeEx // Pipeline register to Address PC for First and Second stage.
	regPipeMWB := regPipeEx
	val nextregPipeMWB = regPipeMWB // Pipeline register to Address PC for Second and third stage.


	//Instruction (Output InstMem - Input InstDeco)
	regMemDeco := InstMem.io.instruc
	val nextregMemDeco = regMemDeco
	InstDeco.io.instruc := nextregMemDeco // Connection between Output InstMem and Input InstDeco. Pipeline register

	//ALU (Input state - Output out)
	ALU.io.state := InstDeco.io.state
	ALU.io.in1 := Mux(Control.io.muxALUin1, RegOfVec(InstDeco.io.rs1), 0.U)
	when(Control.io.muxALUin2 === 2.U){
		ALU.io.in2 := RegOfVec(InstDeco.io.rs2)
	} .elsewhen(Control.io.muxALUin2 === 1.U){
		ALU.io.in2 := InstDeco.io.imm.asUInt
	} .otherwise{
		ALU.io.in2 := 0.U
	}
	regPipeALU := ALU.io.out
	var nextregPipeALU = regPipeALU
	io.out       := nextregPipeALU

	//Starting  Inputs Memory
	Memory.io.wen := 0.U(1.W)
	Memory.io.ren := 0.U(1.W)
	Memory.io.wrAddr := 0.U(8.W)
	Memory.io.wrData := 0.U(32.W)
	Memory.io.rdAddr := 0.U(8.W)

	//Mux Control//

	//Mux program counter (addrI)
	when(Control.io.muxAddrI===0.U){
		addrI := addrI + 1.U
	} .elsewhen(Control.io.muxAddrI===1.U){
		addrI := addrI + InstDeco.io.imm.asUInt
	} .elsewhen(Control.io.muxAddrI===2.U){
		addrI := Cat((RegOfVec(InstDeco.io.rs1) + InstDeco.io.imm.asUInt)(31,1),0.U(1.W))
	} .elsewhen(Control.io.muxAddrI===3.U){
		addrI := Mux(ALU.io.out===1.U, addrI + InstDeco.io.imm.asUInt, addrI + 1.U)
	}

	//Mux Registers bank

	muxRegOfVecPipe := Control.io.muxRegOfVec
	val nextmuxRegOfVecPipe = muxRegOfVecPipe

	regPipeimm :=  InstDeco.io.imm.asUInt
	val nextregPipeimm = regPipeimm

	when(nextmuxRegOfVecPipe===0.U){
		RegOfVec(InstDeco.io.rd) := nextregPipeMWB + 1.U
	} .elsewhen(nextmuxRegOfVecPipe===1.U){
		RegOfVec(InstDeco.io.rd) := Memory.io.rdData
	} .elsewhen(nextmuxRegOfVecPipe===2.U){
		RegOfVec(InstDeco.io.rd) := Memory.io.rdData(7,0)
	} .elsewhen(nextmuxRegOfVecPipe===3.U){
		RegOfVec(InstDeco.io.rd) := Memory.io.rdData(15,0)
	} .elsewhen(nextmuxRegOfVecPipe===4.U){
		RegOfVec(InstDeco.io.rd) := nextregPipeimm.asUInt
	} .elsewhen(nextmuxRegOfVecPipe===5.U){
		RegOfVec(InstDeco.io.rd) := nextregPipeMWB + nextregPipeimm.asUInt
	} .elsewhen(nextmuxRegOfVecPipe===6.U){
		RegOfVec(InstDeco.io.rd) := nextregPipeALU
	} .elsewhen(nextmuxRegOfVecPipe===7.U){
		RegOfVec(0) := 0.U
	}

	//Mux for write enable of Memory
	regPipemuxwen := Control.io.muxwen
	val nextregPipemuxwen = regPipemuxwen
	Memory.io.wen := Mux(nextregPipemuxwen === 1.U, 1.U, 0.U)

	//Mux for read enable of Memory
	regPipemuxren := Control.io.muxren
	val nextregPipemuxren = regPipemuxren
	Memory.io.ren := Mux(nextregPipemuxren===1.U, 1.U, 0.U)

	//Mux for write address of Memory
	Memory.io.wrAddr := Mux(Control.io.muxwrAddr===1.U, RegOfVec(InstDeco.io.rs1) + InstDeco.io.imm.asUInt, 0.U)


	//Mux for read address of Memory
	Memory.io.rdAddr := Mux(Control.io.muxrdAddr===1.U, RegOfVec(InstDeco.io.rs1) + InstDeco.io.imm.asUInt, 0.U)

	//Mux for write data of Memory
	when(Control.io.muxwrData===0.U){
		Memory.io.wrData := RegOfVec(InstDeco.io.rs2)
	} .elsewhen(Control.io.muxwrData===1.U){
		Memory.io.wrData := RegOfVec(InstDeco.io.rs2)(15,0)
	} .elsewhen(Control.io.muxwrData===2.U){
		Memory.io.wrData := RegOfVec(InstDeco.io.rs2)(7,0)
	} .elsewhen(Control.io.muxwrData===3.U){
		Memory.io.wrData := 0.U
	}

	if (formal){
		val init = RegInit(false.B)

		when(init === false.B){
			//verification.assume(reset)
			init := true.B
		}
	}

}



object TOPMain extends App
{
	chisel3.Driver.execute(args, () => new TOP)
}

// Verilog Generation

object TOPDriver_Verilog extends App {
	(new ChiselStage).emitVerilog(new TOP(formal=false),args)
}

// SystemVerilog Generation

object TOPDriver_SystemVerilog extends App {
	(new ChiselStage).emitSystemVerilog(new TOP(formal=true),args)
}
