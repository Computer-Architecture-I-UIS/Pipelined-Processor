package Processor

import chisel3._
import chisel3.util._


import chisel3.experimental._ // formal verification
import chisel3.stage.ChiselStage // verilog, sverilog generation

class ALU (val formal:Boolean=false) extends Module{
	val io = IO(new Bundle {
		val instruc = Input(UInt(32.W))
		val in1 = Input(UInt(32.W))
		val in2 = Input(UInt(32.W))
		val out = Output(UInt(32.W))	
	})
	
	val opcode=Wire(UInt(7.W))
	opcode:=io.instruc(6,0)
	val funct3=Wire(UInt(3.W))
	funct3:=io.instruc(14,12)
	val funct7=Wire(UInt(7.W))
	funct7:=io.instruc(31,25)
	
	
	when(opcode==="h37".U)	{		io.out:=0.U		} //lui
	.elsewhen(opcode==="h17".U)	{		io.out:=0.U		} //auipc
	.elsewhen(opcode==="h13".U)	{
		when(funct3==="h0".U)	{			io.out := io.in1 + io.in2		}//addi
		.elsewhen(funct3==="h2".U)	{			io.out := (io.in1.asSInt < io.in2.asSInt).asUInt			}//slti
		.elsewhen(funct3==="h3".U)	{			io.out := Mux(io.in1 < io.in2, 1.U, 0.U)			}//sltiu
		.elsewhen(funct3==="h4".U)	{			io.out := io.in1 ^ io.in2			}//xori
		.elsewhen(funct3==="h6".U)	{			io.out := io.in1 | io.in2			}//ori
		.elsewhen(funct3==="h7".U)	{			io.out := io.in1 & io.in2			}//andi
		.elsewhen(funct3==="h1".U)	{			io.out := io.in1 << io.in2(4,0)		}//slli
		.elsewhen(funct3==="h5".U)	{
			when(funct7==="h0".U)	{				io.out := io.in1 >> io.in2(4,0)				}//srli
			.elsewhen(funct7==="h20".U)	{				when     (io.in1(31) === 0.U){ io.out := io.in1 >> io.in2(4,0) } 
			       
																					.otherwise{ io.out := io.in1 >> io.in2(4,0) | ("hFFFF_FFFF".U << (32.U - io.in2(4,0))) } 																								
																					}//srai 
																					
			.otherwise{						io.out:=0.U						}
			} .otherwise{						io.out:=0.U						}
		}
	.elsewhen(opcode==="h33".U)	{
		when(funct3==="h0".U)	{
			when(funct7==="h0".U)	{				io.out := io.in1 + io.in2				}//add
			.elsewhen(funct7==="h20".U)	{				io.out:=io.in1 - io.in2				}//sub 
			.otherwise{						io.out:=0.U						}
			} 
		.elsewhen(funct3==="h1".U)	{
			when(funct7==="h0".U)	{				io.out := io.in1 << io.in2(4,0)				} //sll
			.otherwise{						io.out:=0.U					}
			} 
		.elsewhen(funct3==="h2".U)	{
			when(funct7==="h0".U)	{				io.out := (io.in1.asSInt < io.in2.asSInt).asUInt				} //slt
			.otherwise{						io.out:=0.U				  	}
			} 
		.elsewhen(funct3==="h3".U)	{
			when(funct7==="h0".U)	{				io.out := Mux(io.in1 < io.in2, 1.U, 0.U)				} //sltu
			.otherwise{					io.out:=0.U				}
			} 
		.elsewhen(funct3==="h4".U)	{
			when(funct7==="h0".U)	{				io.out := io.in1 ^ io.in2				} //xor
			.otherwise{					io.out:=0.U				}
			} 
		.elsewhen(funct3==="h5".U)	{
			when(funct7==="h0".U)	{				io.out := io.in1 >> io.in2(4,0)				}//srl
			.elsewhen(funct7==="h20".U)	{				when     (io.in1(31) === 0.U){ io.out := io.in1 >> io.in2(4,0) } 
			       
																					.otherwise{ io.out := io.in1 >> io.in2(4,0) | ("hFFFF_FFFF".U << (32.U - 																						io.in2(4,0))) }				
																					}//sra 
			.otherwise{					io.out:=0.U				}
			} 
		.elsewhen(funct3==="h6".U)	{
			when(funct7==="h0".U)	{				io.out := io.in1 | io.in2				}// or
			.otherwise{					io.out:=0.U				}
			}
  	.elsewhen(funct3==="h7".U)	{
			when(funct7==="h0".U)	{				io.out := io.in1 & io.in2				} //and
			.otherwise{					io.out:=0.U				}
			} .otherwise{						io.out:=0.U						}
		}.otherwise{					io.out:=0.U				}

	if (formal){
		when(opcode === "h37".U || opcode === "h17".U){ // lui, auipc
			verification.assert(io.out === 0.U)
		}
		when(opcode === "h13".U && funct3 === "h0".U){ // addi
			verification.assert(io.out === (io.in1 + io.in2))
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

