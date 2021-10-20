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
	val regAddrRD = RegInit(0.U(5.W))
	val regAddrRD2 = RegInit(0.U(5.W))
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
	val nextregPipeALU = regPipeALU
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
	
	regAddrRD := InstDeco.io.rd
	regAddrRD2 := regAddrRD
	val nextRD = regAddrRD2

	when(nextmuxRegOfVecPipe===0.U){
		RegOfVec(nextRD) := nextregPipeMWB
	} .elsewhen(nextmuxRegOfVecPipe===1.U){
		RegOfVec(nextRD) := Memory.io.rdData
	} .elsewhen(nextmuxRegOfVecPipe===2.U){
		RegOfVec(nextRD) := Memory.io.rdData(7,0)
	} .elsewhen(nextmuxRegOfVecPipe===3.U){
		RegOfVec(nextRD) := Memory.io.rdData(15,0)
	} .elsewhen(nextmuxRegOfVecPipe===4.U){
		RegOfVec(nextRD) := nextregPipeimm.asUInt
	} .elsewhen(nextmuxRegOfVecPipe===5.U){
		RegOfVec(nextRD) := nextregPipeMWB + nextregPipeimm.asUInt
	} .elsewhen(nextmuxRegOfVecPipe===6.U){
		RegOfVec(nextRD) := nextregPipeALU
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
			verification.assume(Module.reset.asBool())
			init := true.B
			//First set of Rules: Operations in the ALU based on Instruction decode and control 
			//Rule 1 addi between rs1 and rs2
		}.elsewhen((Control.io.muxALUin2 === 2.U) && (Control.io.muxALUin1 === 1.U) && (InstDeco.io.state === Instructions.addi)){
			verification.assert(ALU.io.out === (RegOfVec(InstDeco.io.rs1) + RegOfVec(InstDeco.io.rs2)))
			//Rule 2 sub between rs1 and rs2
		}.elsewhen((Control.io.muxALUin2 === 2.U) && (Control.io.muxALUin1 === 1.U) && (InstDeco.io.state === Instructions.sub)){
			verification.assert(ALU.io.out === (RegOfVec(InstDeco.io.rs1) - RegOfVec(InstDeco.io.rs2)))
			//Rule 3 xor between rs1 and rs2
		}.elsewhen((Control.io.muxALUin2 === 2.U) && (Control.io.muxALUin1 === 1.U) && (InstDeco.io.state === Instructions.xor)){
			verification.assert(ALU.io.out === (RegOfVec(InstDeco.io.rs1)^RegOfVec(InstDeco.io.rs2)))
			//Rule 4 xori between rs1 and rs2
		}.elsewhen((Control.io.muxALUin2 === 2.U) && (Control.io.muxALUin1 === 1.U) && (InstDeco.io.state === Instructions.xori)){
			verification.assert(ALU.io.out === (RegOfVec(InstDeco.io.rs1)^RegOfVec(InstDeco.io.rs2)))
			//Rule 5 add between rs1 and rs2
		}.elsewhen((Control.io.muxALUin2 === 2.U) && (Control.io.muxALUin1 === 1.U) && (InstDeco.io.state === Instructions.add)){
			verification.assert(ALU.io.out === (RegOfVec(InstDeco.io.rs1)+RegOfVec(InstDeco.io.rs2)))
			//Rule 6 shift right between rs1 and rs2
		}.elsewhen((Control.io.muxALUin2 === 2.U) && (Control.io.muxALUin1 === 1.U) && (InstDeco.io.state === Instructions.srl)){
			verification.assert(ALU.io.out === (RegOfVec(InstDeco.io.rs1) >> RegOfVec(InstDeco.io.rs2)(4,0)))
			//Rule 7 shift right immediate between rs1 and rs2
		}.elsewhen((Control.io.muxALUin2 === 2.U) && (Control.io.muxALUin1 === 1.U) && (InstDeco.io.state === Instructions.srli)){
			verification.assert(ALU.io.out === (RegOfVec(InstDeco.io.rs1) >> RegOfVec(InstDeco.io.rs2)(4,0)))
			//Rule 8 shift left immediate between rs1 and rs2
		}.elsewhen((Control.io.muxALUin2 === 2.U) && (Control.io.muxALUin1 === 1.U) && (InstDeco.io.state === Instructions.slli)){
			verification.assert(ALU.io.out === (RegOfVec(InstDeco.io.rs1) << RegOfVec(InstDeco.io.rs2)(4,0)))
			//Rule 8 shift left between rs1 and rs2
	//	}.elsewhen((Control.io.muxALUin2 === 2.U) && (Control.io.muxALUin1 === 1.U) && (InstDeco.io.state === Instructions.sll)){
	//		verification.assert(ALU.io.out === (RegOfVec(InstDeco.io.rs1) << RegOfVec(InstDeco.io.rs2)(4,0)))
			//Rule 9 or between rs1 and rs2
		}.elsewhen((Control.io.muxALUin2 === 2.U) && (Control.io.muxALUin1 === 1.U) && (InstDeco.io.state === Instructions.or)){
			verification.assert(ALU.io.out === (RegOfVec(InstDeco.io.rs1)|RegOfVec(InstDeco.io.rs2)))
			//Rule 10 ori between rs1 and rs2
		}.elsewhen((Control.io.muxALUin2 === 2.U) && (Control.io.muxALUin1 === 1.U) && (InstDeco.io.state === Instructions.ori)){
			verification.assert(ALU.io.out === (RegOfVec(InstDeco.io.rs1)|RegOfVec(InstDeco.io.rs2)))
			//Rule 11 and between rs1 and rs2
		}.elsewhen((Control.io.muxALUin2 === 2.U) && (Control.io.muxALUin1 === 1.U) && (InstDeco.io.state === Instructions.and)){
			verification.assert(ALU.io.out === (RegOfVec(InstDeco.io.rs1) & RegOfVec(InstDeco.io.rs2)))
			//Rule 12 andi between rs1 and rs2
		}.elsewhen((Control.io.muxALUin2 === 2.U) && (Control.io.muxALUin1 === 1.U) && (InstDeco.io.state === Instructions.andi)){
			verification.assert(ALU.io.out === (RegOfVec(InstDeco.io.rs1) & RegOfVec(InstDeco.io.rs2)))
			//Rule 13 smaller than between rs1 and rs2
	//	}.elsewhen((Control.io.muxALUin2 === 2.U) && (Control.io.muxALUin1 === 1.U) && (InstDeco.io.state === Instructions.slt)){
	//		verification.assert(ALU.io.out === (RegOfVec(InstDeco.io.rs1) < RegOfVec(InstDeco.io.rs2)))
			//Rule 14 smaller than immediate between rs1 and rs2
		}.elsewhen((Control.io.muxALUin2 === 2.U) && (Control.io.muxALUin1 === 1.U) && (InstDeco.io.state === Instructions.slti)){
			verification.assert(ALU.io.out === (RegOfVec(InstDeco.io.rs1) < RegOfVec(InstDeco.io.rs2)))
			//Rule 15 equal to between rs1 and rs2
		}.elsewhen((Control.io.muxALUin2 === 2.U) && (Control.io.muxALUin1 === 1.U) && (InstDeco.io.state === Instructions.beq) && (RegOfVec(InstDeco.io.rs1) === RegOfVec(InstDeco.io.rs2))){
			verification.assert(ALU.io.out === 1.U)
			//Rule 16 not equal to between rs1 and rs2
		}.elsewhen((Control.io.muxALUin2 === 2.U) && (Control.io.muxALUin1 === 1.U) && (InstDeco.io.state === Instructions.bne) && (RegOfVec(InstDeco.io.rs1) === RegOfVec(InstDeco.io.rs2))){
			verification.assert(ALU.io.out === 0.U)
			//Rule 17 addi between rs1 and imm
		}.elsewhen((Control.io.muxALUin2 === 1.U) && (Control.io.muxALUin1 === 1.U) && (InstDeco.io.state === Instructions.addi)){
			verification.assert(ALU.io.out === (RegOfVec(InstDeco.io.rs1) + InstDeco.io.imm.asUInt))
			//Rule 18 sub between rs1 and imm
		}.elsewhen((Control.io.muxALUin2 === 1.U) && (Control.io.muxALUin1 === 1.U) && (InstDeco.io.state === Instructions.sub)){
			verification.assert(ALU.io.out === (RegOfVec(InstDeco.io.rs1) - InstDeco.io.imm.asUInt))
			//Rule 19 xor between rs1 and imm
		}.elsewhen((Control.io.muxALUin2 === 1.U) && (Control.io.muxALUin1 === 1.U) && (InstDeco.io.state === Instructions.xor)){
			verification.assert(ALU.io.out === (RegOfVec(InstDeco.io.rs1)^InstDeco.io.imm.asUInt))
			//Rule 20 xori between rs1 and imm
		}.elsewhen((Control.io.muxALUin2 === 1.U) && (Control.io.muxALUin1 === 1.U) && (InstDeco.io.state === Instructions.xori)){
			verification.assert(ALU.io.out === (RegOfVec(InstDeco.io.rs1)^InstDeco.io.imm.asUInt))
			//Rule 21 add between rs1 and imm
		}.elsewhen((Control.io.muxALUin2 === 1.U) && (Control.io.muxALUin1 === 1.U) && (InstDeco.io.state === Instructions.add)){
			verification.assert(ALU.io.out === (RegOfVec(InstDeco.io.rs1)+InstDeco.io.imm.asUInt))
			//Rule 22 shift right between rs1 and imm
		}.elsewhen((Control.io.muxALUin2 === 1.U) && (Control.io.muxALUin1 === 1.U) && (InstDeco.io.state === Instructions.srl)){
			verification.assert(ALU.io.out === (RegOfVec(InstDeco.io.rs1) >> InstDeco.io.imm(4,0).asUInt))
			//Rule 23 shift right immediate between rs1 and imm
		}.elsewhen((Control.io.muxALUin2 === 1.U) && (Control.io.muxALUin1 === 1.U) && (InstDeco.io.state === Instructions.srli)){
			verification.assert(ALU.io.out === (RegOfVec(InstDeco.io.rs1) >> InstDeco.io.imm(4,0)asUInt))
			//Rule 24 shift left immediate between rs1 and imm
	//	}.elsewhen((Control.io.muxALUin2 === 1.U) && (Control.io.muxALUin1 === 1.U) && (InstDeco.io.state === Instructions.slli)){
	//		verification.assert(ALU.io.out === (RegOfVec(InstDeco.io.rs1) << InstDeco.io.imm(4,0)asUInt))
			//Rule 25 shift left between rs1 and imm
		}.elsewhen((Control.io.muxALUin2 === 1.U) && (Control.io.muxALUin1 === 1.U) && (InstDeco.io.state === Instructions.sll)){
			verification.assert(ALU.io.out === (RegOfVec(InstDeco.io.rs1) << InstDeco.io.imm(4,0)asUInt))
			//Rule 26 or between rs1 and imm
		}.elsewhen((Control.io.muxALUin2 === 1.U) && (Control.io.muxALUin1 === 1.U) && (InstDeco.io.state === Instructions.or)){
			verification.assert(ALU.io.out === (RegOfVec(InstDeco.io.rs1)|InstDeco.io.imm.asUInt))
			//Rule 27 ori between rs1 and imm
		}.elsewhen((Control.io.muxALUin2 === 1.U) && (Control.io.muxALUin1 === 1.U) && (InstDeco.io.state === Instructions.ori)){
			verification.assert(ALU.io.out === (RegOfVec(InstDeco.io.rs1)|InstDeco.io.imm.asUInt))
			//Rule 28 and between rs1 and imm
		}.elsewhen((Control.io.muxALUin2 === 1.U) && (Control.io.muxALUin1 === 1.U) && (InstDeco.io.state === Instructions.and)){
			verification.assert(ALU.io.out === (RegOfVec(InstDeco.io.rs1) & InstDeco.io.imm.asUInt))
			//Rule 29 andi between rs1 and imm
		}.elsewhen((Control.io.muxALUin2 === 1.U) && (Control.io.muxALUin1 === 1.U) && (InstDeco.io.state === Instructions.andi)){
			verification.assert(ALU.io.out === (RegOfVec(InstDeco.io.rs1) & InstDeco.io.imm.asUInt))
			//Rule 30 smaller than between rs1 and imm
		}.elsewhen((Control.io.muxALUin2 === 1.U) && (Control.io.muxALUin1 === 1.U) && (InstDeco.io.state === Instructions.slt)){
			verification.assert(ALU.io.out === (RegOfVec(InstDeco.io.rs1) < InstDeco.io.imm.asUInt))
	//		//Rule 31 smaller than immediate between rs1 and imm
	//	}.elsewhen((Control.io.muxALUin2 === 1.U) && (Control.io.muxALUin1 === 1.U) && (InstDeco.io.state === Instructions.slti)){
	//		verification.assert(ALU.io.out === (RegOfVec(InstDeco.io.rs1) < InstDeco.io.imm.asUInt))
			//Rule 32 equal to between rs1 and imm
		}.elsewhen((Control.io.muxALUin2 === 1.U) && (Control.io.muxALUin1 === 1.U) && (InstDeco.io.state === Instructions.beq) && (RegOfVec(InstDeco.io.rs1) ===InstDeco.io.imm.asUInt)){
			verification.assert(ALU.io.out === 1.U)
			//Rule 33 not equal to between rs1 and imm
		}.elsewhen((Control.io.muxALUin2 === 1.U) && (Control.io.muxALUin1 === 1.U) && (InstDeco.io.state === Instructions.bne) && (RegOfVec(InstDeco.io.rs1) === InstDeco.io.imm.asUInt)){
			verification.assert(ALU.io.out === 0.U)
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
