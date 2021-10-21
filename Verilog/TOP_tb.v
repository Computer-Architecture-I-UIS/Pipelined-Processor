`timescale 1 ns/1 ps
module Processor;
	reg clock = 0;
	reg reset;
	wire  [31:0] out;
TOP Processor (
    .clock (clock),  
    .reset (reset),   
    .io_out(out)
);
    

    parameter CLOCK_PERIOD =10;
    always
    begin
        #(CLOCK_PERIOD/2);
        clock = ~clock;
    
    end     
    
    initial begin
    reset = 1;
    #(CLOCK_PERIOD);
    reset= 0;
    end
    
  initial begin
 //   $monitor("At time %t, value = %h (%0d)", $time, value, value);
    $dumpfile("test_TOP.vcd"); // Archivo en el que se guardarán los resultados de la simulación
    $dumpvars;
    //#(10000); // Configurar el tiempo de simulación
    #(1000000); // Configurar el tiempo de simulación
    $finish;
    end
endmodule
