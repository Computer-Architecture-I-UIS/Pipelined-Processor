package Proccesor

import chisel3._
import chisel3.util._



class TOP extends Module{
	val io = IO(new Bundle {
		val reset = Input(Bool())
		val out = Output(UInt(32.W))
})

	//Inicialización módulos
	val InstDeco = Module(new InstDeco)
	val ALU = Module(new ALU)
	val InstMem = Module(new InstMem)
	val Memo = Module(new Memo)
	val control = Module(new control)
	
	//inicializacion del contador de programa
	val addrI = RegInit(0.U(32.W))
	
	
	//inicializacion del objeto de instrucciones
	val ins = Instructions
	
	//state (salida InstDeco - entrada control)
	control.io.state := InstDeco.io.state
	
	//addrI (Direccion de la memoria de instrucciones)
	InstMem.io.addrI := addrI
	
	//Intruccion (salida InstMem - entrada InstDeco)
	InstDeco.io.instruc := InstMem.io.instruc  
	
	//ALU (entrada state - salida out)
	ALU.io.state := InstDeco.io.state
	io.out       := ALU.io.out
	
	//Inicializacion entradas de la memoria
	Memo.io.wen := 0.U(1.W)
	Memo.io.ren := 0.U(1.W)
	Memo.io.wrAddr := 0.U(8.W)
	Memo.io.wrData := 0.U(32.W)
	Memo.io.rdAddr := 0.U(8.W)
	
}



object TOPMain extends App
{
	chisel3.Driver.execute(args, () => new TOP)
}





















