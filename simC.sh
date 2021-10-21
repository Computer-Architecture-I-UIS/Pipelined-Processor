./compile.sh c_program/$1.c
cp dumped_files/boot.mem src/main/scala/Processor/datapath/IF/
./genInstFromMem.sh
./genInstMemo.sh
./do_gen.sh TOP v
cd Verilog/
iverilog -o test *.v
vvp test
gtkwave -o test_TOP.vcd
cd ..