
package Processor

import chisel3._
import chisel3.util._


class control extends Module{
	val io = IO(new Bundle {

		val state = Input(UInt(6.W))
		val muxALUin1 = Output(Bool())			// Seleccion del in1 de la ALU entre 0 y rs1
		val muxALUin2 = Output(UInt(2.W))   // Seleccion del in2 de la ALU
		val muxAddrI = Output(UInt(2.W))		// Program counter
		val muxRegOfVec = Output(UInt(4.W)) // // Seleccion valor de rd
		val muxwen = Output(Bool())     		// Habilitar escritura de la memoria
		val muxren = Output(Bool())    		 	// Habilitar lectura de la memoria
		val muxwrAddr = Output(Bool())      // Direccion de escritura
		val muxrdAddr = Output(Bool())      // Direccion de lectura
		val muxwrData = Output(UInt(2.W))   // Seleccion del dato de la memoria
})

  muxwen := opcode(6,2) === 8.U
  muxren := opcode(6,2) === 0.U
  muxALUint := opcode(5)===1.U
  muxrd := (opcode(6,2) === 8.U) && (opcode(6,2) === 0.U)

	//inicializacion del objeto de instrucciones
val ins = Instructions

io.muxALUin1 := 0.U
io.muxALUin2 := 0.U //default
io.muxAddrI := 0.U//default
io.muxRegOfVec := 7.U
io.muxwen := 0.U
io.muxren := 0.U
io.muxwrAddr := 0.U
io.muxrdAddr := 0.U
io.muxwrData := 3.U



switch(io.state){


	//SHIFTS
	is(ins.sll){
			io.muxALUin1 := 1.U
			io.muxALUin2 := 2.U
			io.muxAddrI := 0.U
			io.muxRegOfVec := 6.U
		}
		is(ins.slli){
			io.muxALUin1 := 1.U
			io.muxALUin2 := 1.U
			io.muxAddrI := 0.U
			io.muxRegOfVec := 6.U
		}
		is(ins.srl){
			io.muxALUin1 := 1.U
			io.muxALUin2 := 2.U
			io.muxAddrI := 0.U
			io.muxRegOfVec := 6.U
		}
		is(ins.srli){
			io.muxALUin1 := 1.U
			io.muxALUin2 := 1.U
			io.muxAddrI := 0.U
			io.muxRegOfVec := 6.U
		}
		is(ins.sra){
			io.muxALUin1 := 1.U
			io.muxALUin2 := 2.U
			io.muxAddrI := 0.U
			io.muxRegOfVec := 6.U
		}
		is(ins.srai){
			io.muxALUin1 := 1.U
			io.muxALUin2 := 1.U
			io.muxAddrI := 0.U
			io.muxRegOfVec := 6.U
		}
		is(ins.slt){
			io.muxALUin1 := 1.U
			io.muxALUin2 := 2.U
			io.muxAddrI := 0.U
			io.muxRegOfVec := 6.U
		}
		is(ins.slti){
			io.muxALUin1 := 1.U
			io.muxALUin2 := 1.U
			io.muxAddrI := 0.U
			io.muxRegOfVec := 6.U
		}
		is(ins.sltu){
			io.muxALUin1 := 1.U
			io.muxALUin2 := 2.U
			io.muxAddrI := 0.U
			io.muxRegOfVec := 6.U
		}
		is(ins.sltiu){
			io.muxALUin1 := 1.U
			io.muxALUin2 := 1.U
			io.muxAddrI := 0.U
			io.muxRegOfVec := 6.U
		}


		//ADDITION, SUBTRACTION AND LOGIC
		is(ins.add){
			io.muxALUin1 := 1.U
			io.muxALUin2 := 2.U
			io.muxAddrI := 0.U
			io.muxRegOfVec := 6.U
		}
		is(ins.addi){
			io.muxALUin1 := 1.U
			io.muxALUin2 := 1.U
			io.muxAddrI := 0.U
			io.muxRegOfVec := 6.U
		}
		is(ins.sub){
			io.muxALUin1 := 1.U
			io.muxALUin2 := 2.U
			io.muxAddrI := 0.U
			io.muxRegOfVec := 6.U
		}
		is(ins.xor){
			io.muxALUin1 := 1.U
			io.muxALUin2 := 2.U
			io.muxAddrI := 0.U
			io.muxRegOfVec := 6.U
		}
		is(ins.xori){
			io.muxALUin1 := 1.U
			io.muxALUin2 := 1.U
			io.muxAddrI := 0.U
			io.muxRegOfVec := 6.U
		}
		is(ins.or){
			io.muxALUin1 := 1.U
			io.muxALUin2 := 2.U
			io.muxAddrI := 0.U
			io.muxRegOfVec := 6.U
		}
		is(ins.ori){
			io.muxALUin1 := 1.U
			io.muxALUin2 := 1.U
			io.muxAddrI := 0.U
			io.muxRegOfVec := 6.U
		}
		is(ins.and){
			io.muxALUin1 := 1.U
			io.muxALUin2 := 2.U
			io.muxAddrI := 0.U
			io.muxRegOfVec := 6.U
		}
		is(ins.andi){
			io.muxALUin1 := 1.U
			io.muxALUin2 := 1.U
			io.muxAddrI := 0.U
			io.muxRegOfVec := 6.U
		}


		//BRANCHES
		is(ins.bne){
			io.muxALUin1 := 1.U
			io.muxALUin2 := 2.U
			io.muxAddrI := 3.U
		}
		is(ins.beq){
			io.muxALUin1 := 1.U
			io.muxALUin2 := 2.U
			io.muxAddrI := 3.U
		}
		is(ins.blt){
			io.muxALUin1 := 1.U
			io.muxALUin2 := 2.U
			io.muxAddrI := 3.U
		}
		is(ins.bge){
			io.muxALUin1 := 1.U
			io.muxALUin2 := 2.U
			io.muxAddrI := 3.U
		}
		is(ins.bltu){
			io.muxALUin1 := 1.U
			io.muxALUin2 := 2.U
			io.muxAddrI := 3.U
		}
		is(ins.bgeu){
			io.muxALUin1 := 1.U
			io.muxALUin2 := 2.U
			io.muxAddrI := 3.U
		}


		//JUMPS
		is(ins.jal){
			io.muxALUin1 := 0.U
			io.muxALUin2 := 0.U
			io.muxAddrI := 1.U
			io.muxRegOfVec := 0.U
		}
		is(ins.jalr){
			io.muxALUin1 := 1.U
			io.muxALUin2 := 0.U
			io.muxAddrI := 2.U
			io.muxRegOfVec := 0.U
		}


		//STORES
		is(ins.sw){
			io.muxALUin1 := 1.U
			io.muxALUin2 := 0.U
			io.muxAddrI := 0.U
			io.muxwen := 1.U
			io.muxwrAddr := 1.U
			io.muxwrData := 0.U
		}
		is(ins.sh){
			io.muxALUin1 := 1.U
			io.muxALUin2 := 0.U
			io.muxAddrI := 0.U
			io.muxwen := 1.U
			io.muxwrAddr := 1.U
			io.muxwrData := 1.U
		}
		is(ins.sb){
			io.muxALUin1 := 1.U
			io.muxALUin2 := 0.U
			io.muxAddrI := 0.U
			io.muxwen := 1.U
			io.muxwrAddr := 1.U
			io.muxwrData := 2.U
		}


		//LOADS
		is(ins.lw){
			io.muxALUin1 := 1.U
			io.muxALUin2 := 0.U
			io.muxAddrI := 0.U
			io.muxRegOfVec := 1.U
			io.muxren := 1.U
			io.muxrdAddr := 1.U
		}
		is(ins.lb){
			io.muxALUin1 := 1.U
			io.muxALUin2 := 0.U
			io.muxAddrI := 0.U
			io.muxRegOfVec := 2.U
			io.muxren := 1.U
			io.muxrdAddr := 1.U
		}
		is(ins.lbu){
			io.muxALUin1 := 1.U
			io.muxALUin2 := 0.U
			io.muxAddrI := 0.U
			io.muxRegOfVec := 2.U
			io.muxren := 1.U
			io.muxrdAddr := 1.U
		}
		is(ins.lh){
			io.muxALUin1 := 1.U
			io.muxALUin2 := 0.U
			io.muxAddrI := 0.U
			io.muxRegOfVec := 3.U
			io.muxren := 1.U
			io.muxrdAddr := 1.U
		}
		is(ins.lhu){
			io.muxALUin1 := 1.U
			io.muxALUin2 := 0.U
			io.muxAddrI := 0.U
			io.muxRegOfVec := 3.U
			io.muxren := 1.U
			io.muxrdAddr := 1.U
		}


		//LUI AND AUIPC
		is(ins.lui){
			io.muxALUin1 := 0.U
			io.muxALUin2 := 0.U
			io.muxAddrI := 0.U
			io.muxRegOfVec := 4.U
		}
		is(ins.auipc){
			io.muxALUin1 := 0.U
			io.muxALUin2 := 0.U
			io.muxAddrI := 0.U
			io.muxRegOfVec := 5.U
		}

	}

}
