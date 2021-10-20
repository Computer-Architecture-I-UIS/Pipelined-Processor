package Processor

import chisel3._
import chisel3.util._

import chisel3.experimental._ // formal verification
import chisel3.stage.ChiselStage // verilog, sverilog generation

class ALU (val formal:Boolean=false) extends Module{
	val io = IO(new Bundle {
		val state = Input(UInt(6.W))
		val in1 = Input(UInt(32.W))
		val in2 = Input(UInt(32.W))
		val out = Output(UInt(32.W))	
	})
	
// shift

val shifted = Wire(UInt(32.W))
shifted := io.in1 >> io.in2(4,0)

// addition

val totalsum = Wire(UInt(33.W))
totalsum := io.in1 + io.in2

// substraction

val totalsub = Wire(UInt(33.W))
totalsub := io.in1 - io.in2
	
io.out := 0.U
	
val ins=Instructions
	
	switch(io.state){
		  
		is(ins.sll){    // shift left       
			io.out := io.in1 << io.in2(4,0)
		}
		
		is(ins.slli){    // shift left       
			io.out := io.in1 << io.in2(4,0)
		}

		is(ins.srl){    // shift right
			io.out := io.in1 >> io.in2(4,0)
		}
		
		is(ins.srli){    // shift right
			io.out := io.in1 >> io.in2(4,0)
		}

		is(ins.sra){    // shift arithmetic right
			when     (io.in1(31) === 0.U){ io.out := shifted }        
			.otherwise{ io.out := shifted | ("hFFFF_FFFF".U << (32.U - io.in2(4,0))) } 
		}
		
		is(ins.srai){    // shift arithmetic right
			when     (io.in1(31) === 0.U){ io.out := shifted }        
			.otherwise{ io.out := shifted | ("hFFFF_FFFF".U << (32.U - io.in2(4,0))) } 
		}

		is(ins.add){    // ADD
			io.out := totalsum(31,0)
		}
		
		is(ins.addi){    // ADD
			io.out := totalsum(31,0)
		}

		is(ins.sub){    // SUB
			io.out := totalsub(31,0)                        
		}

		is(ins.xor){    // XOR
			io.out := io.in1 ^ io.in2
		}
		
		is(ins.xori){    // XOR
			io.out := io.in1 ^ io.in2
		}

		is(ins.or){    // OR
			io.out := io.in1 | io.in2
		}
		
		is(ins.ori){    // OR
			io.out := io.in1 | io.in2
		}

		is(ins.and){    // AND
			io.out := io.in1 & io.in2
		}
		
		is(ins.andi){    // AND
			io.out := io.in1 & io.in2
		}

		is(ins.slt){    // smaller than
			io.out := (io.in1.asSInt < io.in2.asSInt).asUInt
		}
		
		is(ins.slti){    // smaller than
			io.out := (io.in1.asSInt < io.in2.asSInt).asUInt
		}

		is(ins.sltu){    // smaller than unsigned
			io.out := Mux(io.in1 < io.in2, 1.U, 0.U)
		}
		
		is(ins.sltiu){    // smaller than unsigned
			io.out := Mux(io.in1 < io.in2, 1.U, 0.U)
		}
		
		
		
		is(ins.beq){	// equal to
			io.out := Mux(io.in1 === io.in2, 1.U, 0.U)	
		}

		is(ins.bne){	// not equal to
			io.out := Mux(io.in1 === io.in2, 0.U, 1.U)
		}	
		
		is(ins.blt){	// smaller than
			io.out := (io.in1.asSInt < io.in2.asSInt).asUInt
		}	

		is(ins.bge){	// greater or equal to
			io.out := (io.in1.asSInt < io.in2.asSInt).asUInt  ^ 1.U
		}	
		
		is(ins.bltu){	// smaller than unsigned
			io.out := Mux(io.in1 < io.in2, 1.U, 0.U)
		}	

		is(ins.bgeu){   // greater or equal to unsigned
			io.out := Mux(io.in1 < io.in2, 0.U, 1.U) 
		}


	}
	

	// Formal verification
	if (formal){
  
		when(io.state === ins.sll){    // shift left       
			verification.assert(io.out === (io.in1 << io.in2(4,0))(31,0))
		}
	
		when(io.state === ins.slli){    // shift left       
			verification.assert(io.out === (io.in1 << io.in2(4,0))(31,0))
		}

		when(io.state === ins.srl){    // shift right
			verification.assert(io.out === (io.in1 >> io.in2(4,0)))
		}
		
		when(io.state === ins.srli){    // shift right
			verification.assert(io.out === (io.in1 >> io.in2(4,0)))
		}

		when(io.state === ins.sra){    // shift arithmetic right
			when     (io.in1(31) === 0.U){ io.out === shifted }        
			.otherwise{ io.out === (shifted | ("hFFFF_FFFF".U << (32.U - io.in2(4,0)))) } 
		}
		
		when(io.state === ins.srai){    // shift arithmetic right
			when     (io.in1(31) === 0.U){ io.out === shifted }        
			.otherwise{ io.out === (shifted | ("hFFFF_FFFF".U << (32.U - io.in2(4,0)))) } 
		}

		when(io.state === ins.add){    // ADD
			verification.assert(io.out === (totalsum(31,0)))
		}
		
		when(io.state === ins.addi){    // ADD
			verification.assert(io.out === (totalsum(31,0)))
		}

		when(io.state === ins.sub){    // SUB
			verification.assert(io.out === (totalsub(31,0)))                      
		}

		when(io.state === ins.xor){    // XOR
			verification.assert(io.out === (io.in1 ^ io.in2))
		}
		
		when(io.state === ins.xori){    // XOR
			verification.assert(io.out === (io.in1 ^ io.in2))
		}

		when(io.state === ins.or){    // OR
			verification.assert(io.out === (io.in1 | io.in2))
		}
		
		when(io.state === ins.ori){    // OR
			verification.assert(io.out === (io.in1 | io.in2))
		}

		when(io.state === ins.and){    // AND
			verification.assert(io.out === (io.in1 & io.in2))
		}

	}

}


// Verilog Generation

object ALUDriver_Verilog extends App {
	(new ChiselStage).emitVerilog(new ALU(formal=false),args)
}

// SystemVerilog Generation

object ALUDriver_SystemVerilog extends App {
	(new ChiselStage).emitSystemVerilog(new ALU(formal=true),args)
}





