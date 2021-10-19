package Proccesor

import chisel3._
import chisel3.util._



class TOP extends Module{
	val io = IO(new Bundle {
		val reset = Input(Bool())
		val out = Output(UInt(32.W))
})

	//Starting modules
	val InstDeco = Module(new InstDeco)
	val ALU = Module(new ALU)
	val InstMem = Module(new InstMem)
	val memory = Module(new Memory)
	val control = Module(new control)

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

	//state (Output InstDeco - Input control)
	control.io.state := InstDeco.io.state

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
	ALU.io.instruc := InstDeco.io.state
	ALU.io.in1 := Mux(control.io.muxALUin1, RegOfVec(InstDeco.io.rs1), 0.U)
	when(control.io.muxALUin2 === 2.U){
		ALU.io.in2 := RegOfVec(InstDeco.io.rs2)
	} .elsewhen(control.io.muxALUin2 === 1.U){
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
	when(control.io.muxAddrI===0.U){
		addrI := addrI + 1.U
	} .elsewhen(control.io.muxAddrI===1.U){
		addrI := addrI + InstDeco.io.imm.asUInt
	} .elsewhen(control.io.muxAddrI===2.U){
		addrI := Cat((RegOfVec(InstDeco.io.rs1) + InstDeco.io.imm.asUInt)(31,1),0.U(1.W))
	} .elsewhen(control.io.muxAddrI===3.U){
		addrI := Mux(ALU.io.out===1.U, addrI + InstDeco.io.imm.asUInt, addrI + 1.U)
	}

	//Mux Registers bank

	muxRegOfVecPipe := control.io.muxRegOfVec
	val nextmuxRegOfVecPipe = muxRegOfVecPipe

	regPipeimm :=  InstDeco.io.imm
	val nextregPipeimm = regPipeimm

	when(nextmuxRegOfVecPipe===0.U){
		RegOfVec(InstDeco.io.rd) := nextregPipeMWB + 1.U
	} .elsewhen(nextmuxRegOfVecPipe===1.U){
		RegOfVec(InstDeco.io.rd) := Memo.io.rdData
	} .elsewhen(nextmuxRegOfVecPipe===2.U){
		RegOfVec(InstDeco.io.rd) := Memo.io.rdData(7,0)
	} .elsewhen(nextmuxRegOfVecPipe===3.U){
		RegOfVec(InstDeco.io.rd) := Memo.io.rdData(15,0)
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
	regPipemuxwen := control.io.muxwen
	val nextregPipemuxwen = regPipemuxwen
	Memo.io.wen := Mux(nextregPipemuxren===1.U, 1.U, 0.U)

	//Mux for read enable of Memory
	regPipemuxren := control.io.muxren
	val nextregPipemuxren = regPipemuxren
	Memo.io.ren := Mux(nextregPipemuxren===1.U, 1.U, 0.U)

	//Mux for write address of Memory
	Memo.io.wrAddr := Mux(control.io.muxwrAddr===1.U, RegOfVec(InstDeco.io.rs1) + InstDeco.io.imm.asUInt, 0.U)


	//Mux for read address of Memory
	Memo.io.rdAddr := Mux(control.io.muxrdAddr===1.U, RegOfVec(InstDeco.io.rs1) + InstDeco.io.imm.asUInt, 0.U)

	//Mux for write data of Memory
	when(control.io.muxwrData===0.U){
		Memo.io.wrData := RegOfVec(InstDeco.io.rs2)
	} .elsewhen(control.io.muxwrData===1.U){
		Memo.io.wrData := RegOfVec(InstDeco.io.rs2)(15,0)
	} .elsewhen(control.io.muxwrData===2.U){
		Memo.io.wrData := RegOfVec(InstDeco.io.rs2)(7,0)
	} .elsewhen(control.io.muxwrData===3.U){
		Memo.io.wrData := 0.U
	}

}



object TOPMain extends App
{
	chisel3.Driver.execute(args, () => new TOP)
}